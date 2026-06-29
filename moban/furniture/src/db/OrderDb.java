package db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.CartBean;
import bean.OrderBean;

public class OrderDb {
    DBUtil db = new DBUtil();

    // 结算：生成订单并清空购物车
    public boolean checkout(String username) {
        CartDb cartDb = new CartDb();
        ArrayList<CartBean> cartList = cartDb.getMyCart(username);
        if(cartList.isEmpty()) return false;

        // 生成订单号 (用当前时间精确到秒来模拟)
        String orderNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        for (CartBean item : cartList) {
            String sql = "INSERT INTO orders(orderNo, username, furniture_id, bookName, price, amount, sumPrice) VALUES('"
                       + orderNo + "', '" + username + "', " + item.getFurnitureId() + ", '"
                       + item.getFurnitureName() + "', " + item.getPrice() + ", "
                       + item.getQuantity() + ", " + item.getSubTotal() + ")";
            db.execUpdate(sql);
        }
        // 订单生成完毕，清空该用户的购物车
        cartDb.clearCart(username);
        db.close();
        return true;
    }
 // 查询某用户的所有历史订单
    public ArrayList<OrderBean> getMyOrders(String username) {
        ArrayList<OrderBean> list = new ArrayList<OrderBean>();
        // 按订单编号倒序排列，让最新下的单排在最前面
        String sql = "SELECT * FROM orders WHERE username = '" + username + "' ORDER BY id DESC";
        java.sql.ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                OrderBean bean = new OrderBean();
                bean.setId(rs.getInt("id"));
                bean.setOrderNo(rs.getString("orderNo"));
                bean.setProductName(rs.getString("bookName")); // 映射咱们表里的字段
                bean.setPrice(rs.getInt("price"));
                bean.setAmount(rs.getInt("amount"));
                bean.setSumPrice(rs.getInt("sumPrice"));
                bean.setStatus(rs.getString("status"));
                list.add(bean);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }	
}