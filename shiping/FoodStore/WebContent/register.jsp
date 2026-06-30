<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.RegistBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>食品商城 - 用户注册</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">

</head>
<body>
<%
    // 获取后台传递过来的提示信息
    RegistBean bean = (RegistBean) request.getAttribute("registBean");
    String msg = (bean != null && !bean.getMsg().isEmpty()) ? bean.getMsg() : "";
%>

<div class="container" style="margin-top: 50px;">
    <h2 class="text-center">大科食品商城 - 新用户注册</h2>
    
    <% if (!msg.isEmpty()) { %>
        <h4 class="text-center text-danger"><%=msg%></h4>
    <% } %>

    <div class="row">
        <div class="col-sm-offset-3 col-sm-6 thumbnail" style="padding: 30px;">
            <form class="form-horizontal" action="RegistServlet" method="post">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="username" placeholder="请输入中英文用户名" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password" placeholder="6-10位字母或数字" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">确认密码</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password_confirm" placeholder="请再次输入密码" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">性别</label>
                    <div class="col-sm-8">
                        <label class="radio-inline"><input type="radio" name="sex" value="男" checked> 男</label>
                        <label class="radio-inline"><input type="radio" name="sex" value="女"> 女</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">邮箱</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control" name="email" placeholder="如：user@dlust.edu.cn" required>
                    </div>
                </div>
                <div class="form-group text-center">
    <button type="submit" class="btn btn-success" style="width: 100px;">立即注册</button>
    <button type="reset" class="btn btn-default" style="width: 100px; margin-left: 10px;">重置</button>
    <a href="login.jsp" class="btn btn-info" style="width: 100px; margin-left: 10px;">返回登录</a>
</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>