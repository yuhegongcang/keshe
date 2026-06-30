	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.CartBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>大科食品商城 - 我的购物车</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
<script>
    // 修改数量并计算小计
    function changeQty(id, change) {
        var input = document.getElementById("qty_" + id);
        var currentQty = parseInt(input.value);
        var newQty = currentQty + change;
        
        // 数量不能少于1
        if(newQty >= 1) {
            // 1. 更新输入框的数量
            input.value = newQty;
            
            // 2. 获取隐藏在表格里的单价
            var priceText = document.getElementById("price_" + id).innerText;
            var price = parseFloat(priceText);
            
            // 3. 计算新小计并更新页面
            var newSubtotal = price * newQty;
            document.getElementById("subtotal_" + id).innerHTML = "<b>" + newSubtotal + "</b>";
            
            // 4. 调用全局重新计算函数
            recalculateTotal();
        }
    }

    // 重新计算底部总计和总金额
    function recalculateTotal() {
        // 抓取页面上所有的数量输入框和小计粗体标签
        var allQtyInputs = document.querySelectorAll("input[id^='qty_']");
        var allSubtotals = document.querySelectorAll("td[id^='subtotal_'] b");
        
        var totalQty = 0;
        var totalPrice = 0;
        
        // 遍历累加
        for(var i = 0; i < allQtyInputs.length; i++) {
            totalQty += parseInt(allQtyInputs[i].value);
            totalPrice += parseFloat(allSubtotals[i].innerText);
        }
        
        // 更新底部的总件数和总价
        document.getElementById("final_total_qty").innerText = totalQty;
        document.getElementById("final_total_price").innerText = "¥ " + totalPrice;
    }
</script>
</head>
<body>

<jsp:include page="Link.jsp" />

<div class="container" style="margin-top: 40px;">
    <h2 class="text-center" style="margin-bottom: 30px;">我的购物车</h2>
    
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-hover table-bordered text-center">
    <tr class="info">
        <th class="text-center">食品名称</th>
        <th class="text-center">单价 (元)</th>
        <th class="text-center">数量</th>
        <th class="text-center">小计 (元)</th>
        <th class="text-center">操作</th>
    </tr>
    
    <%
        // 1. 先用 Java 取出数据，并定义好总数和总价
        ArrayList<CartBean> arr = (ArrayList<CartBean>) request.getAttribute("arr");
        int totalAmount = 0; 
        int totalPrice = 0;  

        if (arr != null && !arr.isEmpty()) {
            // 2. 开启 for 循环，每次循环都会产生一个 item 变量
            for (CartBean item : arr) {
                totalAmount += item.getAmount(); 
                totalPrice += item.getSumPrice(); 
    %>
    
    <tr>
        <td style="line-height: 35px;"><%=item.getFoodName()%></td>
        <td style="line-height: 35px;" id="price_<%=item.getId()%>"><%=item.getPrice()%></td>
        <td style="width: 150px;">
            <div class="input-group input-group-sm">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="changeQty('<%=item.getId()%>', -1)">-</button>
                </span>
                <input type="text" class="form-control text-center" id="qty_<%=item.getId()%>" value="<%=item.getAmount()%>" readonly>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="changeQty('<%=item.getId()%>', 1)">+</button>
                </span>
            </div>
        </td>
        <td style="line-height: 35px; color: red;" id="subtotal_<%=item.getId()%>"><b><%=item.getSumPrice()%></b></td>
        <td>
            <a href="CartServlet?type=del&id=<%=item.getId()%>" class="btn btn-danger btn-sm">删除</a>
        </td>
    </tr>
    
    <%
            } // 4. Java for循环的大括号在这里闭合！
        } else {
    %>
    <tr>
        <td colspan="5" class="text-muted" style="padding: 30px;">购物车空空如也，快去选购吧！</td>
    </tr>
    <%  } %>
</table>
            
<div class="well text-right">
    <h4>共计 <span class="text-danger" id="final_total_qty"><%=totalAmount%></span> 件商品</h4>
    <h3>总价：<span class="text-danger" id="final_total_price">¥ <%=totalPrice%></span></h3>
    <div style="margin-top: 20px;">
        <a href="Index.jsp" class="btn btn-default btn-lg">继续购物</a>
<a href="OrderServlet?type=add" class="btn btn-success btn-lg" style="margin-left: 20px;">提交订单</a>
    </div>
</div>
        </div>
    </div>
</div>
<script type="text/javascript">
function changeQty(id, delta) {
    // 直接跳转到 CartServlet，并带上 type=update, id 和 delta (即 1 或 -1)
    // 这样后端就会更新数据库里的 amount
    window.location.href = "CartServlet?type=update&id=" + id + "&amount=" + delta;
}
</script>
</body>
</html>