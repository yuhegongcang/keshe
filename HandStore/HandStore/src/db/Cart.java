package db;
import bean.CartBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cart {
    // 根据用户名查询该用户全部购物车商品
    public ArrayList<CartBean> getCartListByUsername(String username) {
        ArrayList<CartBean> cartList = new ArrayList<>();
        String sqlStr = "select * from cart where username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConn();
            if(conn == null){
                System.out.println("数据库连接失败");
                return cartList;
            }
            pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CartBean cart = new CartBean();
                cart.setId(rs.getInt("id"));
                cart.setHandName(rs.getString("handName"));
                cart.setPrice(rs.getInt("price"));
                cart.setAmount(rs.getInt("amount"));
                cart.setSumPrice(rs.getInt("sumPrice"));
                cart.setUsername(rs.getString("username"));
                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return cartList;
    }

    // 加入购物车
    public int addCart(CartBean cart) {
        int row = 0;
        String sql = "insert into cart(handName,price,amount,sumPrice,username) values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return 0;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cart.getHandName());
            pstmt.setInt(2, cart.getPrice());
            pstmt.setInt(3, cart.getAmount());
            pstmt.setInt(4, cart.getSumPrice());
            pstmt.setString(5, cart.getUsername());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return row;
    }

    // 根据id删除单条购物车商品
    public int deleteCartItem(int id, String username){
        String sql = "delete from cart where id=? and username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try{
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,username);
            row = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pstmt);
        }
        return row;
    }

    // 新增：清空指定用户所有购物车（下单后清空购物车）
    public int clearUserCart(String username){
        String sql = "delete from cart where username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try{
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            row = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pstmt);
        }
        return row;
    }
}