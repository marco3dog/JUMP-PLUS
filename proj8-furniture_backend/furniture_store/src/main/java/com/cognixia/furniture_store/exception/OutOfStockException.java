package com.cognixia.furniture_store.exception;

public class OutOfStockException extends Exception{
    private static final long serialVersionUID = 1L;

    	public OutOfStockException(String msg) {
		// calls the Exception(String msg) constructor
		super(msg);
	}
}
