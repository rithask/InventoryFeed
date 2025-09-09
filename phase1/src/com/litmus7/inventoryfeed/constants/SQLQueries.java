package com.litmus7.inventoryfeed.constants;

public class SQLQueries {
	public static final String INSERT_PRODUCT = "INSERT INTO Inventory (sku, productName, quantity, price) VALUES (?, ?, ?, ?)";
	public static final String SELECT_ALL_PRODUCTS = "SELECT sku, productName, quantity, price FROM Inventory";
	public static final String SELECT_PRODUCT_BY_SKU = "SELECT sku, productName, quantity, price FROM Inventory WHERE sku = ?";
}
