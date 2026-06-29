package bean;

public class FurnitureBean {
    private int id;                
    private String name;           
    private String category;       
    private int price;             
    private String material;        
    private String description;     
    private String msg;           
    private String imgName;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public String getImgName() { return imgName; }
    public void setImgName(String imgName) { this.imgName = imgName; }
}