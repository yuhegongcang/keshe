package bean;

public class ShoeBean {
    private String id;
    private String shoeName;
    private int price;
    private String type;
    private String brand;
    private String size;
    private String introduce;
    // ▼▼▼ 加上下面这行 ▼▼▼
    private String image;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getShoeName() { return shoeName; }
    public void setShoeName(String shoeName) { this.shoeName = shoeName; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public String getIntroduce() { return introduce; }
    public void setIntroduce(String introduce) { this.introduce = introduce; }
 // ▼▼▼ 加上下面这两个方法 ▼▼▼
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}
}