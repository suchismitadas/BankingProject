package com.banking.fellswargo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.fellswargo.exceptions.AccountException;
import com.banking.fellswargo.exceptions.CustomerException;
import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.Customer;
//import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.Transaction;
import com.banking.fellswargo.model.Withdrawal;
import com.banking.fellswargo.repository.AccountRepository;
//import com.banking.fellswargo.repository.AccountRepository;
import com.banking.fellswargo.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
//	@Autowired
//	Account

	@Autowired
	AccountService accountService;
	@Autowired
	CustomerService customerService;
	
	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}
	
	public Transaction conductTrasaction(Transaction transaction) {
		// have to write the transaction logic
		accountService.updateAccount(transaction.getFromAccount(), -1*transaction.getAmount());
		accountService.updateAccount(transaction.getToAccount(), transaction.getAmount());
		return transactionRepository.save(transaction);
//		return transaction;
	}
	
	public List<Transaction> filterTransaction(int accountNumber){
//		System.out.println(accountNumber);
		
		return transactionRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);
	}
	public List<Transaction> getAllTransactionsByType(String type){
		return transactionRepository.findByType(type);
	}
	
	public boolean validateTransaction(Transaction transaction)  throws Exception{
//		System.out.println("i ma here");
		Account toAccount = accountService.getAccount(transaction.getToAccount());
		Account fromAccount = accountService.getAccount(transaction.getFromAccount());
		if(transaction.getAmount()<0) {
			throw new AccountException("Amount cannot be negative", "amount", "InvalidFormat");
		}
		if(toAccount==null)
			throw new AccountException("to account not found", "DestinationAccount", "RequiredField");
		if(fromAccount==null)
			throw new AccountException("from Account not found", "SourceAccount", "RequiredField");
		int amount = transaction.getAmount();
		if(amount>fromAccount.getBalance()) {
			throw new AccountException("Insufficient balance", "Amount", "OutOfRange");
		}
		return true;
	}

	public boolean validateWithdrawal(Withdrawal withdrawal) throws AccountException {
		
	    Account account = accountService.getAccount(withdrawal.getAccountNumber());
		if(account== null) {
			throw new AccountException("from Account not found", "SourceAccount", "RequiredField");
		}
		if(withdrawal.getAmount() > account.getBalance())
		{
			throw new AccountException("Insufficient balance", "Amount", "OutOfRange");
		}if(withdrawal.getAmount() < 0)
		{
			throw new AccountException("Amount cannot be negative", "amount", "InvalidFormat");
		}
//		Account account = customer.getAccounts().
		return true;
	}

	public Transaction conductWithdrawal(Withdrawal withdrawal) {
		
		Account account = accountService.updateAccount(withdrawal.getAccountNumber(),-1*withdrawal.getAmount());
		Transaction transaction = new Transaction(LocalDate.now(), "withdrawal", withdrawal.getAccountNumber(), withdrawal.getAccountNumber(), "completed", "withdrawal", account.getBalance());
		// TODO Auto-generated method stub
		transactionRepository.save(transaction);
		return transaction;
	}
}
