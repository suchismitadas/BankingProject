package com.banking.fellswargo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.fellswargo.exceptions.AccountException;
import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.Customer;
//import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.Transaction;
import com.banking.fellswargo.model.Withdrawal;
import com.banking.fellswargo.service.CustomerService;
//import com.banking.fellswargo.service.AccountService;
import com.banking.fellswargo.service.TransactionService;

@RestController
@CrossOrigin("*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CustomerService cusS;
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>>getAllDetails() {
		return new ResponseEntity<>(transactionService.getAllTransactions(),HttpStatus.FOUND);
	}
	
	@GetMapping("/transactions?{type}")
	public ResponseEntity<List<Transaction>> getAllDetailsByType(@RequestParam(value="type") String type){
		return new ResponseEntity<>( transactionService.getAllTransactionsByType(type), HttpStatus.FOUND);
	}
	
	@CrossOrigin("*")
	@PostMapping("/transactions")
	public ResponseEntity<Transaction> updateTransaction(@Validated @RequestBody Transaction transaction) throws Exception {
		System.out.println("call");
		 boolean isValid = transactionService.validateTransaction(transaction);
		if(isValid)
			return new ResponseEntity<>(transactionService.conductTrasaction(transaction),HttpStatus.CREATED);

		return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		
	}
	
	@PostMapping
	("transactions/withdrawal")
	public ResponseEntity<Transaction> withdrawal(@Validated @RequestBody Withdrawal withdrawal) throws AccountException {
		
		boolean isValid = transactionService.validateWithdrawal(withdrawal);
		if(isValid) {
			Transaction transaction = transactionService.conductWithdrawal(withdrawal); 
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		}
		return null;
	}
	@GetMapping("/transactions/{account}")
	@CrossOrigin
	public ResponseEntity<?> filter(@Validated @PathVariable(value="account")  Integer account)
{
//		System.out.println("i am here"+account);
		Customer customer = cusS.getCustomerById(account);
//		System.out.println(customer);
		Set<Account> accounts = customer.getAccounts();
//		System.out.println(accounts);
		List<Transaction> resultData = new ArrayList<>();
		for(Account accout : accounts) {
			resultData.addAll(transactionService.filterTransaction(accout.getAccountNumber()));
//			System.out.println(transactionService.filterTransaction(accout.getAccountNumber()));
		}
//		System.out.println(resultData);
//		return resultData;
//		return new ResponseEntity<>(null, HttpStatus.FOUND);
		return new ResponseEntity<>(resultData, HttpStatus.OK);
	}
}
