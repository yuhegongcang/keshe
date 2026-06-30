package servlet;

import util.DBUtil;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddShoeServlet")
@MultipartConfig // 这个注解极其重要！有了它，Java才能接收图片文件
public class AddShoeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置中文编码，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 接收普通文本数据
        String shoeName = request.getParameter("shoeName");
        String brand = request.getParameter("brand");
        String size = request.getParameter("size");
        String priceStr = request.getParameter("price");
        String introduce = request.getParameter("introduce");

        // 3. 处理图片文件上传核心逻辑
        Part filePart = request.getPart("shoeImg"); // 获取前端传来的文件
        String fileName = filePart.getSubmittedFileName(); // 获取图片原始文件名（比如 aj.png）
        
        // 获取服务器运行时的 img 文件夹真实绝对路径
        String uploadPath = request.getServletContext().getRealPath("/img");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // 如果没这个文件夹就建一个
        }
        
        // 4. 将图片实体文件保存到服务器
        filePart.write(uploadPath + File.separator + fileName);
        System.out.println("图片已成功保存到物理路径: " + uploadPath + File.separator + fileName);

        // 5. 生成存入数据库的相对路径 (例如 "img/aj.png")
        String dbImgPath = "img/" + fileName;

        double price = Double.parseDouble(priceStr);
        boolean isSuccess = DBUtil.insertShoe(shoeName, brand, size, price, introduce, dbImgPath);

        // 7. 判断是否成功并跳转
        if (isSuccess) {
            System.out.println("商品发布成功，数据库已更新！");
            response.sendRedirect("Index.jsp"); // 成功后跳回首页看效果
        } else {
            response.getWriter().println("<script>alert('发布失败，请检查数据库连接！');history.back();</script>");
        }
     // 如果文件名包含路径符，只截取最后的文件名部分
        if (fileName.contains("\\")) {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        }
    }
}