package com.litmus7.inventoryfeed.model;

public class Product {
	private int sku;
	private String productName;
	private int quantity;
	private float price;

	public Product(int sku, String productName, int quantity, float price) {
		super();
		this.sku = sku;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
		super();
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + productName + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}

}
