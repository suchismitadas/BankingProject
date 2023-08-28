package com.banking.fellswargo.model;


//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class AuthRequest {

    private String id;
    private String password;
	public String getId() {
		return id;
	}
	
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUserName(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
	
//	public 
    
    
    
}
