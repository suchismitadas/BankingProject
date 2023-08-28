package com.banking.fellswargo.model;

import java.time.LocalDate;
import java.util.Date;

//import jakarta.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="type")
	private String type;
	
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="from_account", referencedColumnName = "account_number")
	@NotNull
	@Column(name="fromAccount")
	private int fromAccount;
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="to_account", referencedColumnName = "account_number")
	@Column(name="toAccount")
	private int toAccount;
	
	@Column(name="status")
	private String status = "completed";
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="amount")
	private int amount;

	public Transaction() {
		
	}
	public Transaction(LocalDate date, String type, int fromAccount, int toAccount, String status,
			String remark) {
		super();
//		this.id = id;
		this.date = date;
		this.type = type;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.status = status;
		this.remark = remark;
	}

	public Transaction(LocalDate date, String type, @NotNull int fromAccount, int toAccount, String status,
			String remark, int amount) {
		super();
		this.date = date;
		this.type = type;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.status = status;
		this.remark = remark;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", type=" + type + ", fromAccount=" + fromAccount
				+ ", toAccount=" + toAccount + ", status=" + status + ", remark=" + remark + "]";
	}

	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
