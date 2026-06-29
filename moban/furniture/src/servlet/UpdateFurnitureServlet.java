package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import bean.FurnitureBean;
import db.Furniture;

@MultipartConfig
@WebServlet("/UpdateFurnitureServlet")
public class UpdateFurnitureServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // ==============================================================================
        // 0. 【安全校验】后端拦截：防止普通用户通过工具或直接访问接口强行修改商品
        // ==============================================================================
        Object user = request.getSession().getAttribute("user"); // 获取当前登录状态，请根据你 LoginServlet 存的名字修改（如 "admin"）
        
        // 如果没有登录，或者不是管理员身份（你可以补充具体的角色判断，例如 ((UserBean)user).getRole() != 1）
        if (user == null) {
            response.getWriter().write("<script>alert('非法操作：权限不足！仅限管理员修改商品。'); window.history.back();</script>");
            return; // 极其关键的 return！直接终止代码，不让后面的修改逻辑执行
        }
        // ==============================================================================

        // 1. 获取前端传来的所有修改数据
        int id = Integer.parseInt(request.getParameter("id"));
        String fname = request.getParameter("fname");
        String fcategory = request.getParameter("fcategory");
        int fprice = Integer.parseInt(request.getParameter("fprice"));
        String fmaterial = request.getParameter("fmaterial");
        String fdesc = request.getParameter("fdesc");

        // 获取图片文件流
        Part filePart = request.getPart("fimage");
        
        Furniture db = new Furniture();
        // 先去数据库查出这个家具原本长什么样（特别是旧图片叫什么名字）
        FurnitureBean oldFb = db.getFurnitureById(id);
        String finalImgName = oldFb.getImgName(); // 默认保留旧图片名

        // 2. 核心逻辑：判断老板有没有上传新图片
        // 如果 size > 0，说明老板不仅改了文字，还选了一张新图片！
        if (filePart != null && filePart.getSize() > 0) {
            
            // --- 【阶段 A：利用你刚才在 Canvas 里选中的逻辑，物理粉碎旧图片】 ---
            if (finalImgName != null && !finalImgName.equals("default.jpg") && !finalImgName.isEmpty()) {
                String oldSrcPath = "D:\\workspace\\furniture\\WebContent\\img\\" + finalImgName;
                File oldSrcFile = new File(oldSrcPath);
                if (oldSrcFile.exists()) oldSrcFile.delete(); 

                String oldTomcatPath = request.getServletContext().getRealPath("/img/") + finalImgName;
                File oldTomcatFile = new File(oldTomcatPath);
                if (oldTomcatFile.exists()) oldTomcatFile.delete();
            }

            // --- 【阶段 B：生成并保存新图片】 ---
            String oldName = filePart.getSubmittedFileName();
            String baseName = "";
            String ext = "";
            if (oldName != null && oldName.contains(".")) {
                baseName = oldName.substring(0, oldName.lastIndexOf("."));
                ext = oldName.substring(oldName.lastIndexOf("."));
            }
            // 智能生成新名字：原名_时间戳.jpg
            finalImgName = baseName + "_" + System.currentTimeMillis() + ext; 

            // 双写保存新图片
            String srcPath = "D:\\workspace\\furniture\\WebContent\\img\\" + finalImgName;
            String tomcatPath = request.getServletContext().getRealPath("/img/") + finalImgName;

            filePart.write(tomcatPath);
            java.nio.file.Files.copy(
                java.nio.file.Paths.get(tomcatPath), 
                java.nio.file.Paths.get(srcPath)
            );
        }

        // 3. 带着最终的图片名（如果有新图就是新名字，没上传新图就是老名字），去更新数据库
        boolean isUpdated = db.updateFurniture(id, fname, fcategory, fprice, fmaterial, fdesc, finalImgName);
        if (isUpdated) {
            response.getWriter().write("<script>alert('商品修改成功！'); window.location.href='IndexServlet';</script>");
        } else {
            response.getWriter().write("<script>alert('修改失败！'); window.history.back();</script>");
        }
    }
}