<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.OrderBean"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单 - 家具系统</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
    <div class="container" style="margin-top: 50px;">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title" style="font-size: 20px; font-weight: bold;">📦 我的历史订单</h3>
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-striped text-center">
                    <thead>
                        <tr class="info">
                            <th class="text-center">订单编号</th>
                            <th class="text-center">商品名称</th>
                            <th class="text-center">购买单价</th>
                            <th class="text-center">数量</th>
                            <th class="text-center">实付金额</th>
                            <th class="text-center">状态</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<OrderBean> orderList = (ArrayList<OrderBean>) request.getAttribute("orderList");
                            
                            if (orderList != null && !orderList.isEmpty()) {
                                for (OrderBean order : orderList) {
                        %>
                                <tr>
                                    <td style="color: #666; font-size: 12px; vertical-align: middle;"><%= order.getOrderNo() %></td>
                                    <td style="font-weight: bold; vertical-align: middle;"><%= order.getProductName() %></td>
                                    <td style="vertical-align: middle;">¥<%= order.getPrice() %></td>
                                    <td style="vertical-align: middle;"><%= order.getAmount() %></td>
                                    <td style="color: red; font-weight: bold; vertical-align: middle;">¥<%= order.getSumPrice() %></td>
                                    <td style="vertical-align: middle;">
                                        <span class="label label-success"><%= order.getStatus() %></span>
                                    </td>
                                </tr>
                        <%
                                }
                            } else {
                        %>
                                <tr>
                                    <td colspan="6" style="padding: 40px; color: #999;">您还没有下过单，快去商城逛逛吧！</td>
                                </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="panel-footer text-right">
                <a href="IndexServlet" class="btn btn-primary">返回商城首页</a>
            </div>
        </div>
    </div>
</body>
</html>