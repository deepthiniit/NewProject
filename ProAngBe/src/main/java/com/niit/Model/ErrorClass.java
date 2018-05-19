package com.niit.Model;//for returning meaning ful error messsage in angular js client

public class ErrorClass {
	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorClass(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
