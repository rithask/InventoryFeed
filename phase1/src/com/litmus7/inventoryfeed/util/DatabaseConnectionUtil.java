package com.litmus7.inventoryfeed.util;

import com.litmus7.inventoryfeed.constants.DatabaseConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DatabaseConstants.DB_URL, DatabaseConstants.DB_USER, DatabaseConstants.DB_PASSWORD);
	}
}
