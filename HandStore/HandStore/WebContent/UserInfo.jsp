<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.UserInfoBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>个人中心</title>
</head>
<body>
<jsp:include page="Link.jsp"></jsp:include>
<%
    UserInfoBean uiBean = (UserInfoBean) request.getAttribute("uibean");
    String pwd = "", mail = "", msg = "";
    if(uiBean != null){
        pwd = uiBean.getPwdStr();
        mail = uiBean.getMailAddrStr();
        msg = uiBean.getMsg();
    }
%>
<div class="container">
    <div class="col-md-6 col-md-offset-3 thumbnail">
        <%-- 删掉过时<center>，用text-center实现居中 --%>
   <% if(msg != null && !msg.isEmpty()){ %>
    <p class="text-center text-danger"><%= msg %></p >
<% } %>
        <h3 class="text-center">修改个人信息</h3>
        <form action="UserInfoServlet" method="post" class="form-horizontal">
            <input type="hidden" name="type" value="edit">
            <div class="form-group">
                <label class="col-sm-3 control-label">新密码</label>
                <div class="col-sm-8">
                    <input value="<%=pwd%>" type="text" name="password" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">确认密码</label>
                <div class="col-sm-8">
                    <input value="<%=pwd%>" type="text" name="password2" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">邮箱</label>
                <div class="col-sm-8">
                    <input value="<%=mail%>" type="text" name="email" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-5">
                    <button type="submit" class="btn btn-info btn-block">保存修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>