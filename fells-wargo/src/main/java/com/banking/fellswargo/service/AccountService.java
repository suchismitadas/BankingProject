package com.banking.fellswargo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}
	public Account updateAccount(Integer acc, Integer amount) {
		Account account = accountRepository.findByAccountNumber(acc);
		account.setBalance(account.getBalance()+amount);
		accountRepository.save(account);
		return account;
	}
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
//		List<Account> oldAccount = account.getCustomer().getAccounts();
		return accountRepository.save(account);
	}
	public Account getAccountByCustomer(long id) {
		return accountRepository.findByCustomerId(id);
	}
	public Account getAccount(int number) {
		return accountRepository.findByAccountNumber(number);
	}
	public void deleteAccount(Integer id) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(id);
		
	}
	
	
//	public Optional<Account> getAccount(Long id) {
//		return accountRepository.findById(id);
//	}
//	
//	public Account updateAccounts(Long id, @Valid @RequestBody Account account) {
//		Product updatedProduct = productRepo.findById(productId)
//				.orElseThrow(()-> new ResourceNotFoundException("Product is not avaialble:"+ productId));
//updatedProduct.setPname(changedPoduct.getPname());
//updatedProduct.setPrice(changedPoduct.getPrice());
//productRepo.save(updatedProduct);
//	}

}
