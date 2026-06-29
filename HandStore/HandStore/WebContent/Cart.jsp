<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.CartBean,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>我的购物车</title>
    <script>
        function del(id){
            document.form1.action="cart?type=del&id="+id;
            document.form1.submit();
        }
        function change(id){
            let price = document.getElementById("price"+id).innerText;
            let amount = document.getElementById("amount"+id).value;
            let sum = price * amount;
            document.getElementById("sumPrice"+id).innerText = sum;
        }
    </script>
</head>
<body>
<jsp:include page="Link.jsp"></jsp:include>
<%
@SuppressWarnings("unchecked")
ArrayList<CartBean> list = (ArrayList<CartBean>) request.getAttribute("cartBean");
String msg = "";
int totalAmt=0,totalMoney=0;
%>
<div class="container">
<form name="form1" method="post">
    <h2 class="text-center">我的购物车</h2>
    <% if(!msg.isEmpty()){ %>
        <p class="text-center text-danger"><%=msg%></p >
    <%}%>
    <table class="table table-bordered text-center">
        <tr>
            <th>手办名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
            <th>操作</th>
        </tr>
        <% if(list != null && list.size()>0){ %>
            <% // 循环遍历购物车商品
            for(CartBean c : list){
                // 累加总数、总金额
                totalAmt += c.getAmount();
                totalMoney += c.getSumPrice();
            %>
            <tr>
                <td><%=c.getHandName()%></td>
                <td id="price<%=c.getId()%>"><%=c.getPrice()%></td>
                <td>
                    <input type="number" id="amount<%=c.getId()%>" value="<%=c.getAmount()%>" onchange="change(<%=c.getId()%>)"/>
                    <input type="hidden" id="oldAmount<%=c.getId()%>" value="<%=c.getAmount()%>">
                </td>
                <td id="sumPrice<%=c.getId()%>"><%=c.getSumPrice()%></td>
                <td><button class="btn btn-danger" onclick="del(<%=c.getId()%>)">删除</button></td>
            </tr>
            <% } %>
        <% }else{ %>
            <tr><td colspan="5">购物车暂无商品</td></tr>
        <%} %>
    </table>
    <div class="text-right">
        <h4>商品总数：<%=totalAmt%> 件 &nbsp;&nbsp;总价：<%=totalMoney%> 元</h4>
        <a href=" " class="btn btn-success">继续购物</a >
        <a href="OrderServlet?type=add" class="btn btn-primary">提交订单</a >
    </div>
</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>