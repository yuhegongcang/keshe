package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import bean.OrderBean;
import util.DBUtil;
import util.Common;

public class Order {
   
    public boolean createOrderFromCart(String username) {
        DBUtil db = new DBUtil();
        Common comm = new Common();
        
        String orderNo = comm.GenerateOrderNo(); 
        
       
        String sqlInsert = "INSERT INTO orders (id, shoeName, price, amount, sumPrice, username, orderNo, status) " +
                           "SELECT id, shoeName, price, amount, sumPrice, username, '" + orderNo + "', '未支付' FROM cart " +
                           "WHERE username='" + username + "'";
        
        int row = db.execUpdate(sqlInsert);
        
        if (row > 0) {
            
            String sqlDelete = "DELETE FROM cart WHERE username='" + username + "'";
            db.execUpdate(sqlDelete);
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    // 2. 获取我的订单列表 [cite: 4261-4288]
    public ArrayList<OrderBean> getOrderList(String username) {
        ArrayList<OrderBean> arr = new ArrayList<OrderBean>();
        DBUtil db = new DBUtil();
        
       
        String sql = "SELECT orderNo, status, SUM(amount) as totalAmount, SUM(sumPrice) as totalPrice " +
                     "FROM orders WHERE username='" + username + "' GROUP BY orderNo, status ORDER BY orderNo DESC";
        
        try {
            ResultSet rs = db.execSelect(sql);
            while (rs != null && rs.next()) {
                OrderBean order = new OrderBean();
                order.setOrderNo(rs.getString("orderNo"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getInt("totalAmount"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                arr.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }
 // 根据订单号(orderNo)查询订单里的具体商品明细
    public ArrayList<OrderBean> getOrderDetail(String username, String orderNo) {
        ArrayList<OrderBean> arr = new ArrayList<OrderBean>();
        DBUtil db = new DBUtil();
        
        
        String sql = "SELECT * FROM orders WHERE username='" + username + "' AND orderNo='" + orderNo + "'";
        try {
            java.sql.ResultSet rs = db.execSelect(sql);
            while (rs != null && rs.next()) {
                OrderBean order = new OrderBean();
                order.setOrderNo(rs.getString("orderNo"));
                order.setStatus(rs.getString("status"));
                order.setShoeName(rs.getString("shoeName"));
                order.setPrice(rs.getInt("price"));
                order.setAmount(rs.getInt("amount"));
                order.setSumPrice(rs.getInt("sumPrice"));
                arr.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }
 // 1. 删除订单方法 [cite: 4222]
    public boolean deleteOrder(String username, String orderNo) {
        DBUtil db = new DBUtil();
        // 确保只能删除当前用户自己的订单，防止越权
        String sql = "DELETE FROM orders WHERE username='" + username + "' AND orderNo='" + orderNo + "'";
        int row = db.execUpdate(sql);
        db.close();
        return row > 0;
    }

    // 2. 模拟支付方法 (把状态从'未支付'改为'已支付')
    public boolean payOrder(String username, String orderNo) {
        DBUtil db = new DBUtil();
        String sql = "UPDATE orders SET status='已支付' WHERE username='" + username + "' AND orderNo='" + orderNo + "'";
        int row = db.execUpdate(sql);
        db.close();
        return row > 0;
    }
}