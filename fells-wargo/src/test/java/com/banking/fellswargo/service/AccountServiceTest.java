package com.banking.fellswargo.service;
//package com.wellsfargo.onlinebanking.servicetests;

import java.util.List;
import java.util.Optional;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.repository.AccountRepository;
import com.banking.fellswargo.service.AccountService;

//import com.wellsfargo.onlinebanking.entity.User;
//import com.wellsfargo.onlinebanking.exception.UserAlreadyExistsException;
//import com.wellsfargo.onlinebanking.repository.UserRepository;
//import com.wellsfargo.onlinebanking.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackages = "com.wellsfargo.onlinebanking.service")
@ComponentScan(basePackages = "com.banking.fellswargo.service")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountServiceTest {

	@Autowired
    private MockMvc mockMvc;

	@InjectMocks
	private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void init() {
    	Account account = new Account(123, "saving", "ifc", "opening");

    	when(accountRepository.save(any(Account.class))).thenReturn(account);
    }

    @Test
    public void createAccountTest() {
    	Account newAccount = new Account(123, "saving", "ifc", "opening");
    	Account createdAccount = accountService.createAccount(newAccount);
    	assertEquals(newAccount.getBalance(), createdAccount.getBalance());


    }

    @Test
    public void getAllAccountTest() {
    	Account account2 = new Account(345, "currnt", "isafd", "opening");
    	account2.setAccountNumber(123);
    	Account createdAccount =accountService.createAccount(account2);
    	List<Account>inputList = new ArrayList<>();
    	inputList.add(account2);
    	when(accountRepository.findAll()).thenReturn(inputList);
    	List<Account> accountList = accountService.getAllAccounts();
    	assertEquals(accountList.size(), 1);
    }

    @Test
    public void findByCustomerIdTest() {
    	Account account2 = new Account(345, "currnt", "isafd", "opening");
    	when(accountRepository.findByCustomerId(any(Long.class))).thenReturn(account2);
    	assertEquals(accountService.getAccountByCustomer(120).getBalance(), account2.getBalance());


    }




}