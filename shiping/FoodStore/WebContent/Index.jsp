<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.GoodsBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>大科食品商城 - 首页</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">

</head>
<body>

<jsp:include page="Link.jsp" />

<div class="container">
    <div class="row">
        <%
            // 获取检索返回的商品列表
            ArrayList<GoodsBean> arr = (ArrayList<GoodsBean>) request.getAttribute("arr");
            if (arr != null && !arr.isEmpty()) {
                for (GoodsBean goodsBean : arr) {
        %>
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail text-center" style="padding: 20px;">
                    <div class="caption">
                        <h4><%=goodsBean.getFoodName()%></h4>
                        <p class="text-muted"><%=goodsBean.getProducer()%></p>
                        <h4 class="text-danger">¥ <%=goodsBean.getPrice()%></h4>
                        <p>
                            <a href="GoodsServlet?type=info&id=<%=goodsBean.getId()%>" class="btn btn-primary" role="button">详情</a>
                            <a href="CartServlet?type=addFromIndex&id=<%=goodsBean.getId()%>&foodName=<%=goodsBean.getFoodName()%>&price=<%=goodsBean.getPrice()%>" class="btn btn-info" role="button">加入购物车</a>
                        </p>
                    </div>
                </div>
            </div>
        <% 
                }
            } else {
        %>
            <div class="col-sm-12 text-center" style="margin-top: 50px;">
                <h3 class="text-muted">抱歉，没有找到相关食品。</h3>
            </div>
        <%  } %>
    </div>
</div>
</body>
</html>