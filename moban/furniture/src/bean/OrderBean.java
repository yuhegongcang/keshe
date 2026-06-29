package bean;

public class OrderBean {
    private int id;
    private String orderNo;  
    private String productName; 
    private int price;        
    private int amount;         
    private int sumPrice;      
    private String status;     

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    
    public int getSumPrice() { return sumPrice; }
    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}