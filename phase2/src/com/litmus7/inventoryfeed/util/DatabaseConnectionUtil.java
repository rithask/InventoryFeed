package com.litmus7.inventoryfeed.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.litmus7.inventoryfeed.constants.DatabaseConstants;
import com.litmus7.inventoryfeed.constants.SQLQueries;

public class DatabaseConnectionUtil {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DatabaseConstants.DB_URL, DatabaseConstants.DB_USER,
				DatabaseConstants.DB_PASSWORD);
	}

	public static void createTableIfNotExists() throws SQLException {
		Connection connection = DatabaseConnectionUtil.getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(SQLQueries.CREATE_TABLE);
		statement.close();
		connection.close();
	}
}
