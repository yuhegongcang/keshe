<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.GoodsBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>手办详情</title>
</head>
<body>
<jsp:include page="Link.jsp"/>
<%
    GoodsBean goods = (GoodsBean) request.getAttribute("goods");
%>
<div class="container">
    <div class="row thumbnail">
        <div class="col-sm-6">
            <h3><%=goods.getHandName()%></h3>
            <p>品牌：<%=goods.getBrand()%></p>
            <p>分类：<%=goods.getType()%></p>
            <p>价格：<%=goods.getPrice()%> 元</p>
            <p>商品介绍：<%=goods.getIntroduce()%></p>
            <div style="margin-top:20px">
                <a class="btn btn-primary btn-block" href="OrderServlet?type=directBuy&id=<%=goods.getId()%>&handName=<%=goods.getHandName()%>&price=<%=goods.getPrice()%>">立即购买</a>
                <a class="btn btn-default btn-block" style="margin-top:10px" href="cart?type=addFromBookInfo&id=<%=goods.getId()%>&handName=<%=goods.getHandName()%>&price=<%=goods.getPrice()%>">加入购物车</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>