package bean;

public class CartBean {
    private String id;        
    private String shoeName;  
    private int price;        // 单价
    private int amount;       // 数量
    private int sumPrice;     // 小计
    private String userName;  // 归属的用户名
    private String msg;       // 提示信息

    // 下面是自动生成的 Get 和 Set 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getShoeName() { return shoeName; }
    public void setShoeName(String shoeName) { this.shoeName = shoeName; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getSumPrice() { return sumPrice; }
    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
}