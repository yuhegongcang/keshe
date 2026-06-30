package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import db.Order;

@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        
        if ("list".equals(type)) {
            // 1. 获取所有订单
            Order orderDb = new Order();
            ArrayList<OrderBean> arr = orderDb.getAllOrders();
            
            // 2. 存入 Request
            request.setAttribute("arr", arr);
            
            // 3. 转发到后台订单列表页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/OrderList.jsp");
            dispatcher.forward(request, response);
            
        } else if ("deliver".equals(type)) {
            // 获取需要发货的订单号
            String orderNo = request.getParameter("orderNo");
            
            // 执行发货（更新数据库状态）
            Order orderDb = new Order();
            orderDb.deliverOrder(orderNo);
            
            // 操作完成后，重定向回订单列表页，实现页面刷新
            response.sendRedirect(request.getContextPath() + "/AdminOrderServlet?type=list");
        }
    }
}