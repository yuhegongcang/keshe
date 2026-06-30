<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.GoodsBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理 - 商品库存管理</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"></head>
<body>

<jsp:include page="AdminLink.jsp" />

<div class="container" style="margin-top: 30px;">
    <h3 class="text-center" style="margin-bottom: 20px;">系统在售商品库存清单</h3>
    
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-striped table-bordered table-hover text-center">
                <tr class="info">
                    <th class="text-center">商品ID</th>
                    <th class="text-center">食品名称</th>
                    <th class="text-center">分类</th>
                    <th class="text-center">价格(元)</th>
                    <th class="text-center">当前库存</th>
                    <th class="text-center">生产厂家</th>
                    <th class="text-center">管理操作</th>
                </tr>
                <%
                    ArrayList<GoodsBean> arr = (ArrayList<GoodsBean>) request.getAttribute("arr");
                    if (arr != null && !arr.isEmpty()) {
                        for (GoodsBean item : arr) {
                %>
                <tr>
                    <td style="line-height: 35px;"><%=item.getId()%></td>
                    <td style="line-height: 35px;"><b><%=item.getFoodName()%></b></td>
                    <td style="line-height: 35px;"><%=item.getType()%></td>
                    <td style="line-height: 35px; color: #d35400;"><b><%=item.getPrice()%></b></td>
                    <td style="line-height: 35px;">
                        <% if(item.getStock() < 10) { %>
                            <span class="label label-danger">仅剩 <%=item.getStock()%> 件</span>
                        <% } else { %>
                            <%=item.getStock()%>
                        <% } %>
                    </td>
                    <td style="line-height: 35px;"><%=item.getProducer()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/AdminGoodsServlet?type=delete&id=<%=item.getId()%>" 
                           class="btn btn-danger btn-sm" 
                           onclick="return confirm('确定要下架并删除该商品吗？此操作不可恢复！');">
                           下架删除
                        </a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" class="text-muted" style="padding: 40px;">当前系统没有任何在售商品，请先去上架新商品。</td>
                </tr>
                <%  } %>
            </table>
        </div>
    </div>
</div>

</body>
</html>