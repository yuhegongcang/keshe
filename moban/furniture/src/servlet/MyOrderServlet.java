package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.OrderBean;
import db.OrderDb;

@WebServlet("/MyOrderServlet")
public class MyOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 检查是否登录
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // 2. 去数据库捞订单
        OrderDb orderDb = new OrderDb();
        ArrayList<OrderBean> orderList = orderDb.getMyOrders(username);

        // 3. 传给页面展示
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("Order.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}