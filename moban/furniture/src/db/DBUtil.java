package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库使用类 (兼容 MySQL 8.0 + 课设样书全功能)
 */
public class DBUtil {
    Connection conn = null;
    Statement stat = null;
    PreparedStatement preStat = null;
    ResultSet rs = null;

    final String dbDriver = "com.mysql.cj.jdbc.Driver";

    final String dbUrl = "jdbc:mysql://localhost:3306/furniture?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";    
    final String user = "root";
    final String password = "123456"; 

    /**
     * 构造函数实现数据库的连接 (无参)
     */
    public DBUtil() {
        conn = getConnection(dbDriver, dbUrl, user, password);
        stat = getStatement(conn);
    }

    /**
     * 构造函数实现数据库的连接 (带参)
     */
    public DBUtil(boolean isPreStat, String sql) {
        conn = getConnection(dbDriver, dbUrl, user, password);
        if (isPreStat) {
            preStat = getPreparedStatement(sql);
        } else {
            stat = getStatement(conn);
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (preStat != null) preStat.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            System.out.println("数据库关闭失败.url=" + dbUrl);
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection(String dbDriver, String dbUrl, String user, String password) {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            preStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preStat;
    }

    public Statement getStatement(Connection conn) {
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

    public ResultSet execSelect(String sql) {
        try {
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int execSelectCount(String sql) {
        int count = 0;
        try {
            rs = stat.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int execUpdate(String sql) {
        int count = 0;
        try {
            count = stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int execPreUpdate(String sql, String[] arr) {
        int count = 0;
        try {
            for (int i = 1; i <= arr.length; i++) {
                preStat.setString(i, arr[i - 1]);
            }
            count = preStat.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}