

package com.banking.fellswargo.exceptions;

public class CustomerException extends Exception {

//	String prope
	private String property;
	private String code;
	public CustomerException(String message, String property, String code) {
		super(message);
		this.property = property;
		this.code = code;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
