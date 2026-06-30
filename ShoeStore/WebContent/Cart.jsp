<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.CartBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 我的购物车</title>
    <style>
        .cart-table { width: 80%; margin: 20px auto; border-collapse: collapse; text-align: center; }
        .cart-table th, .cart-table td { border: 1px solid #ddd; padding: 12px; }
        .cart-table th { background-color: #f4f4f4; }
    </style>
</head>
<body style="margin: 0; font-family: '微软雅黑', sans-serif;">

    <jsp:include page="Link.jsp" />

    <h2 align="center" style="margin-top: 30px;">🛒 我的购物车</h2>

    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null && !msg.isEmpty()) {
    %>
        <div style="color: #e74c3c; text-align: center; font-weight: bold; margin-bottom: 15px;"><%= msg %></div>
    <%  } %>

    <table class="cart-table">
        <tr>
            <th>手办名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
            <th>操作</th>
        </tr>
        
        <%
            ArrayList<CartBean> arr = (ArrayList<CartBean>) request.getAttribute("arr");
            int totalPrice = 0; // 用于计算总价
            
            if (arr != null && arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    CartBean cart = arr.get(i);
                    totalPrice += cart.getSumPrice();
        %>
        <tr>
            <td><%= cart.getShoeName() %></td>
            <td>￥<%= cart.getPrice() %></td>
            <td>
                <% if (cart.getAmount() > 1) { %>
                    <a href="CartServlet?type=subOne&id=<%= cart.getId() %>" style="padding: 2px 8px; background: #eee; text-decoration: none; color: #333; border: 1px solid #ccc;">-</a>
                <% } else { %>
                    <span style="padding: 2px 8px; background: #f9f9f9; color: #ccc; border: 1px solid #eee;">-</span>
                <% } %>
                
                <input type="text" value="<%= cart.getAmount() %>" style="width: 40px; text-align: center; margin: 0 5px;" readonly>
                
                <a href="CartServlet?type=addOne&id=<%= cart.getId() %>" style="padding: 2px 8px; background: #eee; text-decoration: none; color: #333; border: 1px solid #ccc;">+</a>
            </td>
            <td style="color: #e74c3c; font-weight: bold;">￥<%= cart.getSumPrice() %></td>
            <td>
                <a href="CartServlet?type=del&id=<%= cart.getId() %>" style="color: #c0392b; text-decoration: none;">删除</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="padding: 30px; color: #7f8c8d;">购物车空空如也，快去挑心怡的手办吧！</td>
        </tr>
        <%  } %>
    </table>

    <div style="width: 80%; margin: 0 auto; text-align: right; font-size: 18px;">
        <p>合计总价：<strong style="color: #e74c3c; font-size: 24px;">￥<%= totalPrice %></strong></p>
        <a href="Index.jsp" style="background:#95a5a6; color:white; padding:10px 20px; text-decoration:none; border-radius: 3px; margin-right: 15px;">继续购物</a>
        <a href="OrderServlet?type=submit" style="background:#e67e22; color:white; padding:10px 20px; text-decoration:none; border-radius: 3px;">结算下单</a>
    </div>

</body>
</html>