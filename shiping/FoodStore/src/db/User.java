package db;

import java.sql.ResultSet;

import bean.UserInfoBean;

public class User {
    // 【修复点1】：去掉了全局的 DBUtil db = null; 和 构造函数里的初始化
    public User() {
    }

    /**
     * 查询是否存在相同用户名
     */
    public boolean isUserExist(String name) {
        DBUtil db = new DBUtil(); // 【核心修复】：每次进门都拿一把全新的钥匙
        String sql = "select count(*) from user where username='" + name + "'";
        int count = db.execSelectCount(sql);
        db.close();
        return count > 0;
    }

    /**
     * 注册新用户
     */
    public boolean insertUserInfo(String name, String pwd, String sex, String email) {
        DBUtil db = new DBUtil(); // 【核心修复】：注册时用新连接，不再报错！
        String sql = "insert into user(username, password, sex, email) values('" 
                     + name + "', '" + pwd + "', '" + sex + "', '" + email + "')";
        int count = db.execUpdate(sql);
        db.close();
        return count > 0; 
    }

    /**
     * 查询用户名和密码是否一致（用于登录校验）
     */
    public boolean isUserExist(String name, String pwd) {
        DBUtil db = new DBUtil(); // 同样加上新连接
        String sql = "select count(*) from user where username='" + name + "' and password='" + pwd + "'";
        int count = db.execSelectCount(sql);
        db.close();
        return count > 0;
    }

    /**
     * 更新用户信息（密码和邮箱）
     */
    public void updateUserInfo(UserInfoBean uiBean, String username, String password, String mail) {
        DBUtil db = new DBUtil(); // 同样加上新连接
        String sql = "UPDATE user SET password='" + password + "', email='" + mail + "' WHERE username='" + username + "'";
        
        int count = db.execUpdate(sql);
        
        if (count > 0) {
            uiBean.setMsg("更新成功！");
        } else {
            uiBean.setMsg("更新失败，请检查数据库连接。");
        }
        db.close();
    }

    /**
     * 获取用户信息（供个人中心页面显示用）
     */
    public void getUserInfo(UserInfoBean uiBean, String username) {
        DBUtil db = new DBUtil(); // 同样加上新连接
        String sql = "SELECT * FROM user WHERE username='" + username + "'";
        ResultSet rs = db.execSelect(sql);
        try {
            if (rs.next()) {
                uiBean.setNameStr(rs.getString("username"));
                uiBean.setPwdStr(rs.getString("password"));
                uiBean.setSexStr(rs.getString("sex"));
                uiBean.setMailAddrStr(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}