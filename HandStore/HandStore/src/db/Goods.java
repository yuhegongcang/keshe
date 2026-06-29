package db;

import bean.GoodsBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.DBUtil;

public class Goods {
    // 原有搜索方法
    public ArrayList<GoodsBean> searchGoods(String keyword) {
        ArrayList<GoodsBean> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql;
        if (keyword == null || keyword.trim().isEmpty()) {
            sql = "SELECT * FROM goods";
        } else {
            sql = "SELECT * FROM goods WHERE handName LIKE ?";
        }

        try {
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);

            if (keyword != null && !keyword.trim().isEmpty()) {
                pstmt.setString(1, "%" + keyword + "%");
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                GoodsBean g = new GoodsBean();
                g.setId(rs.getInt("id"));
                g.setHandName(rs.getString("handName"));
                g.setPrice(rs.getInt("price"));
                g.setType(rs.getString("type"));
                g.setBrand(rs.getString("brand"));
                g.setIntroduce(rs.getString("introduce"));
                list.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    // 新增findAllGoods方法，直接调用searchGoods(null)
    public ArrayList<GoodsBean> findAllGoods() {
        return searchGoods(null);
    }
}