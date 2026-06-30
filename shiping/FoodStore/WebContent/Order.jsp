<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>大科食品商城 - 我的订单</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">

</head>
<body>

<jsp:include page="Link.jsp" />

<div class="container" style="margin-top: 40px;">
    <h2 class="text-center" style="margin-bottom: 30px;">我的订单中心</h2>
    
    <div class="row">
        <div class="col-sm-12">
           <table class="table table-striped table-hover table-bordered text-center">
    <tr class="success">
        <th class="text-center">订单编号</th>
        <th class="text-center">食品名称</th>
        <th class="text-center">购买单价</th>
        <th class="text-center">购买数量</th>
        <th class="text-center">总计 (元)</th>
        <th class="text-center">交易状态</th>
    </tr>
    <%
        // 1. 获取后台传过来的订单列表
        ArrayList<OrderBean> arr = (ArrayList<OrderBean>) request.getAttribute("arr");
        
        // 2. 判断列表是否有数据
        if (arr != null && !arr.isEmpty()) {
            // 3. 开始循环，每次循环生成一个 item 变量代表一条订单
            for (OrderBean item : arr) {
    %>
    <tr>
        <td style="color: #888;"><%=item.getOrderNo()%></td>
        <td><b><%=item.getFoodName()%></b></td>
        <td><%=item.getPrice()%></td>
        <td><%=item.getAmount()%></td>
        <td style="color: red; font-weight: bold;"><%=item.getSumPrice()%></td>
        <td>
            <span class="label label-success"><%=item.getStatus()%></span>
        </td>
    </tr>
    <%
            } // 5. 循环结束的大括号
        } else {
    %>
    <tr>
        <td colspan="6" class="text-muted" style="padding: 40px;">您还没有下过订单哦，快去选购心仪的美食吧！</td>
    </tr>
    <%  } %>
</table>
        </div>
    </div>
</div>
</body>
</html>