package com.banking.fellswargo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.banking.fellswargo.exceptions.AccountException;
import com.banking.fellswargo.exceptions.GlobalExceptionHandler;
import com.banking.fellswargo.exceptions.ResourceNotFoundException;
//import com.banking.fellswargo.jwt.JwtHelper;
import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.model.CustomerDetails;
import com.banking.fellswargo.model.RequestWrapper;
import com.banking.fellswargo.model.Response;
//import com.banking.fellswargo.model.JwtResponse;
import com.banking.fellswargo.service.AccountService;
//import com.banking.fellswargo.repository.CustomerDetailsRepository;
import com.banking.fellswargo.service.CustomerDetailsService;
import com.banking.fellswargo.service.CustomerService;
import com.banking.fellswargo.util.JwtUtil;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RestController
@CrossOrigin("*")
public class CustomerDetailsController {

	@Autowired
	private CustomerDetailsService customerService;

	@Autowired
	private CustomerService cusS;
	@Autowired
	private AccountService accountService;

	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;

	@Autowired
	private JwtUtil jwtHelper;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/customer-details")
	public ResponseEntity<List<CustomerDetails>> getAllDetails() {
		return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	@GetMapping("/customer-details/{id}")

	public ResponseEntity<CustomerDetails> getCustomerDetails(@PathVariable Long id) throws ResourceNotFoundException {
//		try {
	
		ResponseEntity<CustomerDetails> customerDetail = customerService.getCustomerDetailsById(id);
		
		return customerDetail;
	}

	@PostMapping("/register")
//	public ResponseEntity<?> createAccount(@Validated @RequestBody CustomerDetails detail, @Validated @RequestBody Customer customer) {
	public ResponseEntity<?> createAccount(@Validated @RequestBody RequestWrapper wrapper) throws Exception{
		CustomerDetails detail = wrapper.getCustomerDetails();
		
		Customer customer = wrapper.getCustomer();
		if(cusS.getCustomerByEmailId(customer.getEmailId())!=null) {
			throw new AccountException("Email already in use", "emailid", "DuplicateValue");
		};
//		System.out.println(detail+" "+customer);
		long id = 0;
		try {
			id = createAccountUtil(detail, customer);
		} catch(Exception e) {
		/*THROW NEW EXCEPTION("ACCOUNT NOT CREATED"*/
			throw new Exception("Account not created");

		}
		Response response;
		
		  response = new Response("Account created successfully", id);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	public long createAccountUtil(CustomerDetails receiveddetail, Customer receivedcustomer) {
	
//		Account account = new Account();
//		account.setCustomer(receivedcustomer);
//		receivedcustomer.getAccounts().add(account);
		
		Customer createdcustomer = cusS.createCustomer(receivedcustomer);
//		Account createdAccount = accountService.createAccount(account);
	
		receiveddetail.setCustomer(createdcustomer);
	
//		consle.
		receiveddetail.setId(createdcustomer.getId());
	
		CustomerDetails createdcustomerDetails = customerService.createCustomerDetail(receiveddetail);
		
		return createdcustomerDetails.getId();

	}

	@PutMapping("customer-details/{id}")
	public ResponseEntity<CustomerDetails> updateAccount(@PathVariable Long id, @RequestBody CustomerDetails customerDetails) throws Exception {
		return new ResponseEntity<>(customerService.updateCustomerDetail(id, customerDetails),HttpStatus.OK);
	}
//	
//	@DeleteMapping("/accounts/{id}")
//	public Map<String, String> deleteAccount(@PathVariable Integer id){
//		return accountService.deleteAccounts(Integer id);
//	}
}
