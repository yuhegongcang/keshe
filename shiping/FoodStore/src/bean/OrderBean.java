package bean;

public class OrderBean {
    private String id;
    private String foodName;
    private int price;
    private int amount;
    private int sumPrice;
    private String username;
    private String orderNo; // 订单编号
    private String status;  // 订单状态

    // 请在 IDE 中自动生成以下字段的 Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getSumPrice() { return sumPrice; }
    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}