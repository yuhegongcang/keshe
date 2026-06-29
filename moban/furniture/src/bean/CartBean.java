package bean;

public class CartBean {
    private int cartId;      
    private int furnitureId;   
    private String furnitureName;
    private int price;          
    private int quantity;       
    
    public int getSubTotal() {
        return price * quantity;
    }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    
    public int getFurnitureId() { return furnitureId; }
    public void setFurnitureId(int furnitureId) { this.furnitureId = furnitureId; }
    
    public String getFurnitureName() { return furnitureName; }
    public void setFurnitureName(String furnitureName) { this.furnitureName = furnitureName; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}