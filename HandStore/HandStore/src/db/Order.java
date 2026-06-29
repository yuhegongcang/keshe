package db;
import bean.OrderBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order {
    // 新增订单记录
    public int addOrder(OrderBean order) {
        int row = 0;
        String sql = "insert into orders(id,handName,price,amount,sumPrice,username,orderNo,status) values(?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return 0;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getHandName());
            pstmt.setInt(3, order.getPrice());
            pstmt.setInt(4, order.getAmount());
            pstmt.setInt(5, order.getSumPrice());
            pstmt.setString(6, order.getUsername());
            pstmt.setString(7, order.getOrderNo());
            pstmt.setString(8, order.getStatus());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return row;
    }

    // 根据用户名查询全部订单
    public ArrayList<OrderBean> getOrderListByUser(String username) {
        ArrayList<OrderBean> orderList = new ArrayList<>();
        String sql = "select * from orders where username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return orderList;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderBean order = new OrderBean();
                order.setId(rs.getInt("id"));
                order.setHandName(rs.getString("handName"));
                order.setPrice(rs.getInt("price"));
                order.setAmount(rs.getInt("amount"));
                order.setSumPrice(rs.getInt("sumPrice"));
                order.setUsername(rs.getString("username"));
                order.setOrderNo(rs.getString("orderNo"));
                order.setStatus(rs.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return orderList;
    }

    // 根据订单号查询单个订单详情
    public ArrayList<OrderBean> getOrderByOrderNo(String orderNo) {
        ArrayList<OrderBean> list = new ArrayList<>();
        String sql = "select * from orders where orderNo=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            rs = pstmt.executeQuery();
            while(rs.next()){
                OrderBean o = new OrderBean();
                o.setId(rs.getInt("id"));
                o.setHandName(rs.getString("handName"));
                o.setPrice(rs.getInt("price"));
                o.setAmount(rs.getInt("amount"));
                o.setSumPrice(rs.getInt("sumPrice"));
                o.setUsername(rs.getString("username"));
                o.setOrderNo(rs.getString("orderNo"));
                o.setStatus(rs.getString("status"));
                list.add(o);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(conn,pstmt,rs);
        }
        return list;
    }

    // 新增：根据订单号删除订单 deleteOrder
    public int deleteOrder(String orderNo){
        String sql = "delete from orders where orderNo=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try{
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            row = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pstmt);
        }
        return row;
    }

    // 新增：支付订单，修改状态为已支付 payOrder
    public int payOrder(String orderNo){
        String sql = "update orders set status='已支付' where orderNo=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try{
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            row = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pstmt);
        }
        return row;
    }
}