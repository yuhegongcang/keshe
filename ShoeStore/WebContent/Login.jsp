<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 用户登录</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body>
    <h2 align="center">欢迎登录手办交易商城</h2>
    
    <div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-4 col-md-offset-4" style="border: 1px solid #ddd; padding: 30px; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1);">
            <h2 class="text-center">商城登录</h2>
            <br>
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" name="username" class="form-control" placeholder="请输入用户名" required>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" name="password" class="form-control" placeholder="请输入密码" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block btn-lg" style="margin-top: 20px;">登 录</button>
            </form>
            <div class="text-center" style="margin-top: 15px;">
                <a href="Register.jsp">没有账号？点击注册</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>