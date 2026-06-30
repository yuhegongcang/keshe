<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理 - 订单发货中心</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="AdminLink.jsp" />

<div class="container" style="margin-top: 30px;">
    <h3 class="text-center" style="margin-bottom: 20px;">用户订单管理中心</h3>
    
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-striped table-bordered table-hover text-center">
                <tr class="success">
                    <th class="text-center">下单用户</th>
                    <th class="text-center">订单编号</th>
                    <th class="text-center">包含商品总数</th>
                    <th class="text-center">订单总金额</th>
                    <th class="text-center">当前状态</th>
                    <th class="text-center">发货操作</th>
                </tr>
                <%
                    ArrayList<OrderBean> arr = (ArrayList<OrderBean>) request.getAttribute("arr");
                    if (arr != null && !arr.isEmpty()) {
                        for (OrderBean item : arr) {
                %>
                <tr>
                    <td style="line-height: 35px;"><b><%=item.getUsername()%></b></td>
                    <td style="line-height: 35px; color: #888;"><%=item.getOrderNo()%></td>
                    <td style="line-height: 35px;"><%=item.getAmount()%> 件</td>
                    <td style="line-height: 35px; color: red; font-weight: bold;">¥ <%=item.getSumPrice()%></td>
                    
                    <td style="line-height: 35px;">
                        <% if("已支付".equals(item.getStatus())) { %>
                            <span class="label label-warning">待发货 (<%=item.getStatus()%>)</span>
                        <% } else if("已发货".equals(item.getStatus())) { %>
                            <span class="label label-success">运输中 (<%=item.getStatus()%>)</span>
                        <% } else { %>
                            <span class="label label-default"><%=item.getStatus()%></span>
                        <% } %>
                    </td>
                    
                    <td>
                        <% if("已支付".equals(item.getStatus())) { %>
                            <a href="<%=request.getContextPath()%>/AdminOrderServlet?type=deliver&orderNo=<%=item.getOrderNo()%>" 
                               class="btn btn-primary btn-sm"
                               onclick="return confirm('确认要对订单 [<%=item.getOrderNo()%>] 执行发货吗？');">
                               确认发货
                            </a>
                        <% } else { %>
                            <button class="btn btn-default btn-sm" disabled>已处理</button>
                        <% } %>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-muted" style="padding: 40px;">当前系统暂无任何订单记录。</td>
                </tr>
                <%  } %>
            </table>
        </div>
    </div>
</div>

</body>
</html>