<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理 - 工作台首页</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="AdminLink.jsp" />

<div class="container" style="margin-top: 50px;">
    <div class="jumbotron text-center">
        <h2>欢迎来到管理员工作台</h2>
        <p>请选择您要进行的操作模块</p>
    </div>
    <div class="row text-center" style="margin-top: 40px;">
        <div class="col-sm-4">
            <a href="AddFood.jsp" class="btn btn-success btn-lg btn-block" style="padding: 40px 0;">
                <span class="glyphicon glyphicon-plus" style="font-size: 2em; display:block; margin-bottom:10px;"></span>
                上架新食品
            </a>
        </div>
        <div class="col-sm-4">
            <a href="<%=request.getContextPath()%>/AdminGoodsServlet?type=list" class="btn btn-info btn-lg btn-block" style="padding: 40px 0;">
                <span class="glyphicon glyphicon-list-alt" style="font-size: 2em; display:block; margin-bottom:10px;"></span>
                库存管理与下架
            </a>
        </div>
        <div class="col-sm-4">
            <a href="<%=request.getContextPath()%>/AdminOrderServlet?type=list" class="btn btn-warning btn-lg btn-block" style="padding: 40px 0;">
                <span class="glyphicon glyphicon-send" style="font-size: 2em; display:block; margin-bottom:10px;"></span>
                订单处理与发货
            </a>
        </div>
    </div>
</div>
</body>
</html>