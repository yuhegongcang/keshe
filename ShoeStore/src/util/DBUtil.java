package util;

import java.sql.*;

public class DBUtil {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // 注意：这里的 URL 已经直接指向了咱们新建的 shoestoredb，并且加了防乱码的参数
    private static final String URL = "jdbc:mysql://localhost:3306/shoestoredb?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
    
    // 你的 MySQL 账号
    private static final String USER = "root";
    // 你的 MySQL 密码（如果你的密码不是 root，请一定要在这里修改！）
    private static final String PWD = "123456";

    /**
     * 1. 获取数据库连接
     */
    public void getConn() {
        try {
            // 注册 MySQL 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            System.out.println("【数据库连接失败】请检查 MySQL 服务是否启动，以及账号密码是否正确！");
            e.printStackTrace();
        }
    }

    /**
     * 2. 执行查询，返回记录的数量 (例如：查询用户名是否重复)
     * 使用了可变参数 Object... params，非常灵活
     */
    public int execSelectCount(String sql, Object... params) {
        getConn();
        int count = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            // 自动填充问号占位符
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 3. 执行增、删、改操作 (例如：插入注册的新用户)
     */
    public int execUpdate(String sql, Object... params) {
        getConn();
        int row = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            // 自动填充问号占位符
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 4. 释放数据库资源，防止内存泄漏
     */
    public void close() {
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    /**
     * 执行查询，返回完整结果集 (用于读取商品列表)
     */
    public ResultSet execSelect(String sql) {
        getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
 // --- 新增功能：发布新球鞋 ---
    public static boolean insertShoe(String name, String brand, String size, double price, String introduce, String imgPath) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 获取数据库连接
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PWD);
            
            // 编写 SQL 插入语句
            String sql = "INSERT INTO shoe(shoeName, brand, size, price, introduce, image) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            // 替换问号里的数据
            pstmt.setString(1, name);
            pstmt.setString(2, brand);
            pstmt.setString(3, size);
            pstmt.setDouble(4, price);
            pstmt.setString(5, introduce);
            pstmt.setString(6, imgPath); // 这里存的就是 img/xxx.png
            
            // 执行写入
            int row = pstmt.executeUpdate();
            return row > 0; // 如果写入成功，row 会大于 0
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 关闭连接，释放资源
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 // --- 新增功能：根据 ID 删除球鞋 ---
    public static boolean deleteShoe(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 获取数据库连接
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // ⚠️注意：如果你这里的密码变量名不叫 PASS (比如叫 PASSWORD)，请手动改成你真实的变量名！
            conn = DriverManager.getConnection(URL, USER, PWD); 
            
            // 编写 SQL 删除语句：删除 id 等于传进来的那个数字的数据
            String sql = "DELETE FROM shoe WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            // 执行删除
            int row = pstmt.executeUpdate();
            return row > 0; // 如果删除成功，row 会大于 0
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 关闭连接
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}