package com.sp.error;

public class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public MyRuntimeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
