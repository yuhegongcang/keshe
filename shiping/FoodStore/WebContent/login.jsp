<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>食品商城 - 用户登录</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">

</head>
<body>
<%
    // 获取后台 Servlet 传过来的登录实体数据
    LoginBean bean = (LoginBean) request.getAttribute("loginBean");
    String msg = (bean != null && bean.getMsg() != null) ? bean.getMsg() : "";
    String username = (bean != null && bean.getNameStr() != null) ? bean.getNameStr() : "";
%>

<div class="container" style="margin-top: 80px;">
    <h2 class="text-center">大科食品商城 - 用户登录</h2>
    
    <% if (!msg.isEmpty()) { %>
        <h4 class="text-center text-danger"><%=msg%></h4>
    <% } %>

    <div class="row">
        <div class="col-sm-offset-4 col-sm-4 thumbnail" style="padding: 30px;">
            <form class="form-horizontal" action="LoginServlet" method="post">
                <div class="form-group">
                    <div class="col-sm-12">
                        <label>用户名</label>
                        <input type="text" class="form-control" name="username" value="<%=username%>" placeholder="请输入用户名" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <label>密码</label>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码" required>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 20px;">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-success btn-block">安全登录</button>
                    </div>
                </div>
                <div class="text-center" style="margin-top: 15px;">
    <a href="register.jsp" class="btn btn-link">还没有账号？立即注册</a>
    <a href="SearchServlet?type=class&value=" class="btn btn-default btn-sm" style="margin-left:10px;">先去逛逛 (游客模式)</a>
</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>