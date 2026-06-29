<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.RegistBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>用户注册</title>
    <style>.row{margin:20px;}</style>
</head>
<body>
<jsp:include page="Link.jsp"></jsp:include>
<%
    RegistBean bean = (RegistBean) request.getAttribute("registBean");
    String msg = "";
    if(bean != null && !bean.getMsg().isEmpty()){
        msg = bean.getMsg();
    }
%>
<div class="container">
    <div class="row thumbnail col-md-8 col-md-offset-2">
        <%-- 移除废弃<center>，使用bootstrap text-center居中 --%>
        <% if(!msg.isEmpty()){ %>
            <p class="text-center text-danger"><%= msg %></p >
        <% } %>
        <h2 class="text-center">新用户注册</h2>
        <form class="form-horizontal" action="RegistServlet" method="post">
            <div class="form-group">
                <label class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="username" placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">确认密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name="password2" placeholder="请再次输入密码">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">性别</label>
                <div class="col-sm-8">
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="男" checked> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="女"> 女
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">邮箱</label>
                <div class="col-sm-8">
                    <input type="email" class="form-control" name="email" placeholder="请输入邮箱">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-5">
                    <button type="submit" class="btn btn-primary btn-block">立即注册</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>