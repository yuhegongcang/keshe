package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import bean.ShoeBean;
import util.DBUtil;

public class Shoe {

    public ArrayList<ShoeBean> getListBeanByType(String type, String value) {
        ArrayList<ShoeBean> arr = new ArrayList<ShoeBean>();
        DBUtil db = new DBUtil();
        
   
        String sql = "SELECT * FROM shoe ";
        
        // 如果有搜索条件，就拼接 WHERE 语句
        if (value != null && !value.isEmpty()) {
        	if ("class".equals(type)) { 
            
                sql += "WHERE type = '" + value + "' ";
            } else if ("shoeName".equals(type)) { 
              
                sql += "WHERE shoeName LIKE '%" + value + "%' ";
            }
        }
        
        try {
            ResultSet rs = db.execSelect(sql);
            while (rs != null && rs.next()) {
                ShoeBean shoe = new ShoeBean();
                shoe.setId(rs.getString("id"));
                shoe.setShoeName(rs.getString("shoeName"));
                shoe.setPrice(rs.getInt("price"));
                shoe.setType(rs.getString("type"));
                shoe.setBrand(rs.getString("brand"));
                shoe.setSize(rs.getString("size"));
                shoe.setIntroduce(rs.getString("introduce"));
                shoe.setImage(rs.getString("image"));
                
                

                arr.add(shoe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close(); // 查完记得关门
        }
        return arr;
    }

    public ShoeBean getShoeById(String id) {
        ShoeBean shoe = null;
        DBUtil db = new DBUtil();
        String sql = "SELECT * FROM shoe WHERE id = '" + id + "'";
        try {
            java.sql.ResultSet rs = db.execSelect(sql);
            if (rs != null && rs.next()) {
                shoe = new ShoeBean();
                shoe.setId(rs.getString("id"));
                shoe.setShoeName(rs.getString("shoeName"));
                shoe.setPrice(rs.getInt("price"));
                shoe.setType(rs.getString("type"));
                shoe.setBrand(rs.getString("brand"));
                shoe.setSize(rs.getString("size"));
                shoe.setIntroduce(rs.getString("introduce"));
                shoe.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return shoe;
    }
    public ArrayList<ShoeBean> getShoesByCategory(String category) {
        ArrayList<ShoeBean> arr = new ArrayList<ShoeBean>();
        DBUtil db = new DBUtil();

        String sql = "SELECT * FROM shoe WHERE type = '" + category + "'";
        try {
            java.sql.ResultSet rs = db.execSelect(sql);
            while (rs != null && rs.next()) {
                ShoeBean shoe = new ShoeBean();
                shoe.setId(rs.getString("id"));
                shoe.setShoeName(rs.getString("shoeName"));
                shoe.setPrice(rs.getInt("price"));
                shoe.setType(rs.getString("type"));
                arr.add(shoe);
                shoe.setImage(rs.getString("image"));
                // 记得要把其他字段也 set 进去
            }
        } catch (Exception e) { e.printStackTrace(); }
        db.close();
        return arr;
    }
}