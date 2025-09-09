package com.litmus7.inventoryfeed.exceptions;

public class DatabaseException extends RuntimeException {

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
