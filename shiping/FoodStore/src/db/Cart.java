package db;

import bean.CartBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cart {
    DBUtil db = null;

    public Cart() {
        db = new DBUtil();
    }

    /**
     * 判断商品是否存在于购物车 [cite: 2941]
     */
    public boolean isExist(String username, String goodsId) {
        String sql = "select count(*) from cart where id='" + goodsId + "' and username='" + username + "'";
        int count = db.execSelectCount(sql);
        return count > 0;
    }

    /**
     * 从首页将商品添加到购物车 [cite: 2976, 2980]
     */
 // 在 Cart.java 的 addFromIndex 方法中
    public void addFromIndex(String username, String goodsId, String foodName, int price) {
        // 这里的 INSERT 语句必须写全所有字段名！
        // 假设表结构是：id, username, foodName, price, amount
        String sql = "INSERT INTO cart(id, username, foodName, price, amount) VALUES('" 
                     + goodsId + "', '" + username + "', '" + foodName + "', " + price + ", 1)";
        db.execUpdate(sql);
        db.close();
    }
    public void updateAmount(String username, String goodsId, int delta) {
        String sql = "UPDATE cart SET amount = amount + " + delta + " WHERE username='" + username + "' AND id='" + goodsId + "'";
        db.execUpdate(sql);
        db.close();
    }

    /**
     * 根据用户名获取购物车列表 [cite: 3663]
     */
    public ArrayList<CartBean> getListBean(String username) {
        ArrayList<CartBean> arr = new ArrayList<CartBean>();
        String sql = "select id, foodName, price, amount, sumPrice, username from cart where username='" + username + "'";
        ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                CartBean cartBean = new CartBean();
                cartBean.setId(rs.getString("id"));
                cartBean.setFoodName(rs.getString("foodName"));
                cartBean.setPrice(rs.getInt("price"));
                cartBean.setAmount(rs.getInt("amount"));
                cartBean.setSumPrice(rs.getInt("sumPrice"));
                cartBean.setUserName(rs.getString("username"));
                arr.add(cartBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }
    /**
     * 删除购物车中的指定商品
     */
    public void deleteItem(String username, String goodsId) {
        String sql = "DELETE FROM cart WHERE username='" + username + "' AND id='" + goodsId + "'";
        db.execUpdate(sql);
        db.close();
    }

    /**
     * 结算后清空购物车
     */
    public void clearCart(String username) {
        String sql = "DELETE FROM cart WHERE username='" + username + "'";
        db.execUpdate(sql);
        db.close();
    }
}