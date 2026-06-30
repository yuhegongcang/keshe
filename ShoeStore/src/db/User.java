package db;
import util.DBUtil; // 引入你之前写好的数据库工具类

public class User {
    
    // 检查用户名是否已经被注册
    public boolean isUserExist(String username) {
        DBUtil db = new DBUtil();
        // 这里的表名 user 和字段名 username 对应我们刚建的表
        String sql = "SELECT count(*) FROM user WHERE username = '" + username + "'";
        int count = db.execSelectCount(sql);
        db.close();
        return count > 0;
    }

    // 将新用户存入数据库
    public boolean insertUserInfo(String username, String password, String sex, String email) {
        DBUtil db = new DBUtil();
        String sql = "INSERT INTO user (username, password, sex, email) VALUES (?, ?, ?, ?)";
        // 复用你 DBUtil 强大的 execUpdate 方法
        int row = db.execUpdate(sql, username, password, sex, email);
        db.close();
        return row > 0;
    }
// 请把这段代码复制到你现有的 User.java 里面
    
    /**
     * 查询用户名和密码是否匹配（用于登录验证）
     */
    public boolean isUserExist(String username, String password) {
        util.DBUtil db = new util.DBUtil();
        // 到数据库里找有没有账号密码完全一致的记录
        String sql = "SELECT count(*) FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        int count = db.execSelectCount(sql);
        db.close();
        
        // 如果 count 大于 0，说明账号密码正确，允许登录
        return count > 0;
    }
 // 1. 获取当前登录用户的全部信息
    public void getUserInfo(bean.UserInfoBean uiBean, String username) {
        util.DBUtil db = new util.DBUtil();
        String sql = "SELECT * FROM user WHERE username = '" + username + "'";
        try {
            java.sql.ResultSet rs = db.execSelect(sql);
            if (rs != null && rs.next()) {
                uiBean.setUsername(rs.getString("username"));
                uiBean.setPassword(rs.getString("password"));
                uiBean.setSex(rs.getString("sex"));
                uiBean.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    // 2. 更新个人信息（只允许改密码和邮箱）
    public boolean updateUserInfo(String username, String password, String email) {
        util.DBUtil db = new util.DBUtil();
        // 使用问号占位符，防止 SQL 报错
        String sql = "UPDATE user SET password=?, email=? WHERE username=?";
        int row = db.execUpdate(sql, password, email, username);
        db.close();
        return row > 0;
    }
}