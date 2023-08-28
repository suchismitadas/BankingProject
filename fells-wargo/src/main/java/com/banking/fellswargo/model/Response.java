package com.banking.fellswargo.model;

public class Response {
	
	private String message;
	private long id;
	public Response() {
		
	}
	
	public Response(String mesage, long id) {
		this.message  = mesage;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String  getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
