package servlet;

import util.DBUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteShoeServlet")
public class DeleteShoeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 因为我们是通过 <a> 标签跳转过来的，所以请求方式是 GET，必须写在 doGet 里
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
     
        String idStr = request.getParameter("id");
        
        if (idStr != null && !idStr.isEmpty()) {
            int shoeId = Integer.parseInt(idStr);
            
            // 2. 调用数据库工具类，执行删除
            boolean isSuccess = DBUtil.deleteShoe(shoeId);
            
            if (isSuccess) {
                System.out.println("成功删除 ID 为 " + shoeId + " 的球鞋！");
            } else {
                System.out.println("删除失败，请检查数据库！");
            }
        }
        
        response.sendRedirect("Index.jsp");
    }
}