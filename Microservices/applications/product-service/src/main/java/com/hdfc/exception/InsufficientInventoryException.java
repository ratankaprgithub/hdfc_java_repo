package com.hdfc.exception;

public class InsufficientInventoryException extends RuntimeException {

	public InsufficientInventoryException(String message) {

		super(message);
	}
}