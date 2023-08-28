package com.banking.fellswargo.model;



public class AuthResponse {
	
	private String token;
	private boolean isValid;
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String setToken() {
		return token;
	}
	public void setToken(String username) {
		this.token = username;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthResponse(String username, boolean isValid, long id) {
		super();
		this.token = username;
		this.isValid = isValid;
		this.id = id;
	}
	@Override
	public String toString() {
		return "AuthResponse [token=" + token + ", isValid=" + isValid + "]";
	}

	
	
	
	
}