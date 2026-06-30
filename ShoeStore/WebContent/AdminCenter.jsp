<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 安全检查：防止普通用户或者没登录的人直接偷瞄这个页面
    String currentUser = (String) request.getSession().getAttribute("currentUser");
    if (currentUser == null || !"admin".equals(currentUser)) {
        response.sendRedirect("Login.jsp"); // 如果不是管理员，强行踢回登录页
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员个人中心</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body style="background-color: #f5f5f5;">
<div class="container" style="margin-top: 40px;">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">🛡️ 大科手办商城 - 管理员后台个人中心</h3>
        </div>
        <div class="panel-body">
            <h4>欢迎您，最高管理员：<span class="label label-danger"><%= currentUser %></span></h4>
            <hr>
            
            <div class="row">
                <div class="col-md-4">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">📊 核心管理功能</a>
                        
                        <a href="AddShoe.jsp" class="list-group-item list-group-item-success" style="font-weight: bold;">
                            ➕ 发布新手办商品
                        </a>
                        
                        <a href="Index.jsp" class="list-group-item">🏪 返回商城首页</a>
                        <a href="LogoutServlet" class="list-group-item list-group-item-danger">🚪 退出管理员账号</a>
                    </div>
                </div>
                
                <div class="col-md-8">
                    <div class="jumbotron">
                        <h2>后台操作指引</h2>
                        <p>点击左侧的“发布新手办商品”，即可进入专属表单页面。上传的图片将会自动保存在本地服务器的 img 目录中，并自动关联 Navicat 数据库。</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>