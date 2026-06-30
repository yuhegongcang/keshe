package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.GoodsBean;
public class Goods {
    DBUtil db = null;

    public Goods() {
        db = new DBUtil();
    }

    /**
     * 根据不同条件获取食品列表
     * @param type 检索类型 (如 foodName, class)
     * @param value 检索的关键字或分类名
     * @return ArrayList<GoodsBean>
     */
    public ArrayList<GoodsBean> getListBeanByType(String type, String value) {
        ArrayList<GoodsBean> arr = new ArrayList<GoodsBean>();
        String sql = "select id, foodName, price, type, producer, introduce, shelfLife, stock from goods ";
        String whereStr = "";

        // 根据传入的 type 动态拼接 SQL 查询条件 [cite: 2584, 2586, 2600]
        if ("foodName".equals(type)) {
            whereStr = "where foodName like '%" + value + "%' order by id";
        } else if ("class".equals(type)) {
            // 确保 value 是从前端传来的准确分类名
            whereStr = "WHERE type = '" + value + "' ORDER BY id";
        }

        if (!whereStr.isEmpty()) {
            sql += whereStr;
        }

        ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setId(rs.getString("id"));
                goodsBean.setFoodName(rs.getString("foodName"));
                goodsBean.setPrice(rs.getInt("price"));
                goodsBean.setType(rs.getString("type"));
                goodsBean.setProducer(rs.getString("producer"));
                goodsBean.setIntroduce(rs.getString("introduce"));
                goodsBean.setShelfLife(rs.getInt("shelfLife"));
                goodsBean.setStock(rs.getInt("stock"));
                arr.add(goodsBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }
    /**
     * 根据食品 ID 获取单个食品的详细信息
     * @param id 食品的主键 ID
     * @return GoodsBean 实体对象
     */
    public GoodsBean getGoodsById(String id) {
        GoodsBean goodsBean = new GoodsBean();
        // 按照传入的 ID 查询对应的食品数据
        String sql = "select id, foodName, price, type, producer, introduce, shelfLife, stock from goods where id='" + id + "'";
        ResultSet rs = db.execSelect(sql);
        try {
            if (rs.next()) {
                goodsBean.setId(rs.getString("id"));
                goodsBean.setFoodName(rs.getString("foodName"));
                goodsBean.setPrice(rs.getInt("price"));
                goodsBean.setType(rs.getString("type"));
                goodsBean.setProducer(rs.getString("producer"));
                goodsBean.setIntroduce(rs.getString("introduce"));
                goodsBean.setShelfLife(rs.getInt("shelfLife"));
                goodsBean.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return goodsBean;
    }
    /**
     * 后台功能：添加新食品上架
     * @param bean 包含新食品所有信息的实体类
     * @return true: 添加成功; false: 添加失败
     */
    public boolean addGoods(GoodsBean bean) {
        // 构建插入 goods 表的 SQL 语句
        String sql = "insert into goods(id, foodName, price, type, producer, introduce, shelfLife, stock) values('"
                + bean.getId() + "', '" 
                + bean.getFoodName() + "', " 
                + bean.getPrice() + ", '"
                + bean.getType() + "', '" 
                + bean.getProducer() + "', '" 
                + bean.getIntroduce() + "', "
                + bean.getShelfLife() + ", " 
                + bean.getStock() + ")";
        
        int count = db.execUpdate(sql);
        db.close();
        return count > 0;
    }
    
    /**
     * 后台功能：检查商品 ID 是否已存在，防止主键冲突
     */
    public boolean isGoodsExist(String id) {
        String sql = "select count(*) from goods where id='" + id + "'";
        int count = db.execSelectCount(sql);
        db.close();
        return count > 0;
    }
    /**
     * 后台功能：获取所有食品列表（按添加顺序倒序排列）
     */
    public ArrayList<GoodsBean> getAllGoods() {
        ArrayList<GoodsBean> arr = new ArrayList<GoodsBean>();
        String sql = "select id, foodName, price, type, producer, introduce, shelfLife, stock from goods order by id desc";
        ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
            	System.out.println("DEBUG: 发现一条记录，ID为: " + rs.getString("id"));
                GoodsBean bean = new GoodsBean();
                bean.setId(rs.getString("id"));
                bean.setFoodName(rs.getString("foodName"));
                bean.setPrice(rs.getInt("price"));
                bean.setType(rs.getString("type"));
                bean.setProducer(rs.getString("producer"));
                bean.setIntroduce(rs.getString("introduce"));
                bean.setShelfLife(rs.getInt("shelfLife"));
                bean.setStock(rs.getInt("stock"));
                arr.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return arr;
    }

    /**
     * 后台功能：下架（删除）指定食品
     */
    public boolean deleteGoods(String id) {
        String sql = "delete from goods where id='" + id + "'";
        int count = db.execUpdate(sql);
        db.close();
        return count > 0;
    }
    /**
     * 根据食品名称进行模糊查询
     * @param keyword 搜索关键字
     * @return 匹配的商品列表
     */
    public ArrayList<GoodsBean> getGoodsByFuzzyName(String keyword) {
        ArrayList<GoodsBean> list = new ArrayList<>();
        
        // 【新增】：实例化数据库连接对象，这样下面的 db.execQuery 才能生效
        DBUtil db = new DBUtil(); 
        
        // 核心：使用 LIKE '%关键字%' 进行模糊匹配
        String sql = "SELECT * FROM goods WHERE foodName LIKE '%" + keyword + "%'";
        System.out.println("DEBUG: 执行模糊查询SQL: " + sql);
        
        try {
            java.sql.ResultSet rs = db.execSelect(sql);
            while (rs.next()) {
                GoodsBean bean = new GoodsBean();
                bean.setId(rs.getString("id"));
                bean.setFoodName(rs.getString("foodName"));
                bean.setPrice(rs.getInt("price"));
                bean.setType(rs.getString("type"));
                bean.setProducer(rs.getString("producer"));
                bean.setIntroduce(rs.getString("introduce"));
                bean.setShelfLife(rs.getInt("shelfLife"));
                bean.setStock(rs.getInt("stock"));
                
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }
}