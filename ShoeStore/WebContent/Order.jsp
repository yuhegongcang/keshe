<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 我的订单</title>
    <style>
        .order-table { width: 80%; margin: 20px auto; border-collapse: collapse; text-align: center; }
        .order-table th, .order-table td { border: 1px solid #ddd; padding: 12px; }
        .order-table th { background-color: #f4f4f4; }
        /* 专门给支付按钮写的漂亮样式 */
        .btn-pay { background:#e74c3c; color:white; text-decoration:none; padding:6px 16px; border-radius:3px; display:inline-block; font-weight:bold; transition: 0.3s; }
        .btn-pay:hover { background:#c0392b; }
        .btn-paid { background:#27ae60; color:white; text-decoration:none; padding:6px 16px; border-radius:3px; display:inline-block; cursor:not-allowed; }
    </style>
</head>
<body style="margin: 0; font-family: '微软雅黑', sans-serif;">

    <jsp:include page="Link.jsp" />

    <h2 align="center" style="margin-top: 30px;">📦 我的订单</h2>
    
    <% 
        String msg = (String) request.getAttribute("msg");
        if (msg != null && !msg.isEmpty()) { 
    %>
        <div style="color: #27ae60; text-align: center; font-weight: bold; font-size: 18px; margin-bottom: 15px;"><%= msg %></div>
    <%  } %>

    <table class="order-table">
        <tr>
            <th>订单编号</th>
            <th>订单状态</th>
            <th>商品总数</th>
            <th>订单总价</th>
            <th>操作</th>
        </tr>
        
        <%
            ArrayList<OrderBean> arr = (ArrayList<OrderBean>) request.getAttribute("arr");
            if (arr != null && arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    OrderBean order = arr.get(i);
                    String status = order.getStatus() == null ? "未结算" : order.getStatus();
                    boolean isPaid = status.contains("已支付") || status.contains("已结算");
        %>
        <tr>
            <td>
                <a href="OrderServlet?type=detail&orderNo=<%= order.getOrderNo() %>" style="color: #3498db; text-decoration: underline; font-weight: bold;">
                    <%= order.getOrderNo() %>
                </a>
            </td>
            
            <td style="color: <%= isPaid ? "#27ae60" : "#e74c3c" %>; font-weight: bold;"><%= status %></td>
            
            <td><%= order.getTotalAmount() %> 双</td>
            <td style="color: #e74c3c; font-weight: bold;">￥<%= order.getTotalPrice() %></td>
            
            <td>
                <% if (!isPaid) { %>
                    <a href="OrderServlet?type=pay&orderNo=<%= order.getOrderNo() %>" class="btn-pay">💳 去支付</a>
                <% } else { %>
                    <span class="btn-paid">✅ 已完成</span>
                <% } %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="padding: 30px; color: #7f8c8d;">您还没有下过任何订单哦！</td>
        </tr>
        <%  } %>
    </table>

</body>
</html>