<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.GoodsBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>大科食品商城 - 食品详情</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">

</head>
<body>

<!-- 引入顶部公共导航栏 -->
<jsp:include page="Link.jsp" />

<%
    // 从 Request 中取出 Servlet 放入的食品实体对象
    GoodsBean goodsBean = (GoodsBean) request.getAttribute("goodsBean");
    if (goodsBean != null) {
%>
<div class="container" style="margin-top: 40px;">
    <div class="row well">
        <!-- 左侧：由于没有真实的图片，这里用占位色块模拟食品图片展示区 -->
        <div class="col-sm-5 text-center">
            <div style="width: 100%; height: 300px; background-color: #ecf0f1; border-radius: 8px; line-height: 300px; color: #95a5a6; font-size: 24px;">
                商品图片暂无
            </div>
        </div>
        
        <!-- 右侧：食品详细信息与操作区 -->
        <div class="col-sm-7">
            <h2><%=goodsBean.getFoodName()%></h2>
            <hr>
            <p style="font-size: 16px;"><strong>所属分类：</strong> <%=goodsBean.getType()%></p>
            <p style="font-size: 16px;"><strong>生产厂家：</strong> <%=goodsBean.getProducer()%></p>
            <p style="font-size: 16px;"><strong>保质期：</strong> <%=goodsBean.getShelfLife()%> 天</p>
            <p style="font-size: 16px;"><strong>当前库存：</strong> <%=goodsBean.getStock()%> 件</p>
            <h3 style="color: #e74c3c; margin-top: 20px;">¥ <%=goodsBean.getPrice()%></h3>
            
            <div style="margin-top: 30px;">
                <!-- 详情页加入购物车 -->
                <a class="btn btn-info btn-lg" role="button" 
                   href="CartServlet?type=addFromIndex&id=<%=goodsBean.getId()%>&foodName=<%=goodsBean.getFoodName()%>&price=<%=goodsBean.getPrice()%>">
                   加入购物车
                </a>
            </div>
        </div>
    </div>
    
    <!-- 底部：详细介绍 -->
    <div class="row" style="margin-top: 30px;">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">商品介绍</h3>
                </div>
                <div class="panel-body" style="line-height: 1.8; font-size: 15px; padding: 20px;">
                    <%=goodsBean.getIntroduce()%>
                </div>
            </div>
        </div>
    </div>
</div>
<% 
    } else { 
%>
<!-- 数据获取失败的容错显示 -->
<div class="container text-center" style="margin-top: 80px;">
    <h3 class="text-danger">抱歉，未找到该食品的详细信息。</h3>
    <a href="Index.jsp" class="btn btn-primary" style="margin-top: 20px;">返回首页</a>
</div>
<% 
    } 
%>

</body>
</html>