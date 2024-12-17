package models;

import java.security.Timestamp;

public class Order {
	private int order_id;
	private int user_id;
	private String name;
	private String phone;
	private String email_address;
	private String order_date;
	private int total_price;
	private String shipping_address;
	private String status;
	private String order_hash;
	private String order_signature;
	public Order(int order_id, int user_id, String name, String phone, String email_address, String order_date,
			int total_price, String shipping_address, String status, String order_signature) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.name = name;
		this.phone = phone;
		this.email_address = email_address;
		this.order_date = order_date;
		this.total_price = total_price;
		this.shipping_address = shipping_address;
		this.status = status;
		this.order_signature = order_signature;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrder_hash() {
		return order_hash;
	}
	public void setOrder_hash(String order_hash) {
		this.order_hash = order_hash;
	}
	public String getOrder_signature() {
		return order_signature;
	}
	public void setOrder_signature(String order_signature) {
		this.order_signature = order_signature;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id + ", name=" + name + ", phone=" + phone
				+ ", email_address=" + email_address + ", order_date=" + order_date + ", total_price=" + total_price
				+ ", shipping_address=" + shipping_address + ", status=" + status + ", order_hash=" + order_hash
				+ ", order_signature=" + order_signature + "]";
	}

	
}
