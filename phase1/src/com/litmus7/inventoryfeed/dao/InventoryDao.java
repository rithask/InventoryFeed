package com.litmus7.inventoryfeed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.inventoryfeed.constants.SQLQueries;
import com.litmus7.inventoryfeed.exceptions.DatabaseException;
import com.litmus7.inventoryfeed.model.Product;
import com.litmus7.inventoryfeed.util.DatabaseConnectionUtil;

public class InventoryDao {
	public boolean createProduct(Product product) throws SQLException {
		DatabaseConnectionUtil.createTableIfNotExists();

		try (Connection connection = DatabaseConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_PRODUCT)) {
			preparedStatement.setInt(1, product.getSku());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setFloat(4, product.getPrice());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DatabaseException("Failed to insert product into the database: " + e.getMessage());
		}
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		try (Connection connection = DatabaseConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SELECT_ALL_PRODUCTS);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int sku = resultSet.getInt("sku");
				String productName = resultSet.getString("productName");
				int quantity = resultSet.getInt("quantity");
				float price = resultSet.getFloat("price");

				products.add(new Product(sku, productName, quantity, price));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to fetch products from the database: " + e.getMessage());
		}

		return products;
	}

	public boolean createProducts(List<Product> products) throws SQLException {
		DatabaseConnectionUtil.createTableIfNotExists();

		Connection connection = null;
		try {
			connection = DatabaseConnectionUtil.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_PRODUCT)) {
				for (Product product : products) {

					preparedStatement.setInt(1, product.getSku());
					preparedStatement.setString(2, product.getProductName());
					preparedStatement.setInt(3, product.getQuantity());
					preparedStatement.setFloat(4, product.getPrice());
					preparedStatement.addBatch();
				}
				preparedStatement.executeBatch();
			}

			connection.commit();
			return true;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					throw new DatabaseException("Failed to rollback transcation: " + e.getMessage());
				}
			}
			throw new DatabaseException("Error inserting product into the database: " + e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DatabaseException("Failed to close database connection: " + e.getMessage());
				}
			}
		}
	}
}
