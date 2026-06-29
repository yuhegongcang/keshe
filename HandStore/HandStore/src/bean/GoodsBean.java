package bean;

public class GoodsBean {
    private int id;
    private String handName;
    private int price;
    private String type;
    private String brand;
    private String introduce;
    private int amount;
    private String imgUrl;

    //无参构造
    public GoodsBean() {
    }

    //get & set 全部方法
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getHandName() {
        return handName;
    }
    public void setHandName(String handName) {
        this.handName = handName;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}