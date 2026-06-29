<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.FurnitureBean"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>家具销售系统 - 首页</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">

    <%-- 核心：引入你刚才写好的带有搜索框的导航栏 --%>
    <jsp:include page="nav.jsp" />

    <div class="container">
        <h2 class="text-center" style="margin-top: 20px; font-weight: bold;">最新家具上架</h2>
        <hr/>
        <div class="row">
            <%
                // 接收从 IndexServlet 传过来的家具列表（包含搜索结果）
                ArrayList<FurnitureBean> list = (ArrayList<FurnitureBean>) request.getAttribute("fList");
                
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        FurnitureBean fb = list.get(i);
            %>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail" style="border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); margin-bottom: 20px;">
    <%-- 利用 ID 动态拼接图片路径，比如 ID 是 1，路径就是 img/1.jpg --%>
					<img src="img/<%= fb.getImgName() %>" style="width: 100%; height: 200px; object-fit: contain; background-color: #f8f9fa; border-radius: 10px 10px 0 0;">
    			<div class="caption">
                        <h3 style="font-weight: bold; color: #333;"><%= fb.getName() %></h3>
                        <p><strong>分类：</strong> <span class="label label-info"><%= fb.getCategory() %></span></p>
                        <p><strong>材质：</strong> <%= fb.getMaterial() %></p>
                        <p><strong>价格：</strong> <span style="color:#d9534f; font-size:22px; font-weight: bold;">¥<%= fb.getPrice() %></span></p>
                        <p style="color: #666; height: 60px; overflow: hidden;"><%= fb.getDescription() %></p>
                        <p class="text-center">
                            <a href="DetailServlet?fid=<%= fb.getId() %>" class="btn btn-primary" role="button" style="width: 45%;">查看详情</a> 
                            <a href="AddCartServlet?fid=<%= fb.getId() %>" class="btn btn-warning" role="button" style="width: 45%;">加入购物车</a>
                            <%
    // 1. 获取当前登录的账号名
    String currentUsername = (String) session.getAttribute("username");
    
    // 2. 权限判断：如果账号存在，且名字叫 "admin"（老板账号），才画出这两个按钮
    if (currentUsername != null && currentUsername.equals("admin")) { 
%>
    <!-- 下面这两个按钮，普通客户绝对看不见！ -->
    <a href="EditFurniture.jsp?id=<%= fb.getId() %>" class="btn btn-info" style="width: 45%; margin-top: 5px;">修改</a>
    <a href="DeleteFurnitureServlet?id=<%= fb.getId() %>" class="btn btn-danger" style="width: 45%; margin-top: 5px;" onclick="return confirm('警告：确定要彻底删除商品和图片吗？')">删除</a>
<%
    } 
%>
                        </p>
                    </div>
                </div>
            </div>
            <%
                    }
                } else {
            %>
                <div class="col-sm-12 text-center">
                    <h4 style="color: #999; padding: 50px;">没有找到相关的家具哦~</h4>
                </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>