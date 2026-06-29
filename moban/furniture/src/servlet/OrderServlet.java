package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.OrderDb;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String type = request.getParameter("type");
        if ("add".equals(type)) {
            OrderDb orderDb = new OrderDb();
            boolean success = orderDb.checkout(username);
            if (success) {

                response.getWriter().write("<script>alert('下单成功！已为您自动扣款。'); window.location.href='IndexServlet';</script>");
            } else {
                response.sendRedirect("CartServlet");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}