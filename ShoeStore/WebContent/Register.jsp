<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.RegistBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 用户注册</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body>    
    <%
        RegistBean bean = (RegistBean) request.getAttribute("registBean");
        String msg = (bean != null) ? bean.getMsg() : "";
    %>
    <div style="color: red; text-align: center;"><%= msg %></div>

    <div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="text-center">新用户注册</h2>
            <form action="RegistServlet" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-9"><input type="text" name="username" class="form-control" placeholder="请输入用户名" required></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码</label>
                    <div class="col-sm-9"><input type="password" name="password" class="form-control" placeholder="请输入密码" required></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">确认密码</label>
                    <div class="col-sm-9"><input type="password" name="password" class="form-control" placeholder="请再次输入密码" required></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">性别</label>
                    <div class="col-sm-9" style="padding-top: 7px;">
                        <label class="radio-inline"><input type="radio" name="sex" value="男" checked> 男</label>
                        <label class="radio-inline"><input type="radio" name="sex" value="女"> 女</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">邮箱</label>
                    <div class="col-sm-9"><input type="email" name="email" class="form-control" placeholder="请输入邮箱" required></div>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary btn-lg" style="width: 100%;">注 册</button>
                </div>
                <div class="form-group text-center" style="margin-top: 10px;">
    <a href="Login.jsp" class="btn btn-default btn-lg" style="width: 100%;">已有账号？返回登录</a>
</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>