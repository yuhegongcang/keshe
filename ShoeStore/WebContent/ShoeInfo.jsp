<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.ShoeBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 商品详情</title>
</head>
<body style="margin: 0; font-family: '微软雅黑', sans-serif; text-align: center;">

    <jsp:include page="Link.jsp" />

    <%
        ShoeBean shoe = (ShoeBean) request.getAttribute("shoe");
        if (shoe != null) {
    %>
    
    <div class="container" style="margin-top: 40px; margin-bottom: 50px;">
    
    <div class="row" style="background: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1);">
        
        <div class="col-md-5 text-center">
            <img src="<%= shoe.getImage() %>" class="img-responsive img-thumbnail" alt="球鞋图片" style="width: 100%; max-width: 400px; border: none; box-shadow: 0 2px 8px rgba(0,0,0,0.05);">
        </div>
        
        <div class="col-md-7" style="padding-top: 20px;">
            <h2 style="font-weight: bold; color: #333;"><%= shoe.getShoeName() %></h2>
            <hr>
            <p style="font-size: 16px; color: #7f8c8d;"><strong>品牌：</strong> <%= shoe.getBrand() %></p>
            <p style="font-size: 16px; color: #7f8c8d;"><strong>大小：</strong> <%= shoe.getSize() %></p>
            <p style="font-size: 16px; color: #7f8c8d;"><strong>详细介绍：</strong> <%= shoe.getIntroduce() %></p>
            
            <h3 style="color: #e74c3c; font-weight: bold; margin-top: 30px;">￥<%= shoe.getPrice() %></h3>
            
            <div style="margin-top: 40px;">
                <a href="CartServlet?type=add&id=<%= shoe.getId() %>" class="btn btn-primary btn-lg" style="padding: 10px 40px; margin-right: 15px;">🛒 加入购物车</a>
                <a href="javascript:history.back()" class="btn btn-default btn-lg">返回列表</a>
            </div>
        </div>

    </div>
</div>

    <% } else { %>
        <h3 style="color: red; margin-top: 100px;">⚠️ 抱歉，找不到该手办的信息！</h3>
    <% } %>

</body>
</html>