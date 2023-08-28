package com.banking.fellswargo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.fellswargo.exceptions.AccountException;
import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.Customer;
//import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.service.AccountService;
import com.banking.fellswargo.service.CustomerDetailsService;
import com.banking.fellswargo.service.CustomerService;
//import com.banking.fellswargo.service.CustomerService;

@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllDetails() {
		return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.FOUND);
	}
	
//	@CrossOrigin("*")
	@GetMapping("/accounts/{id}")
	public ResponseEntity<?> getAccountDetails(@PathVariable Integer id) {
//		Console.log(
//		System.out.println("asdfad---");
		Customer customer = customerService.getCustomerById(id);
//		customer.getAccounts()
//		Account account = accountService.getAccount(id);
//		if(!account.isPresent()) {
//			System.out.println("Account is not present");
//			return null;
//		}
		return new ResponseEntity<>(customer.getAccounts(), HttpStatus.ACCEPTED);
	}
//	
	@PostMapping("/accounts?{id}&{type}")
	public ResponseEntity<Account> createAccount(@Valid @RequestParam(value="id") long id, @Valid @RequestParam(value="type") String type) throws Exception {
		Customer customer = customerService.getCustomerById(id);
		if(customer==null)
			throw new Exception("Customer not found");
		Set<Account> accounts = customer.getAccounts();
		for(Account acc : accounts) {
			if(acc.getType().equals(type)) {
				throw new AccountException("Accout type already exist", "type", "Duplicate");
			}
		}
		Account account = new Account();
		account.setCustomer(customer);
		account.setType(type);
		customer.getAccounts().add(account);
		customerService.createCustomer(customer);
		
		
		return new ResponseEntity<>( accountService.createAccount(account), HttpStatus.CREATED);
//		return account;
	}
//	
//	@PutMapping("/accounts/{id}")
//	public Account updateAccount(@PathVariable Integer id, @Valid @RequestBody Account account ) {
//		return accountService.updateAccounts(Integer id, Account account);
//	}
//	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable Integer id) throws AccountException{
		Account account = accountService.getAccount(id);		
		if(account ==null)
			throw new AccountException("account not found", "account id", "id");
		Set<Account> accounts = account.getCustomer().getAccounts();
		accountService.deleteAccount(id);
		
		if(accounts.size() == 0) {
			
			customerService.deleteCustomer(account.getCustomer().getId());
			customerDetailsService.deleteCustomerDetail(account.getCustomer().getId());
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
}
