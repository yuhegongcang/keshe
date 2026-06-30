<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布新手办 - 管理员后台</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="margin-top: 50px; max-width: 600px;">
    <h2 class="text-center" style="color: #d35400; font-weight: bold;">🚀 发布新手办</h2>
    <hr>
    
    <form action="AddShoeServlet" method="post" enctype="multipart/form-data">
        
        <div class="form-group">
            <label>手办名称 (如: 初音未来):</label>
            <input type="text" class="form-control" name="shoeName" required>
        </div>
        
        <div class="form-group">
            <label>品牌 :</label>
            <input type="text" class="form-control" name="brand" required>
        </div>
        
        <div class="form-group">
            <label>大小:</label>
            <input type="text" class="form-control" name="size" required>
        </div>
        
        <div class="form-group">
            <label>价格 (￥):</label>
            <input type="number" step="0.01" class="form-control" name="price" required>
        </div>
        
        <div class="form-group">
            <label>商品详细介绍:</label>
            <textarea class="form-control" name="introduce" rows="3" required></textarea>
        </div>
        
        <div class="form-group" style="background: #f9f9f9; padding: 15px; border-radius: 5px;">
            <label style="color: red;">📸 上传手办主图:</label>
            <input type="file" name="shoeImg" accept="image/*" required>
            <p class="help-block">系统会自动将图片保存并关联到数据库	</p>
        </div>
        
        <button type="submit" class="btn btn-warning btn-lg btn-block">确认发布商品</button>
        <a href="Index.jsp" class="btn btn-default btn-block" style="margin-top: 10px;">返回首页</a>
    </form>
</div>
</body>
</html>