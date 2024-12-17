package models;

public class OrderDetail {
	private int id;
	private int order_id;
	private int product_id;
	private String product_name;
	private int quantity;
	private int price;
	private int total_price;

	public OrderDetail(int id, int order_id, int product_id, String product_name, int quantity, int price,
			int total_price) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
		this.total_price = total_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order_id=" + order_id + ", product_id=" + product_id + ", product_name="
				+ product_name + ", quantity=" + quantity + ", price=" + price + ", total_price=" + total_price + "]";
	}

}
