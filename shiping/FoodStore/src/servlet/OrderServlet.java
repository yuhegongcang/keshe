package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Order;
import bean.OrderBean;
import util.Common;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String type = request.getParameter("type");
        
        String url = "Index.jsp"; // 默认跳转
        
        if (username == null || username.isEmpty()) {
            url = "login.jsp"; // 安全校验：未登录踢回登录页
        } else if (type != null) {
            Order orderDb = new Order();
            
            // 1. 生成新订单 (从购物车结账)
            if (type.equals("add")) {
                Common common = new Common();
                String orderNo = common.GenerateOrderNo(); // 生成唯一订单号 (如 20260626083015)
                
                boolean isSuccess = orderDb.addOrder(username, orderNo);
                
                // 无论成功与否，生成订单后直接重定向到"查看订单列表"的逻辑
                response.sendRedirect("OrderServlet?type=init");
                return; // 重要：重定向后必须 return，终止后续请求转发
            } 
            
            // 2. 初始化查看订单列表
            else if (type.equals("init")) {
                ArrayList<OrderBean> arr = orderDb.getListBean(username);
                request.setAttribute("arr", arr);
                url = "Order.jsp"; // 带着数据转发到展示页面
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}