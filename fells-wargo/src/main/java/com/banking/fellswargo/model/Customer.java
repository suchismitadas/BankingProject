package com.banking.fellswargo.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer  {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
//	@OneToOne
//	@MapsId
//	private CustomerDetails customer_details;
	
	@Column(name="password")
	private String password = "Password@123";
	
	@Column(name="email_id")
	private String emailId ;
	
	@Column(name="internet_banking_allowed")
	private boolean internetBankingAllowed = true;
	
	@Column(name="verified")
	private boolean verifiedUser = false;
	
	@Column(name="last_login")
	private LocalDate lastUserLogin;
	
	@OneToMany(mappedBy="customer")
	Set<Account> accounts;
	

	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", password=" + password + ", emailId=" + emailId + ", internetBankingAllowed="
				+ internetBankingAllowed + ", verifiedUser=" + verifiedUser + ", lastUserLogin=" + lastUserLogin
				+ ", accounts=" + accounts + "]";
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Customer() {
		this.accounts = new HashSet<>();
	}
//	public
	
	public Customer(String password, String emailId) {
		super();
		this.password = password;
		this.emailId = emailId;
	}

	public Customer(String password, boolean internetBankingAllowed, boolean verifiedUser, LocalDate lastUserLogin) {
		super();
		this.password = password;
		this.internetBankingAllowed = internetBankingAllowed;
		this.verifiedUser = verifiedUser;
		this.lastUserLogin = lastUserLogin;
		this.accounts = new HashSet<>();
	}
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isInternetBankingAllowed() {
		return internetBankingAllowed;
	}
	public void setInternetBankingAllowed(boolean internetBankingAllowed) {
		this.internetBankingAllowed = internetBankingAllowed;
	}
	public boolean isVerifiedUser() {
		return verifiedUser;
	}

	public void setVerifiedUser(boolean verifiedUser) {
		this.verifiedUser = verifiedUser;
	}
	public LocalDate getLastUserLogin() {
		return lastUserLogin;
	}
	public void setLastUserLogin(LocalDate lastUserLogin) {
		this.lastUserLogin = lastUserLogin;
	}
	
	
}
