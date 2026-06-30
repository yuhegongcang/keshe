<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 从 Session 中获取刚才登录成功时存入的用户名
    String username = (String) session.getAttribute("username");
%>
<%@ page import="java.net.URLEncoder" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">

<nav class="navbar navbar-default" style="background-color: #cccccc; border-color: #dcdcdc; margin-bottom: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="Index.jsp" style="font-weight: bold; color: #3498db;">🏀 大科手办商城</a>
    </div>

    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="Index.jsp">首页全部</a></li>
        <li><a href="SearchServlet?type=class&value=<%= URLEncoder.encode("篮球鞋", "UTF-8") %>">比例手办</a></li>
        <li><a href="SearchServlet?type=class&value=<%= URLEncoder.encode("跑步鞋", "UTF-8") %>">黏土人</a></li>
        <li><a href="SearchServlet?type=class&value=<%= URLEncoder.encode("休闲鞋", "UTF-8") %>">盲盒</a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <% 
            // 直接使用第4行已经声明好的 username 进行判断
            if (username != null && !"".equals(username)) { 
        %>
            <li><a href="#" style="color: #27ae60;">欢迎您: <strong><%= username %></strong></a></li>
            <li><a href="UserInfoServlet">个人中心</a></li>
            <li><a href="CartServlet?type=init">🛒 我的购物车</a></li>
            <li><a href="OrderServlet?type=init">📦 我的订单</a></li>
            <li><a href="LogoutServlet" style="color: #e74c3c;">退出登录</a></li>
        <% } else { %>
            <li><a href="Login.jsp">登录</a></li>
            <li><a href="Register.jsp">注册</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
    <br><br>
    
    <form action="SearchServlet?type=shoeName" method="post" style="display:inline;">
        <input type="text" name="value" placeholder="输入手办名称 ..." size="30">
        <button type="submit">搜 索</button>
    </form>
</div>