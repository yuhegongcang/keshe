package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取当前用户的 session [cite: 2336-2338]
        HttpSession session = request.getSession();
        
        // 2. 让 session 失效（清除里面存的用户名等状态） [cite: 2343-2345]
        session.invalidate();
        
        // 3. 退出成功后，跳转回商城首页 [cite: 2347-2351]
        RequestDispatcher dispacher = request.getRequestDispatcher("Index.jsp");
        dispacher.forward(request, response);
    }
}