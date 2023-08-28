package com.banking.fellswargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue
	private int accountNumber;
	
	@Column(name="balance")
	private int balance = 0;
	
	@Column(name="type")
	private String type = "savings";
	
	@Column(name="ifsc_code")
	private String ifscCode = "fells0001";
	
	@Column(name="status")
	private String status = "open";
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Account() {
		
	}
	public Account(int balance, String type, String ifscCode, String status) {
		super();
		this.balance = balance;
		this.type = type;
		this.ifscCode = ifscCode;
		this.status = status;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", type=" + type + ", ifscCode="
				+ ifscCode + ", status=" + status + "]";
	}
	
	
	
}
