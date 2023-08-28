package com.banking.fellswargo.model;

public class Withdrawal {
	
	private int accountNumber;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	private int amount;
	private String remark = "withdrawal";
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Withdrawal(int id, int amount, String remark) {
		super();
		this.accountNumber = id;
		this.amount = amount;
		this.remark = remark;
	}
	
   	
}
