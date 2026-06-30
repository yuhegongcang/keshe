<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 订单明细</title>
    <style>
        .info-table { width: 70%; margin: 20px auto; border-collapse: collapse; text-align: center; }
        .info-table th, .info-table td { border: 1px solid #ddd; padding: 10px; }
        .info-table th { background-color: #f9f9f9; }
    </style>
</head>
<body style="margin: 0; font-family: '微软雅黑', sans-serif;">

    <jsp:include page="Link.jsp" />

    <h3 align="center" style="margin-top: 30px;">📄 订单详细信息</h3>
    
    <%
        String orderNo = (String) request.getAttribute("orderNo");
        String status = (String) request.getAttribute("status");
        String username = (String) session.getAttribute("username");
    %>
    
    <div style="width: 70%; margin: 0 auto; background: #fdfdfd; padding: 15px; border: 1px dashed #ccc;">
        <p><strong>订单编号：</strong> <%= orderNo %></p>
        <p><strong>收货人：</strong> <%= username %></p>
        <p><strong>订单状态：</strong> <span style="color:red;"><%= status %></span></p>
    </div>

    <table class="info-table">
        <tr>
            <th>购买手办</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计金额</th>
        </tr>
        <%
            ArrayList<OrderBean> arr = (ArrayList<OrderBean>) request.getAttribute("arr");
            int realTotalPrice = 0;
            if (arr != null) {
                for (OrderBean order : arr) {
                    realTotalPrice += order.getSumPrice();
        %>
        <tr>
            <td style="color: #3498db;"><%= order.getShoeName() %></td>
            <td>￥<%= order.getPrice() %></td>
            <td><%= order.getAmount() %>个</td>
            <td style="color: #e74c3c; font-weight: bold;">￥<%= order.getSumPrice() %></td>
        </tr>
        <%      }
            }
        %>
    </table>
    
    <div style="width: 70%; margin: 20px auto; text-align: right; font-size: 18px;">
        实际需付款：<strong style="color: #e74c3c; font-size: 24px;">￥<%= realTotalPrice %></strong>
    </div>

</body>
</html>