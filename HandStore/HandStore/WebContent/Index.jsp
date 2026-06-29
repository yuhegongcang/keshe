<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.GoodsBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>潮玩手办商城</title>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Link.jsp"></jsp:include>

<%
    ArrayList<GoodsBean> goodsList = (ArrayList<GoodsBean>) request.getAttribute("goodsList");
%>

<!-- 商品区域 -->
<div align="center" style="margin-top:50px;">
<%
    if(goodsList == null || goodsList.isEmpty()){
%>
    <p>暂无商品数据</p >
<%
    }else{
        for(GoodsBean g : goodsList){
%>
    <div style="margin:8px 0;">
        <%=g.getHandName()%> 价格：<%=g.getPrice()%>
    <!-- 修改后正确代码 -->
<a href="cart?gid=${g.goodsId}">加入购物车</a>
    </div>
<%
        }
    }
%>
</div>

<script>
function submitSearch(){
	let key = document.querySelector("input[name='keyword']").value;
	window.location.href = "Search?type=class&value=" + encodeURIComponent(keyword);

}
</script>
</body>
</html>