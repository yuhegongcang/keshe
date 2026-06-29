<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.FurnitureBean"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
    <jsp:include page="nav.jsp" /> <div class="container" style="margin-top: 50px;">
        <%
            FurnitureBean fb = (FurnitureBean) request.getAttribute("furniture");
            if (fb != null) {
        %>
        <div class="col-md-6 text-center">
    		<img src="/furniture/img/<%= fb.getImgName() %>" style="width: 100%; border-radius: 10px;">
		</div>
            
            <div class="col-md-6">
                <h2 style="font-weight: bold; color: #333;"><%= fb.getName() %></h2>
                <p style="font-size: 16px; color: #777;">分类：<%= fb.getCategory() %></p>
                <hr>
                <h1 style="color: #e3000f; font-weight: bold;">¥ <%= fb.getPrice() %></h1>
                <p style="margin-top: 20px; font-size: 16px;"><strong>主要材质：</strong><%= fb.getMaterial() %></p>
                <div style="margin-top: 20px; padding: 15px; background: #f8f9fa; border-left: 4px solid #f0ad4e;">
                    <strong>商品描述：</strong><br>
                    <%= fb.getDescription() %>
                </div>
                
                <div style="margin-top: 40px;">
                    <a href="AddCartServlet?fid=<%= fb.getId() %>" class="btn btn-warning btn-lg" style="width: 200px;">加入购物车</a>
                    
                    <a href="IndexServlet" class="btn btn-default btn-lg" style="margin-left: 10px;">继续逛逛</a>
                </div>
            </div>
        </div>
        <% } else { %>
            <div class="alert alert-danger">找不到该商品的信息。</div>
        <% } %>
    </div>
</body>
</html>