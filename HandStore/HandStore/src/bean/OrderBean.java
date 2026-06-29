package bean;
public class OrderBean {
    private Integer id,price,amount,sumPrice;
    private String handName,username,orderNo,status,msg;
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getHandName() {return handName;}
    public void setHandName(String handName) {this.handName = handName;}
    public Integer getPrice() {return price;}
    public void setPrice(Integer price) {this.price = price;}
    public Integer getAmount() {return amount;}
    public void setAmount(Integer amount) {this.amount = amount;}
    public Integer getSumPrice() {return sumPrice;}
    public void setSumPrice(Integer sumPrice) {this.sumPrice = sumPrice;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getOrderNo() {return orderNo;}
    public void setOrderNo(String orderNo) {this.orderNo = orderNo;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getMsg() {return msg;}
    public void setMsg(String msg) {this.msg = msg;}
}