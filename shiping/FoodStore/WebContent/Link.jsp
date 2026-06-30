<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/SearchServlet?type=class&value=">大科食品商城</a>
    </div>

<ul class="nav navbar-nav">
      <li><a href="SearchServlet?type=class&value=all">全部商品</a></li>
      <li><a href="SearchServlet?type=class&value=<%=java.net.URLEncoder.encode("生鲜水果", "UTF-8")%>">生鲜水果</a></li>
      <li><a href="SearchServlet?type=class&value=<%=java.net.URLEncoder.encode("休闲零食", "UTF-8")%>">休闲零食</a></li>
      <li><a href="SearchServlet?type=class&value=<%=java.net.URLEncoder.encode("速食冲饮", "UTF-8")%>">速食冲饮</a></li>
    </ul>
    <form class="navbar-form navbar-right" method="post" action="SearchServlet?type=foodName">
      <div class="form-group">
        <input type="text" name="value" class="form-control" placeholder="搜索食品名称...">
      </div>
      <button type="submit" class="btn btn-default">检索</button>
    </form>
    
    <ul class="nav navbar-nav navbar-right">
      <% 
         String username = (String) session.getAttribute("username");
         if (username != null && !username.isEmpty()) { 
      %>
         <li><a>欢迎，<%=username%></a></li>
         <li><a href="UserInfo.jsp">个人中心</a></li>
         <li><a href="CartServlet?type=init">购物车</a></li>
         <li><a href="OrderServlet?type=init">我的订单</a></li>
         
         <% if("admin".equals(username)) { %>
            <li>
                <a href="admin/AdminIndex.jsp" style="color: #f39c12; font-weight: bold;">
                    <span class="glyphicon glyphicon-cog"></span> 后台管理
                </a>
            </li>
         <% } %>
         
         <li><a href="LogoutServlet">退出</a></li>
      <% } else { %>
         <li><a href="login.jsp">登录</a></li>
         <li><a href="register.jsp">注册</a></li>
      <% } %>
    </ul>
  </div>
</nav>