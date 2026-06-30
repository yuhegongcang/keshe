<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理 - 新食品上架</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="AdminLink.jsp" />

<div class="container" style="margin-top: 30px;">
    <h3 class="text-center">录入新食品信息</h3>
    <hr>
    
    <% 
        String msg = (String) request.getAttribute("msg");
        if (msg != null) { 
    %>
        <div class="alert alert-info text-center"><%=msg%></div>
    <% } %>


    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <form class="form-horizontal" action="<%=request.getContextPath()%>/AdminGoodsServlet?type=add" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label">商品ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="id" placeholder="例如：F001" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">食品名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="foodName" placeholder="输入商品名称" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">价格(元)</label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" name="price" min="0" required>
                    </div>
                    <label class="col-sm-2 control-label">库存数量</label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" name="stock" min="0" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">食品分类</label>
<div class="col-sm-4">
    <select class="form-control" name="goodsType">
        <option value="休闲零食">休闲零食</option>
        <option value="生鲜水果">生鲜水果</option>
        <option value="速食冲饮">速食冲饮</option>
    </select>
</div>
                    <label class="col-sm-2 control-label">保质期(天)</label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" name="shelfLife" min="1" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">生产厂家</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="producer" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">详细介绍</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="introduce" rows="4" placeholder="食品配料、口味描述等..." required></textarea>
                    </div>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-success" style="width: 150px;">确认上架</button>
                    <button type="reset" class="btn btn-default" style="width: 150px; margin-left: 20px;">清空重填</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>