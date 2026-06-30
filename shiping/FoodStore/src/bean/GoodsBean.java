package bean;

public class GoodsBean {
    private String id;
    private String foodName;
    private int price;
    private String type;
    private String producer;
    private String introduce;
    private int shelfLife;
    private int stock;

    // 请在 IDE 中自动生成以下所有字段的 Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }
    
    public String getIntroduce() { return introduce; }
    public void setIntroduce(String introduce) { this.introduce = introduce; }
    
    public int getShelfLife() { return shelfLife; }
    public void setShelfLife(int shelfLife) { this.shelfLife = shelfLife; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}