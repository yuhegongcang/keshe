package db;

public class User {
    DBUtil db = null;

    public User() {
        db = new DBUtil();
    }

    // 1. 查询用户名是否已经存在
    public boolean isUserExist(String name) {
        // 去数据库里查有没有这个名字
        String sql = "SELECT count(*) FROM user WHERE name='" + name + "'";
        int count = db.execSelectCount(sql);
        db.close();
        
        // 如果 count > 0 说明名字被占用了，返回 true
        return count > 0;
    }

    // 2. 将新用户插入数据库
    public boolean insertUserInfo(String name, String pwd, String sex, String mail) {
        String sql = "INSERT INTO user(name, password, sex, mail) VALUES('" 
                   + name + "', '" + pwd + "', '" + sex + "', '" + mail + "')";
        int count = db.execUpdate(sql);
        db.close();
        
        // 如果插入的行数 > 0，说明注册成功
        return count > 0;
    }
 // 3. 验证用户名和密码是否正确 (登录用)
    public boolean isUserExist(String name, String pwd) {
        // 去数据库里查有没有账号密码完全匹配的记录
        String sql = "SELECT count(*) FROM user WHERE name='" + name + "' AND password='" + pwd + "'";
        int count = db.execSelectCount(sql);
        db.close();
        
        // 如果 count > 0 说明账号密码完全正确
        return count > 0;
    }
}