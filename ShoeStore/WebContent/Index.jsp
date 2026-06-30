<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ShoeBean" %>
<%@ page import="db.Shoe" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 首页</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        .shoe-card { border: 1px solid #ddd; padding: 15px; margin: 10px; width: 250px; display: inline-block; text-align: left; box-shadow: 2px 2px 5px #eee; vertical-align: top; }
        .shoe-price { color: #e74c3c; font-size: 18px; font-weight: bold; }
    </style>
</head>
<body style="margin: 0; font-family: '微软雅黑', sans-serif; text-align: center;">

    <jsp:include page="Link.jsp" />

    <% 
        // 【优化】在顶部先获取一次登录状态，供全局使用
        String currentUser = (String) request.getSession().getAttribute("currentUser"); 
    %>

    <div class="container" style="margin-top: 30px;">
        <h3 style="color: #e1251b; font-weight: bold;">🔥 热门手办列表</h3>

        <% if ("admin".equals(currentUser)) { %>
            <div style="margin-bottom: 20px; text-align: right;">
                <a href="AdminCenter.jsp" class="btn btn-info btn-lg">⚙️ 进入管理员个人中心</a>
            </div>
        <% } %>
        
        <div class="row">
        <%
            // 1. 尝试接收 SearchServlet 传来的搜索结果
            ArrayList<ShoeBean> arr = (ArrayList<ShoeBean>) request.getAttribute("arr");
            
            if (arr == null) {
                Shoe shoeDb = new Shoe();
                arr = shoeDb.getListBeanByType("class", ""); 
            }

            if (arr != null && arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    ShoeBean shoe = arr.get(i);
        %>
        
        <div class="col-md-4" style="margin-bottom: 20px;">
            <div class="thumbnail" style="text-align: center; padding: 15px; box-shadow: 2px 2px 5px #eee;">
                <img src="<%= shoe.getImage() %>" alt="手办图片" style="height: 200px; object-fit: cover;">
                
                <div class="caption">
                    <h3 style="font-size: 16px; font-weight: bold;"><%= shoe.getShoeName() %></h3>
                    <p style="color: #666;">品牌: <%= shoe.getBrand() %> | 大小: <%= shoe.getSize() %></p>
                    <p class="shoe-price">￥<%= shoe.getPrice() %></p>
                    <p>
                        <a href="ShoeServlet?type=info&id=<%= shoe.getId() %>" class="btn btn-default">查看详情</a>
                        <a href="CartServlet?type=add&id=<%= shoe.getId() %>" class="btn btn-primary">加入购物车</a>
                        
                        <% if ("admin".equals(currentUser)) { %>
                            <a href="DeleteShoeServlet?id=<%= shoe.getId() %>" class="btn btn-danger" style="margin-left: 5px;">🗑️ 删除</a>
                        <% } %>
                    </p>
                </div>
            </div>
        </div>

        <%
                } // for 循环结束
            } else {
        %>
            <p style="color: red; width: 100%; text-align: center;">抱歉，目前还没有上架任何手办或者没有搜到相关商品。</p>
        <%  } %>
        </div>
    </div>
</body>
</html>