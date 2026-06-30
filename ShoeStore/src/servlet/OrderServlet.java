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
import db.Order;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        
        // 🚨 极其关键的修复：统一使用 currentUser 作为登录凭证
        String currentUser = (String) session.getAttribute("currentUser");

        // 1. 安全校验：未登录踢回登录页 (拦截未授权访问)
        if (currentUser == null || currentUser.isEmpty()) {
            // 使用重定向直接打回登录页更加彻底
            response.sendRedirect("Login.jsp");
            return; 
        }

        // 2. 防御性编程：如果没传 type 参数，默认给他看订单列表
        if (type == null) {
            type = "init";
        }

        Order orderDb = new Order();

        // 3. 核心业务分发：使用 Switch 结构大幅提升代码整洁度与执行效率
        switch (type) {
            case "submit":
                boolean isSubmitSuccess = orderDb.createOrderFromCart(currentUser);
                // 巧妙使用三元运算符简化 if-else
                request.setAttribute("msg", isSubmitSuccess ? "✅ 下单成功！" : "❌ 购物车为空，下单失败！");
                request.getRequestDispatcher("OrderServlet?type=init").forward(request, response);
                break;

            case "init":
                ArrayList<OrderBean> initArr = orderDb.getOrderList(currentUser);
                request.setAttribute("arr", initArr);
                request.getRequestDispatcher("Order.jsp").forward(request, response);
                break;

            case "orderInfo":
                String orderNoInfo = request.getParameter("orderNo");
                String status = request.getParameter("status");
                
                ArrayList<OrderBean> detailArr = orderDb.getOrderDetail(currentUser, orderNoInfo);
                request.setAttribute("arr", detailArr);
                request.setAttribute("orderNo", orderNoInfo);
                request.setAttribute("status", status);
                request.getRequestDispatcher("OrderInfo.jsp").forward(request, response);
                break;

            case "delete":
                String orderNoDel = request.getParameter("orderNo");
                boolean isDelSuccess = orderDb.deleteOrder(currentUser, orderNoDel);
                request.setAttribute("msg", isDelSuccess ? "🗑️ 订单删除成功！" : "⚠️ 订单删除失败！");
                request.getRequestDispatcher("OrderServlet?type=init").forward(request, response);
                break;

            case "pay":
                String orderNoPay = request.getParameter("orderNo");
                boolean isPaySuccess = orderDb.payOrder(currentUser, orderNoPay);
                request.setAttribute("msg", isPaySuccess ? "💰 支付成功！感谢您的购买！" : "⚠️ 支付失败！");
                request.getRequestDispatcher("OrderServlet?type=init").forward(request, response);
                break;

            default:
                // 如果传入了未知的 type，直接跳回首页防止系统崩溃
                response.sendRedirect("Index.jsp");
                break;
        }
    }
}