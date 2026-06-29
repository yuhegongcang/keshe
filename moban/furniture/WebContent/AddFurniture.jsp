<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上架新家具 - 后台管理</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
    <jsp:include page="nav.jsp" />

    <div class="container" style="margin-top: 30px;">
        <div class="panel panel-primary" style="max-width: 600px; margin: 0 auto;">
            <div class="panel-heading">
                <h3 class="panel-title text-center" style="font-weight: bold;">📦 上架新家具</h3>
            </div>
            <div class="panel-body" style="padding: 30px;">
                <form action="UploadServlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>家具名称</label>
                        <input type="text" class="form-control" name="fname" placeholder="例如：北欧极简布艺沙发" required>
                    </div>
                    <div class="form-group">
                        <label>所属分类</label>
                        <select class="form-control" name="fcategory">
                            <option value="客厅">客厅</option>
                            <option value="卧室">卧室</option>
                            <option value="书房">书房</option>
                            <option value="餐厅">餐厅</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>销售价格 (元)</label>
                        <input type="number" class="form-control" name="fprice" required>
                    </div>
                    <div class="form-group">
                        <label>主要材质</label>
                        <input type="text" class="form-control" name="fmaterial" placeholder="例如：实木、真皮" required>
                    </div>
                    <div class="form-group">
                        <label>商品描述</label>
                        <textarea class="form-control" name="fdesc" rows="3" required></textarea>
                    </div>
                    <div class="form-group" style="background: #e9ecef; padding: 15px; border-radius: 5px;">
                        <label style="color: #d9534f;">📸 上传商品图片 (必填)</label>
                        <input type="file" name="fimage" accept="image/*" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-block btn-lg">确认上架</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>