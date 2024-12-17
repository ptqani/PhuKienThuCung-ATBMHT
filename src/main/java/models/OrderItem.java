package models;

public class OrderItem {
    private int id;  // ID của sản phẩm trong đơn hàng
    private int orderId;  // ID của đơn hàng
    private int productId;  // ID sản phẩm
    private int quantity;  // Số lượng sản phẩm
    private int price;  // Giá sản phẩm tại thời điểm đặt hàng

    // Constructor
    public OrderItem(int productId, int quantity, int price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter và setter cho các trường
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Quantity: " + quantity + ", Price: " + price;
    }
}
