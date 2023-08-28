package com.banking.fellswargo.model;

import java.time.LocalDate;
import com.banking.fellswargo.model.Customer;
//import java.util.Date;
import java.util.Objects;

//import jakarta.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import jakarta.validation.constraints.Size;
import lombok.Builder;

@Entity
@Table(name="customer_details")
@Builder
public class CustomerDetails {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	@Column(name = "first_name", nullable=false, length=30)
	private String firstName;
	
	@Column(name = "last_name", nullable=false, length=30)
	private String lastName;
	
	@Column(name = "address", nullable=false, length=30)
	private String permanentAddress;
	
//	@Size(min=10, max=10)
	@Column(name = "mobile", length=10)
	private String mobileNumber;
	
//	@Size(min=12, max=12)
	@Column(name = "aadhar", nullable=false, length=12)
	private String aadhar;
	
//	@Size(min=10, max=10)
	@Column(name = "pan", nullable=false, length=10)
	private String pan;
	
	@Column(name = "dob")
	private LocalDate dob;
	
	@Column(name = "occupation", nullable=false)
	private String occupation;

	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		
		this.customer = customer;
	}


	public CustomerDetails() {
		
	}


	public CustomerDetails(String firstName, String lastName, String permanentAddress, String mobileNumber, String aadhar,
			String pan, LocalDate dob, String occupation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.permanentAddress = permanentAddress;
		this.mobileNumber = mobileNumber;
		this.aadhar = aadhar;
		this.pan = pan;
		this.dob = dob;
		this.occupation = occupation;
	
	}



	@Override
	public String toString() {
		return "CustomerDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", permanentAddress=" + permanentAddress + ", mobileNumber=" + mobileNumber + ", aadhar=" + aadhar
				+ ", pan=" + pan + ", dob=" + dob + ", occupation=" + occupation + ", customer=" + customer + "]";
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getOccupation() {
		return occupation;
	}
 	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	@Override
	public int hashCode() {
		return Objects.hash(aadhar, dob, firstName, id, lastName, mobileNumber, occupation, pan, permanentAddress);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetails other = (CustomerDetails) obj;
		return aadhar == other.aadhar && Objects.equals(dob, other.dob) && Objects.equals(firstName, other.firstName)
				&& id == other.id && Objects.equals(lastName, other.lastName) && mobileNumber == other.mobileNumber
				&& Objects.equals(occupation, other.occupation) && pan == other.pan
				&& Objects.equals(permanentAddress, other.permanentAddress);
	}
	
	
	
	
}
