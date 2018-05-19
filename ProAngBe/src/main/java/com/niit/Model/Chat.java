package com.niit.Model;

import org.hibernate.validator.constraints.NotEmpty;

public class Chat {

	private int id;
	/* @NotEmpty(message="enter the valid message to be sent(*)") */
	private String message;
	private String to;
	private String from;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Chat [message=" + message + ", to=" + to + "]";
	}
}
