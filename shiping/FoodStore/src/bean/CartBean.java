package bean;

public class CartBean {
    private String id;
    private String foodName; // 替换了原有的 bookName
    private int price;
    private int amount;
    private int sumPrice;
    private String userName;
    private String msg; // 操作提示信息

    // 请在 IDE 中自动生成以上所有字段的 Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getSumPrice() {return this.price * this.amount;}
    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    
}