<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心 - 家具商城</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<body style="background-color: #f1f3f6;">
    <div class="container" style="margin-top: 50px;">
        <div class="row">
            <div class="col-md-3">
                <div class="list-group">
                    <a href="#" class="list-group-item active">👤 我的主页</a>
                    <a href="MyOrderServlet" class="list-group-item">📦 历史订单</a>
                    <a href="CartServlet" class="list-group-item">🛒 我的购物车</a>
                    <a href="#" class="list-group-item">⚙️ 账号设置</a>
                </div>
            </div>
            
            <div class="col-md-9">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">欢迎回来，<strong><%= username %></strong>！</h3>
                    </div>
                    <div class="panel-body" style="padding: 40px;">
                        <div class="row text-center">
                            <div class="col-xs-4">
                                <h2><a href="MyOrderServlet">📄</a></h2>
                                <h4>查阅订单</h4>
                                <p class="text-muted">追踪您的物流与消费记录</p>
                            </div>
                            <div class="col-xs-4">
                                <h2><a href="CartServlet">🛍️</a></h2>
                                <h4>购物车</h4>
                                <p class="text-muted">看看还有什么没结算的好物</p>
                            </div>
                            <div class="col-xs-4">
                                <h2><a href="IndexServlet">🛋️</a></h2>
                                <h4>浏览商城</h4>
                                <p class="text-muted">发现更多现代家居灵感</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>