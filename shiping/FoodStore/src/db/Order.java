package db;

import bean.OrderBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order {
    DBUtil db = null;

    public Order() {
        db = new DBUtil();
    }

    /**
     * 将购物车中的商品提交为正式订单
     * @param username 用户名
     * @param orderNo 生成的唯一订单号
     * @return true: 成功; false: 失败
     */
    public boolean addOrder(String username, String orderNo) {
        // 1. 生成唯一的订单明细 ID (防止主键冲突)
        String uniqueId = System.currentTimeMillis() + (int)(Math.random()*1000) + "";
        
        // 2. 拼接搬运数据的 SQL (从 cart 到 orders)
        String insertSql = "INSERT INTO orders(id, foodName, price, amount, sumPrice, username, orderNo, status) " +
                           "SELECT '" + uniqueId + "', foodName, price, amount, (price * amount), '" + username + "', '" + orderNo + "', '已支付' " +
                           "FROM cart WHERE username='" + username + "'";
        
        System.out.println("DEBUG: 结算执行的SQL: " + insertSql);
        int insertCount = db.execUpdate(insertSql);
        
        // 3. 【核心修复】：如果订单数据成功搬运（写入成功），立刻清空该用户的购物车！
        if (insertCount > 0) {
            String deleteSql = "DELETE FROM cart WHERE username='" + username + "'";
            db.execUpdate(deleteSql);
            System.out.println("DEBUG: 订单写入成功，已清空用户 [" + username + "] 的购物车！");
        }
        
        // 4. 关闭数据库连接
        db.close();
        
        return insertCount > 0;
    }

    /**
     * 获取指定用户的所有订单记录
     */
    public ArrayList<OrderBean> getListBean(String username) {
        ArrayList<OrderBean> arr = new ArrayList<OrderBean>();
        // 按照订单编号降序排列，最新的订单在最前面
        String sql = "select * from orders where username='" + username + "' order by orderNo desc";
        ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                OrderBean orderBean = new OrderBean();
                orderBean.setId(rs.getString("id"));
                orderBean.setFoodName(rs.getString("foodName"));
                orderBean.setPrice(rs.getInt("price"));
                orderBean.setAmount(rs.getInt("amount"));
                orderBean.setSumPrice(rs.getInt("sumPrice"));
                orderBean.setUsername(rs.getString("username"));
                orderBean.setOrderNo(rs.getString("orderNo"));
                orderBean.setStatus(rs.getString("status"));
                arr.add(orderBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }
    /**
     * 后台功能：获取全站所有用户的订单明细（按订单编号分组，倒序排列）
     */
    public ArrayList<OrderBean> getAllOrders() {
        ArrayList<OrderBean> arr = new ArrayList<OrderBean>();
        // 使用 sum 聚合函数将同一个订单号里的多件商品数量和总价合在一起展示
        String sql = "select orderNo, status, sum(amount) as amount, sum(sumPrice) as sumPrice, username " +
                     "from orders group by orderNo, status, username order by orderNo desc";
        ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                OrderBean orderBean = new OrderBean();
                orderBean.setOrderNo(rs.getString("orderNo"));
                orderBean.setStatus(rs.getString("status"));
                orderBean.setAmount(rs.getInt("amount"));
                orderBean.setSumPrice(rs.getInt("sumPrice"));
                orderBean.setUsername(rs.getString("username"));
                arr.add(orderBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }

    /**
     * 后台功能：将订单状态修改为“已发货”
     * @param orderNo 订单编号
     */
    public boolean deliverOrder(String orderNo) {
        String sql = "update orders set status='已发货' where orderNo='" + orderNo + "'";
        int count = db.execUpdate(sql);
        db.close();
        return count > 0;
    }
 // 假设你已经有了订单操作类 Order [cite: 211]
    public boolean submitOrder(String username, String orderNo) {
        // 1. 开启数据库事务（非常重要，确保订单生成和库存扣减要么同时成功，要么同时失败）
        
        // 2. 扣减库存 SQL：将购物车中该用户的商品对应库存减少
        // 逻辑：更新 goods 表，让库存 stock = stock - 购物车里的数量
        String updateStockSql = "UPDATE goods g, cart c SET g.stock = g.stock - c.amount " +
                                "WHERE g.id = c.id AND c.username = '" + username + "'";
        db.execUpdate(updateStockSql);
        
        // 3. 原有的订单插入逻辑...
        String insertSql = "INSERT INTO orders ... (SELECT ... FROM cart WHERE ...)";
        db.execUpdate(insertSql);
        
        // 4. 清空购物车
        String deleteSql = "DELETE FROM cart WHERE username = '" + username + "'";
        db.execUpdate(deleteSql);
        
        return true;
    }
}