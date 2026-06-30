package bean;

/**
 * 个人中心数据实体类
 */
public class UserInfoBean {
    private String nameStr;      // 用户名
    private String pwdStr;       // 用户密码
    private String sexStr;       // 性别
    private String mailAddrStr;  // 邮箱地址
    private String msg;          // 页面反馈信息 (如“修改成功”)

    // 生成 Getter 和 Setter 方法
    public String getNameStr() { return nameStr; }
    public void setNameStr(String nameStr) { this.nameStr = nameStr; }

    public String getPwdStr() { return pwdStr; }
    public void setPwdStr(String pwdStr) { this.pwdStr = pwdStr; }

    public String getSexStr() { return sexStr; }
    public void setSexStr(String sexStr) { this.sexStr = sexStr; }

    public String getMailAddrStr() { return mailAddrStr; }
    public void setMailAddrStr(String mailAddrStr) { this.mailAddrStr = mailAddrStr; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
}