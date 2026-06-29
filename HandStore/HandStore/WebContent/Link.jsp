<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
%>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="Index.jsp">潮玩手办商城</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="Index.jsp">主页</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">全部分类<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Search?type=class&value=动漫手办">动漫手办</a></li>
                        <li><a href="Search?type=class&value=机甲模型">机甲模型</a></li>
                        <li><a href="Search?type=class&value=盲盒">盲盒</a></li>
                        <li><a href="Search?type=class&value=高达拼装">高达拼装</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" action="Search" method="post">
                <input type="hidden" name="type" value="handName">
                <input type="text" class="form-control" name="value" placeholder="搜索手办名称">
                <button type="submit" class="btn btn-default">检索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <% if(username == null || username.isEmpty()){ %>
                    <li><a href="Login.jsp">登录</a></li>
                    <li><a href="Register.jsp">注册</a></li>
                <% }else{ %>
                    <li><a>欢迎<%=username%></a></li>
                    <li><a href="UserInfoServlet">个人中心</a></li>
                    <li><a href="cart?type=init">购物车</a></li>
                    <li><a href="OrderServlet?type=init">订单</a></li>
                    <li><a href="LogoutServlet">退出</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>