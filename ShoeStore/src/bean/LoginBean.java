package bean;

public class LoginBean {
    private String msg = "";
    private String nameStr = ""; // 存放登录成功的用户名

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getNameStr() {
        return nameStr;
    }
    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }
}