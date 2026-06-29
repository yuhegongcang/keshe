package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.FurnitureBean;
import db.Furniture;

@WebServlet("/DeleteFurnitureServlet")
public class DeleteFurnitureServlet extends HttpServlet {
    
    // 删除操作通常通过 <a> 标签跳转触发，所以使用 doGet 接收
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 获取要删除的家具 ID
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.getWriter().write("<script>alert('参数错误！'); window.history.back();</script>");
            return;
        }
        int id = Integer.parseInt(idStr);

        Furniture db = new Furniture();

        // 2. 核心：在删除数据库前，先查出图片名字，执行物理删除
        FurnitureBean fb = db.getFurnitureById(id);
        if (fb != null) {
            String imgName = fb.getImgName();

            // 防御性判断：如果有图片，且不是默认图片，就执行粉碎操作
            if (imgName != null && !imgName.equals("default.jpg") && !imgName.isEmpty()) {
                
                // 🔪 路径一：删除 D 盘源码目录里的永久备份图片
                String srcPath = "D:\\workspace\\furniture\\WebContent\\img\\" + imgName;
                File srcFile = new File(srcPath);
                if (srcFile.exists()) {
                    srcFile.delete(); 
                }

                // 🔪 路径二：顺手把 Tomcat 运行缓存目录里的也删了，保证网页刷新后彻底消失
                String tomcatPath = request.getServletContext().getRealPath("/img/") + imgName;
                File tomcatFile = new File(tomcatPath);
                if (tomcatFile.exists()) {
                    tomcatFile.delete();
                }
            }
        }

        // 3. 图片灰飞烟灭后，最后把数据库里的记录彻底删掉
        boolean isDeleted = db.deleteFurnitureById(id);

        if (isDeleted) {
            response.getWriter().write("<script>alert('删除成功，相关图片已彻底清理！'); window.location.href='IndexServlet';</script>");
        } else {
            response.getWriter().write("<script>alert('系统繁忙，删除失败！'); window.history.back();</script>");
        }
    }
}