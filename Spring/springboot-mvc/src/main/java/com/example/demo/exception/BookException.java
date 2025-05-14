package com.example.demo.exception;

public class BookException extends Exception{
	
	// 自訂 BookException，可以輸入 message。
	public BookException(String message) {
		super(message);
	}
}
