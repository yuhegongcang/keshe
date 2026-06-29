<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="welcome.jsp">
    			<span class="glyphicon glyphicon-bed" aria-hidden="true"></span> 现代家具商城
			</a>
        </div>
        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-left" action="IndexServlet" method="get">
                <div class="form-group">
                    <input type="text" class="form-control" name="keyword" placeholder="搜寻梳化、床褥..." style="width: 250px;">
                </div>
                <button type="submit" class="btn btn-warning">搜寻</button>
            </form>
            
            <ul class="nav navbar-nav navbar-right">
                <%
                    String username = (String) session.getAttribute("username");
                    if (username == null || username.isEmpty()) {
                %>
                        <li><a href="Login.jsp">登入 / 建立帐户</a></li>
                <%  } else {  %>
                        <li><a href="UserCenter.jsp">👤 个人中心</a></li>
                        <li><a href="CartServlet">🛒 购物车</a></li>
                        <li><a href="LogoutServlet">登出</a></li>
                <%  }  %>
            </ul>
        </div>
    </div>
</nav>