package models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Serializable {
	private int id;
	private String name;
	private String image;
	private double price;
	private String sellprice;
	private String title;
	private String description;
	private int cateId;
	private int id_product;
	private int quantity;

	public Product(int id, String name, String image, double price, String sellprice, String title, String description,
			int cateId, int id_product) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.sellprice = sellprice;
		this.title = title;
		this.description = description;
		this.cateId = cateId;
		this.id_product = id_product;
	}

	public Product(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public Product() {
		id = 0;
		name = "";
		image = "";
		price = 0;
		sellprice = "";
		title = "";
		description = "";
		cateId = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSellprice() {
		return sellprice;
	}

	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", sellprice="
				+ sellprice + ", title=" + title + ", description=" + description + ", cateId=" + cateId
				+ ", id_product=" + id_product + "]";
	}

	public String getPriceCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(price);
	}

}
