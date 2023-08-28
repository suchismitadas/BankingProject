package com.banking.fellswargo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.fellswargo.exceptions.AccountException;
import com.banking.fellswargo.exceptions.CustomerException;
import com.banking.fellswargo.exceptions.PasswordException;
import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.model.ChangePasswordRequest;
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountService accountService;
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer getCustomerByEmailId(String email) {
		return customerRepository.findByEmailId(email);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		Customer user = customerRepository.findById(Long.valueOf(username));
//		System.out.println(username);
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
		Customer customer = customerRepository.findById(Long.valueOf(username)).orElseThrow(()-> new RuntimeException("username not found"));
		return new org.springframework.security.core.userdetails.User(String.valueOf(customer.getId()), customer.getPassword(), new ArrayList<>());
//		return customer;
	}
//	public Customer get

	public boolean updatePassword(long id, ChangePasswordRequest changepaswordrequest) throws Exception{
		// TODO Auto-generated method stub
		if(changepaswordrequest.getNewPassword().equals(changepaswordrequest.getOldPassword()))
			throw new PasswordException("You already know your password");
		Customer customer = getCustomerById(id);
		if(!customer.getPassword().equals(changepaswordrequest.getOldPassword())) {
			
			throw new PasswordException("Old password does not maptch");
		}
		customer.setPassword(changepaswordrequest.getNewPassword());
		
		Customer updatedCustomer = customerRepository.save(customer);
		return true;
//		return new ResponseEntity<>(, HttpStatus.ACCEPTED);
	}

	public Customer getCustomerById(long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id).get();
//		return null;
	}

	public boolean isVerified(long id) {
	
		Customer customer  = getCustomerById(id);
		if(customer==null)
			return false;
		return customer.isVerifiedUser();
	}

	public Customer makeCustomerVerified(long id) throws CustomerException, AccountException {
		Customer customer  = getCustomerById(id);
		if(customer==null)
			throw new CustomerException("Customer not found", "Custoemrid", "not found");
		customer.setVerifiedUser(true);
	
		Account account = new Account();
		account.setCustomer(customer);
		customer.getAccounts().add(account);

		Account createdAccount = accountService.createAccount(account);
		if(createdAccount==null)
				throw new AccountException("account not created", "verification", "error");
		return createCustomer(customer);
	
		
//		return null;
	}

	public void deleteCustomer(long id) {
		// TODO Auto-generated method stub
		 customerRepository.deleteById(id);
//		 customer.dele
//		return null;
	}
	
//	public Set<Account> getAccounts(long id) {
//		return customerRepository.findById().getAcc
//	}

}
