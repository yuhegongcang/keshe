package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前 session [cite: 2338]
        HttpSession session = request.getSession();
        
        // 使 session 无效化，清除所有登录状态信息 [cite: 2344, 2345]
        session.invalidate();
        
        // 页面跳转回到首页 [cite: 2348, 2349]
        response.sendRedirect("Index.jsp");
    }
}