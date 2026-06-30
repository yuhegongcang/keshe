<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.UserInfoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手办商城 - 个人中心</title>
</head>
<body style="margin: 0; font-family: '微软雅黑', sans-serif; text-align: center;">

    <jsp:include page="Link.jsp" />

    <h2 style="margin-top: 30px;">👤 个人中心</h2>

    <%
        UserInfoBean uiBean = (UserInfoBean) request.getAttribute("uiBean");
        if (uiBean != null && uiBean.getMsg() != null && !uiBean.getMsg().isEmpty()) {
    %>
        <div style="color: #27ae60; font-weight: bold; margin-bottom: 15px;"><%= uiBean.getMsg() %></div>
    <%  } %>

    <div style="width: 400px; margin: 20px auto; border: 1px solid #ddd; padding: 30px; text-align: left; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
        <form action="UserInfoServlet?type=edit" method="post">
            <p style="color: #7f8c8d;"><strong>用户名：</strong> <%= uiBean != null ? uiBean.getUsername() : "" %> （不可修改）</p>
            <p style="color: #7f8c8d;"><strong>性 别：</strong> <%= uiBean != null ? uiBean.getSex() : "" %> （不可修改）</p>
            <hr style="border: 0; border-top: 1px dashed #eee; margin: 20px 0;">
            <p><strong>修改密码：</strong> <input type="text" name="password" value="<%= uiBean != null ? uiBean.getPassword() : "" %>" required></p>
            <p><strong>修改邮箱：</strong> <input type="email" name="email" value="<%= uiBean != null ? uiBean.getEmail() : "" %>" required></p>
            
            <div style="text-align: center; margin-top: 30px;">
                <button type="submit" style="background:#3498db; color:white; border:none; padding:10px 40px; cursor:pointer; font-size: 16px; border-radius: 3px;">保存修改</button>
            </div>
        </form>
    </div>

</body>
</html>