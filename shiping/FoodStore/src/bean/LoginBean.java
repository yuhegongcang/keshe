package bean;

public class LoginBean {
    private String msg = "";      // 提示信息
    private String nameStr = "";  // 用于回显的用户名

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