<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.FurnitureBean" %>
<%@ page import="db.Furniture" %>
<%
    // 获取要修改的家具ID，并查出它的原有信息用来填入输入框
    String idStr = request.getParameter("id");
    if (idStr == null || idStr.isEmpty()) {
        out.print("<script>alert('参数错误'); window.history.back();</script>");
        return;
    }
    int id = Integer.parseInt(idStr);
    Furniture db = new Furniture();
    FurnitureBean fb = db.getFurnitureById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改家具信息</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
    <jsp:include page="nav.jsp" />

    <div class="container" style="margin-top: 30px;">
        <div class="panel panel-warning" style="max-width: 600px; margin: 0 auto;">
            <div class="panel-heading">
                <h3 class="panel-title text-center" style="font-weight: bold;">📝 修改家具信息</h3>
            </div>
            <div class="panel-body" style="padding: 30px;">
                <form action="UpdateFurnitureServlet" method="post" enctype="multipart/form-data">
                    <!-- 极其关键：隐藏域！用来把 ID 悄悄传给 Servlet -->
                    <input type="hidden" name="id" value="<%= fb.getId() %>">

                    <div class="form-group">
                        <label>家具名称</label>
                        <input type="text" class="form-control" name="fname" value="<%= fb.getName() %>" required>
                    </div>
                    <div class="form-group">
                        <label>所属分类</label>
                        <select class="form-control" name="fcategory">
                            <!-- 回显原来的分类 -->
                            <option value="客厅" <%= "客厅".equals(fb.getCategory())?"selected":"" %>>客厅</option>
                            <option value="卧室" <%= "卧室".equals(fb.getCategory())?"selected":"" %>>卧室</option>
                            <option value="书房" <%= "书房".equals(fb.getCategory())?"selected":"" %>>书房</option>
                            <option value="餐厅" <%= "餐厅".equals(fb.getCategory())?"selected":"" %>>餐厅</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>销售价格 (元)</label>
                        <input type="number" class="form-control" name="fprice" value="<%= fb.getPrice() %>" required>
                    </div>
                    <div class="form-group">
                        <label>主要材质</label>
                        <input type="text" class="form-control" name="fmaterial" value="<%= fb.getMaterial() %>" required>
                    </div>
                    <div class="form-group">
                        <label>商品描述</label>
                        <textarea class="form-control" name="fdesc" rows="3" required><%= fb.getDescription() %></textarea>
                    </div>
                    
                    <div class="form-group" style="background: #e9ecef; padding: 15px; border-radius: 5px;">
                        <label style="color: #31708f;">📸 更换商品图片 (如果不换，请留空即可)</label>
                        <div style="margin-bottom: 10px;">
                            <img src="img/<%= fb.getImgName() %>" style="width: 100px; height: 100px; object-fit: cover; border-radius: 5px;">
                            <span style="color: #999; margin-left: 10px;">当前主图</span>
                        </div>
                        <input type="file" name="fimage" accept="image/*">
                    </div>
                    <button type="submit" class="btn btn-warning btn-block btn-lg">保存修改</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>