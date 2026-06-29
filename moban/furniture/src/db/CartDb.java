package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.CartBean;

public class CartDb {
    DBUtil db = null;

    public CartDb() {
        db = new DBUtil();
    }

    // 1. 加入购物车逻辑
    public void addToCart(String username, int furnitureId) {
        // 先查一下这件家具是不是已经在购物车里了
        String checkSql = "SELECT * FROM cart WHERE username='" + username + "' AND furniture_id=" + furnitureId;
        ResultSet rs = db.execSelect(checkSql);
        try {
            if (rs.next()) {
                // 如果已经在购物车了，数量 + 1
                int currentQty = rs.getInt("quantity");
                String updateSql = "UPDATE cart SET quantity=" + (currentQty + 1) + " WHERE id=" + rs.getInt("id");
                db.execUpdate(updateSql);
            } else {
                // 如果没有，就插入一条新记录
                String insertSql = "INSERT INTO cart(username, furniture_id, quantity) VALUES('" + username + "', " + furnitureId + ", 1)";
                db.execUpdate(insertSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    // 2. 获取某用户的购物车列表 (重点：需要联表查询，把家具的具体信息也带出来)
    public ArrayList<CartBean> getMyCart(String username) {
        ArrayList<CartBean> list = new ArrayList<CartBean>();
        // 使用 INNER JOIN 将 cart 表和 furniture 表连接起来
        String sql = "SELECT c.id as cart_id, c.quantity, f.id as f_id, f.name, f.price " +
                     "FROM cart c INNER JOIN furniture f ON c.furniture_id = f.id " +
                     "WHERE c.username='" + username + "' ORDER BY c.id DESC";
        ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                CartBean bean = new CartBean();
                bean.setCartId(rs.getInt("cart_id"));
                bean.setFurnitureId(rs.getInt("f_id"));
                bean.setFurnitureName(rs.getString("name"));
                bean.setPrice(rs.getInt("price"));
                bean.setQuantity(rs.getInt("quantity"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }
 // 增加数量
    public void addQty(int cartId) {
        String sql = "UPDATE cart SET quantity = quantity + 1 WHERE id = " + cartId;
        db.execUpdate(sql);
    }
    // 减少数量 (最少不能低于1)
    public void subQty(int cartId) {
        String sql = "UPDATE cart SET quantity = quantity - 1 WHERE id = " + cartId + " AND quantity > 1";
        db.execUpdate(sql);
    }
    // 删除购物车中的某项
    public void deleteItem(int cartId) {
        String sql = "DELETE FROM cart WHERE id = " + cartId;
        db.execUpdate(sql);
    }
    // 清空某人的购物车（结算后调用）
    public void clearCart(String username) {
        String sql = "DELETE FROM cart WHERE username = '" + username + "'";
        db.execUpdate(sql);
    }
}