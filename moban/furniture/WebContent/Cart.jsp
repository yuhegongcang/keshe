<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.CartBean"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的购物车 - 家具系统</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
    <div class="container" style="margin-top: 50px;">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title" style="font-size: 20px; font-weight: bold;">🛒 我的购物车</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>商品名称</th>
                            <th>单价</th>
                            <th>数量</th>
                            <th>小计</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<CartBean> cartList = (ArrayList<CartBean>) request.getAttribute("cartList");
                            int totalPrice = 0; // 总计金额
                            
                            if (cartList != null && !cartList.isEmpty()) {
                                for (CartBean item : cartList) {
                                    totalPrice += item.getSubTotal();
                        %>
                                <tr>
                                    <td style="font-weight: bold;"><%= item.getFurnitureName() %></td>
                                    <td>¥<%= item.getPrice() %></td>
                                    <td style="display:flex; justify-content:flex-start; align-items:center;">
    <a href="CartServlet?type=sub&cid=<%= item.getCartId() %>" class="btn btn-default btn-sm">-</a>
    <input type="text" value="<%= item.getQuantity() %>" style="width: 40px; text-align: center; margin: 0 5px;" readonly>
    <a href="CartServlet?type=addQty&cid=<%= item.getCartId() %>" class="btn btn-default btn-sm">+</a>
</td>
                                    <td style="color: red; font-weight: bold;">¥<%= item.getSubTotal() %></td>
                                    <td>
                                        <a href="CartServlet?type=del&cid=<%= item.getCartId() %>" class="btn btn-danger btn-sm">删除</a>
                                    </td>
                                </tr>
                        <%
                                }
                            } else {
                        %>
                                <tr>
                                    <td colspan="5" class="text-center" style="padding: 30px; color: #999;">购物车空空如也，快去挑点喜欢的家具吧！</td>
                                </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <div class="text-right" style="font-size: 20px; margin-top: 20px;">
                    总计金额：<span style="color: red; font-size: 28px; font-weight: bold;">¥<%= totalPrice %></span>
                </div>
            </div>
            <div class="panel-footer text-right">
                <a href="IndexServlet" class="btn btn-default">继续购物</a>
                <a href="OrderServlet?type=add" class="btn btn-success" <%= totalPrice==0?"disabled":"" %>>去结算生成订单</a>
            </div>
        </div>
    </div>
</body>
</html>