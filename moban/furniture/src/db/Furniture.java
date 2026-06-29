package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.FurnitureBean;

public class Furniture {
    DBUtil db = null;

    public Furniture() {
        db = new DBUtil();
    }

    // 获取所有家具的列表
    public ArrayList<FurnitureBean> getAllFurniture() {
        ArrayList<FurnitureBean> list = new ArrayList<FurnitureBean>();
        // 查询家具表 (假设表名叫 furniture)
        String sql = "SELECT * FROM furniture ORDER BY id DESC";
        ResultSet rs = db.execSelect(sql);

        try {
            while (rs.next()) {
                FurnitureBean bean = new FurnitureBean();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setCategory(rs.getString("category"));
                bean.setPrice(rs.getInt("price"));
                bean.setMaterial(rs.getString("material"));
                bean.setDescription(rs.getString("description"));
             // 在 fb.setDescription(rs.getString("description")); 的下面加上这句：
                bean.setImgName(rs.getString("img_name"));
                
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }
 // 1. 根据关键字模糊搜索家具
    public ArrayList<FurnitureBean> searchFurniture(String keyword) {
        ArrayList<FurnitureBean> list = new ArrayList<FurnitureBean>();
        // 使用 LIKE 进行模糊匹配
        String sql = "SELECT * FROM furniture WHERE name LIKE '%" + keyword + "%' OR category LIKE '%" + keyword + "%'";
        java.sql.ResultSet rs = db.execSelect(sql);
        try {
            while (rs.next()) {
                FurnitureBean fb = new FurnitureBean();
                fb.setId(rs.getInt("id"));
                fb.setName(rs.getString("name"));
                fb.setCategory(rs.getString("category"));
                fb.setPrice(rs.getInt("price"));
                fb.setMaterial(rs.getString("material"));
                fb.setDescription(rs.getString("description"));
             // 在 fb.setDescription(rs.getString("description")); 的下面加上这句：
                fb.setImgName(rs.getString("img_name"));
                list.add(fb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    // 2. 根据 ID 获取单件家具的详细信息
    public FurnitureBean getFurnitureById(int id) {
        FurnitureBean fb = null;
        String sql = "SELECT * FROM furniture WHERE id=" + id;
        java.sql.ResultSet rs = db.execSelect(sql);
        try {
            if (rs.next()) {
                fb = new FurnitureBean();
                fb.setId(rs.getInt("id"));
                fb.setName(rs.getString("name"));
                fb.setCategory(rs.getString("category"));
                fb.setPrice(rs.getInt("price"));
                fb.setMaterial(rs.getString("material"));
                fb.setDescription(rs.getString("description"));
             // 在 fb.setDescription(rs.getString("description")); 的下面加上这句：
                fb.setImgName(rs.getString("img_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return fb;
    }
 // 添加新家具的方法
    public boolean insertFurniture(String name, String category, int price, String material, String desc, String imgName) {
        db = new DBUtil();
        // 把所有信息，连同刚刚生成的乱码图片名，一起 INSERT 到表里
        String sql = "INSERT INTO furniture (name, category, price, material, description, img_name) " +
                     "VALUES ('" + name + "', '" + category + "', " + price + ", '" + material + "', '" + desc + "', '" + imgName + "')";
        int count = db.execUpdate(sql);
        db.close();
        return count > 0;
    }
 // 4. 根据 ID 彻底删除家具记录
    public boolean deleteFurnitureById(int id) {
        db = new DBUtil();
        String sql = "DELETE FROM furniture WHERE id=" + id;
        int count = db.execUpdate(sql);
        db.close();
        return count > 0;
    }
 // 5. 根据 ID 修改家具信息
    public boolean updateFurniture(int id, String name, String category, int price, String material, String desc, String imgName) {
        db = new DBUtil();
        // 拼接 UPDATE SQL 语句
        String sql = "UPDATE furniture SET name='" + name + "', category='" + category + "', price=" + price + 
                     ", material='" + material + "', description='" + desc + "', img_name='" + imgName + "' " +
                     "WHERE id=" + id;
        int count = db.execUpdate(sql);
        db.close();
        return count > 0;
    }
}