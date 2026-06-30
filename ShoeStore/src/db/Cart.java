package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import bean.CartBean;
import util.DBUtil;

public class Cart {
    
    public boolean isExist(String username, String shoeId) {
        DBUtil db = new DBUtil();
        String sql = "SELECT count(*) FROM cart WHERE id='" + shoeId + "' AND username='" + username + "'";
        int count = db.execSelectCount(sql);
        db.close();
        return count > 0;
    }

    
    public boolean addCart(String username, String shoeId, String shoeName, int price) {
        DBUtil db = new DBUtil();
        int amount = 1; 
        int sumPrice = price * amount;
        String sql = "INSERT INTO cart (id, shoeName, price, amount, sumPrice, username) VALUES (?, ?, ?, ?, ?, ?)";
        int row = db.execUpdate(sql, shoeId, shoeName, price, amount, sumPrice, username);
        db.close();
        return row > 0;
    }

  
    public ArrayList<CartBean> getListBean(String username) {
        ArrayList<CartBean> arr = new ArrayList<CartBean>();
        DBUtil db = new DBUtil();
        String sql = "SELECT * FROM cart WHERE username='" + username + "'";
        try {
            ResultSet rs = db.execSelect(sql);
            while (rs != null && rs.next()) {
                CartBean cart = new CartBean();
                cart.setId(rs.getString("id"));
                cart.setShoeName(rs.getString("shoeName"));
                cart.setPrice(rs.getInt("price"));
                cart.setAmount(rs.getInt("amount"));
                cart.setSumPrice(rs.getInt("sumPrice"));
                cart.setUserName(rs.getString("username"));
                arr.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }


    public boolean delByGoods(String username, String shoeId) {
        DBUtil db = new DBUtil();
        String sql = "DELETE FROM cart WHERE id='" + shoeId + "' AND username='" + username + "'";
        int row = db.execUpdate(sql);
        db.close();
        return row > 0;
    }

    public boolean updateCartAmount(String username, String shoeId, int addAmount) {
        DBUtil db = new DBUtil();
        
        String sql = "UPDATE cart SET amount = amount + " + addAmount + 
                     ", sumPrice = price * amount " + 
                     "WHERE username='" + username + "' AND id='" + shoeId + "'";
        int row = db.execUpdate(sql);
        db.close();
        return row > 0;
    }
}