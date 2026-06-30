<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>大科食品商城 - 个人中心</title>
       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="Link.jsp" />
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-sm-offset-3 col-sm-6 panel panel-info" style="padding: 0;">
            <div class="panel-heading"><h3 class="panel-title text-center">我的个人资料</h3></div>
            <div class="panel-body text-center" style="padding: 40px;">
            <hr>
<h4 class="text-left">修改个人资料</h4>
<form class="form-horizontal" action="${pageContext.request.contextPath}/UserInfoServlet?type=edit" method="post">
    <div class="form-group">
        <label class="col-sm-3 control-label">新密码</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" name="password" placeholder="6-10位" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">确认密码</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" name="password2" placeholder="再次输入" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">新邮箱</label>
        <div class="col-sm-8">
            <input type="email" class="form-control" name="email" value="${uibean.mailAddrStr}" required>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">确认修改</button>
</form>
                <img src="https://api.dicebear.com/7.x/adventurer/svg?seed=<%=session.getAttribute("username")%>" 
                     alt="avatar" style="width: 100px; height: 100px; border-radius: 50%; margin-bottom: 20px;">
                <h4>当前登录账号：<b class="text-danger"><%=session.getAttribute("username")%></b></h4>
                <p class="text-muted" style="margin-top:20px;">
                </p>
                <div style="margin-top: 30px;">
                    <a href="CartServlet?type=init" class="btn btn-primary">直达我的购物车</a>
                    <a href="OrderServlet?type=init" class="btn btn-success">查看我的历史订单</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>