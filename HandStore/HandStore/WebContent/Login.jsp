<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.LoginBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>用户登录</title>
</head>
<body>
    <jsp:include page="Link.jsp"></jsp:include>
    <%
        // 直接接收 Servlet 传过来的 loginMsg 字符串
        String msg = (String) request.getAttribute("loginMsg");
        if (msg == null) {
            msg = ""; // 如果没有错误信息，就让它保持为空
        }
    %>

    <div class="container">
        <div class="row col-md-6 col-md-offset-3 thumbnail">
            <%-- 错误提示文字 --%>
            <% if(!msg.isEmpty()){ %>
                <p class="text-center text-danger"><%= msg %></p >
            <% } %>

            <h2 class="text-center">用户登录</h2>
            <%-- 表单提交，method必须是post --%>
            <form action="login" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-8">
                        <input type="text" name="username" class="form-control" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码</label>
                    <div class="col-sm-8">
                        <input type="password" name="password" class="form-control" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-5">
                        <button type="submit" class="btn btn-success btn-block">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>