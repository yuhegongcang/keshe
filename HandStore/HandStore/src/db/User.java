package db;
import bean.UserInfoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    // 判断用户名是否存在（注册查重）
    public boolean isUserExist(String name) {
        int count = 0;
        String sql = "select count(*) from user where username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return false;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return count > 0;
    }

    // 登录校验：用户名+密码
    public boolean isUserExist(String name, String pwd) {
        int count = 0;
        String sql = "select count(*) from user where username=? and password=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return false;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return count > 0;
    }

    // 注册新增用户
    public boolean insertUserInfo(String name, String pwd, String sex, String email) {
        int row = 0;
        String sql = "insert into user(username,password,sex,email) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return false;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            pstmt.setString(3, sex);
            pstmt.setString(4, email);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return row > 0;
    }

    // 根据用户名查询用户信息（适配 UserInfoBean 字段后缀 Str）
    public void getUserInfo(UserInfoBean ui, String uname) {
        String sql = "select * from user where username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) return;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname);
            rs = pstmt.executeQuery();
            if(rs.next()){
                ui.setNameStr(rs.getString("username"));
                ui.setPwdStr(rs.getString("password"));
                ui.setSexStr(rs.getString("sex"));
                ui.setMailAddrStr(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }

    // 新增：修改用户密码、邮箱
    public int updateUserInfo(UserInfoBean ui, String username, String newPwd, String newEmail){
        String sql = "update user set password=?,email=? where username=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try{
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPwd);
            pstmt.setString(2, newEmail);
            pstmt.setString(3, username);
            row = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pstmt);
        }
        return row;
    }
}