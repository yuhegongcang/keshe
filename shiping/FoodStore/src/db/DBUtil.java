package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库交互工具类
 */
public class DBUtil {

    Connection conn = null;
    Statement stat = null;
    PreparedStatement preStat = null;
    ResultSet rs = null;

    // 数据库连接信息 (请根据你的本地MySQL实际情况修改密码)
    final String dbDriver = "com.mysql.jdbc.Driver";
    // 注意这里的数据库名称改为了 foodstoredb
    final String dbUrl = "jdbc:mysql://localhost:3306/foodstoredb?useUnicode=true&characterEncoding=UTF-8";
    final String user = "root";
    final String password = "123456"; // 替换为你自己的数据库密码

    public DBUtil() {
        conn = getConnection(dbDriver, dbUrl, user, password);
        stat = getStatement(conn);
    }

    public DBUtil(boolean isPreStat, String sql) {
        conn = getConnection(dbDriver, dbUrl, user, password);
        if (isPreStat) {
            preStat = getPreparedStatement(sql);
        } else {
            stat = getStatement(conn);
        }
    }

    // 获取数据库连接
    public Connection getConnection(String dbDriver, String dbUrl, String user, String password) {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 获取 Statement
    public Statement getStatement(Connection conn) {
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

    // 获取 PreparedStatement
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            preStat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preStat;
    }

    // 执行查询语句，返回 ResultSet
    public ResultSet execSelect(String sql) {
        try {
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 执行查询语句，返回记录条数
    public int execSelectCount(String sql) {
        int count = 0;
        try {
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 执行更新语句 (Insert, Update, Delete)
    public int execUpdate(String sql) {
        int count = 0;
        try {
            count = stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 关闭资源
    public void close() {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (preStat != null) preStat.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            System.out.println("数据库资源关闭失败");
            ex.printStackTrace();
        }
    }
}