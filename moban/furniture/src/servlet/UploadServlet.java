package servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import db.Furniture;

// 这个注解是文件上传的灵魂！
@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 接收前端填写的普通文本数据
        String fname = request.getParameter("fname");
        String fcategory = request.getParameter("fcategory");
        int fprice = Integer.parseInt(request.getParameter("fprice"));
        String fmaterial = request.getParameter("fmaterial");
        String fdesc = request.getParameter("fdesc");

        // 2. 接收图片文件
        Part filePart = request.getPart("fimage");
        
        String oldName = filePart.getSubmittedFileName(); 
        String baseName = "";
        String ext = "";
        
        if (oldName != null && oldName.contains(".")) {
            baseName = oldName.substring(0, oldName.lastIndexOf("."));
            ext = oldName.substring(oldName.lastIndexOf("."));     
        }
        
        String newFileName = baseName + "_" + System.currentTimeMillis() + ext;
        // 4. 先放进 Tomcat 运行目录（网页立即可见）
        String tomcatPath = request.getServletContext().getRealPath("/img");
        String tomcatFullPath = tomcatPath + File.separator + newFileName;
        filePart.write(tomcatFullPath);

        // 5. 顺手复制一份到你的 D 盘源码目录（永久保存）
        String srcPath = "D:\\workspace\\furniture\\WebContent\\img";
        String srcFullPath = srcPath + File.separator + newFileName;
        
        // 使用 Java 极简的自带复制工具
        java.nio.file.Files.copy(
            java.nio.file.Paths.get(tomcatFullPath), 
            java.nio.file.Paths.get(srcFullPath)
        );
        
        // 写入Tomcat运行目录（因为 Part.write 只能调一次，我们用复制或者再次写入）
        // 提示：最稳妥的做法是直接用 filePart 再次写到运行目录
        // 但由于 Servlet 机制限制，我们可以直接把 filePart 写入运行目录，
        // 然后利用 Java 文件流复制到源码目录。
        // 6. 调用刚才写好的方法，把所有数据存进数据库
        Furniture furnitureDb = new Furniture();
        boolean isSuccess = furnitureDb.insertFurniture(fname, fcategory, fprice, fmaterial, fdesc, newFileName);

        if (isSuccess) {
            response.getWriter().write("<script>alert('上架成功！'); window.location.href='IndexServlet';</script>");
        } else {
            response.getWriter().write("<script>alert('系统繁忙，上架失败！'); window.history.back();</script>");
        }
    }
}