<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse" style="border-radius: 0;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/admin/AdminIndex.jsp">
        <span class="glyphicon glyphicon-send"></span> 大科商城后台管理系统
      </a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="<%=request.getContextPath()%>/admin/AddFood.jsp">新食品上架</a></li>
      <li><a href="<%=request.getContextPath()%>/AdminGoodsServlet?type=list">商品库存管理</a></li>
      <li><a href="<%=request.getContextPath()%>/AdminOrderServlet?type=list">订单发货管理</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="<%=request.getContextPath()%>/Index.jsp"><span class="glyphicon glyphicon-home"></span> 返回前台商城</a></li>
    </ul>
  </div>
</nav>