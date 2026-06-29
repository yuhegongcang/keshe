<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.OrderBean,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery.min.js"></script>
</head>
<body>
<jsp:include page="Link.jsp"></jsp:include>
<%
// 消除泛型强转警告
@SuppressWarnings("unchecked")
ArrayList<OrderBean> orderList = (ArrayList<OrderBean>) request.getAttribute("orderList");
%>
<div class="container">
    <h2 class="text-center">我的订单</h2>
    <table class="table table-bordered text-center">
        <tr>
            <th>订单编号</th>
            <th>订单状态</th>
            <th>手办名称</th>
            <th>单价</th>
            <th>购买数量</th>
            <th>小计金额</th>
            <th>操作</th>
        </tr>
        <% if(orderList != null && orderList.size()>0){ %>
            <% for(OrderBean o : orderList){ %>
            <tr>
                <td>
                    <a href=" >">
                        <%=o.getOrderNo()%>
                    </a >
                </td>
                <td><%=o.getStatus()%></td>
                <td><%=o.getHandName()%></td>
                <td><%=o.getPrice()%>元</td>
                <td><%=o.getAmount()%></td>
                <td><%=o.getSumPrice()%>元</td>
                <td>
                    <a href="OrderServlet?type=orderInfo&orderNo=<%=o.getOrderNo()%>" class="btn btn-info btn-sm">
                        查看详情
                    </a >
                </td>
            </tr>
            <% } %>
        <% }else{ %>
            <tr>
                <td colspan="7">暂无订单记录</td>
            </tr>
        <% } %>
    </table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>