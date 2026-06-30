package bean;

public class OrderBean {
    private String orderNo;     // 订单编号
    private String status;      // 状态 (已支付/未支付)
    private int totalAmount;   
    private int totalPrice;     // 订单总金额

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
 // 在原有的 orderNo, status 等变量下面，增加这4个详细字段
    private String shoeName;    //
    private int price;          // 单价
    private int amount;         // 购买数量
    private int sumPrice;       // 单个商品小计

    // 补充它们的 Get 和 Set 方法
    public String getShoeName() { return shoeName; }
    public void setShoeName(String shoeName) { this.shoeName = shoeName; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    // 原来已经有总数量(totalAmount)和总价(totalPrice)，这里重载一个单品数量的
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getSumPrice() { return sumPrice; }
    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }
}