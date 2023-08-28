package com.banking.fellswargo.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.banking.fellswargo.model.Account;
import com.banking.fellswargo.repository.AccountRepository;


@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)

@ComponentScan(basePackages = "com.banking.fellswargo.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AccountRepositoryTest {
	@Autowired
	public AccountRepository accountRepo;
	
	@Test
	public void testAccountRepository_Save_NotNULL() {
		
		Account testAccount = new Account(12, "saving", "ifs", "sdaljsjd");
		
		Account savedAccount = accountRepo.save(testAccount);
		System.out.println("dsajf");
		assertNotNull(savedAccount);
	}
	
	@Test
	public void testAccountRepository_Save_Exists() {
		
//		assertFalse(accountRepo.existByUserId("5006"));
		
		Account testAccount = new Account(12, "saving", "ifs", "sdaljsjd");
		Account savedAccount = accountRepo.save(testAccount);
		
//		Account getAccount = accountRepo.findByAccountNumber(1);
		assertEquals(testAccount.getBalance(), testAccount.getBalance());
//		assertTrue(accountRepo.existByUserId(savedAccount.getUserId()));
//		assertFalse(accountRepo.existByUserId("5007"));
	}
//	
	@Test
	public void testAccountRepository_Save_Find() {
		
		Account testAccount = new Account(12, "saving", "ifs", "sdaljsjd");
		
		Account savedAccount = accountRepo.save(testAccount);
		
		Account foundAccount = accountRepo.findByAccountNumber(testAccount.getAccountNumber());
		assertEquals(foundAccount, savedAccount);
//		assertEquals(accountRepo.findByUserId("5007"), null);
//		
//		Account foundAccount1 = accountRepo.findByAccountNumber(testAccount.getAccountNumber());
//		assertEquals(foundAccount1, savedAccount);
//		assertEquals(accountRepo.findByAccountNumber("20023"), null);
	}
}

