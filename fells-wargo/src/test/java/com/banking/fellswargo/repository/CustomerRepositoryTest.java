package com.banking.fellswargo.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.repository.AccountRepository;
import com.banking.fellswargo.repository.CustomerRepository;

//import com.wellsfargo.onlinebanking.entity.Account;
//import com.wellsfargo.onlinebanking.repository.AccountRepository;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.banking.fellswargo.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	public CustomerRepository customerRepository;

	@Test
	public void customerSave() {

		LocalDate date = LocalDate.now();
		Customer testCustomer = new Customer("Password@1", false, false, date);

		Customer savedCustomer = customerRepository.save(testCustomer);

		assertNotNull(savedCustomer);
	}

	@Test
	public void findAllCustomerTest() {
		LocalDate date = LocalDate.now();
		Customer testCustomer1 = new Customer("Password@1", false, false, date);
		Customer testCustomer2 = new Customer("Pasword@1", false, false, date);
		Customer savedCustomer = customerRepository.save(testCustomer1);
		Customer savedCustomer2 = customerRepository.save(testCustomer2);
		List<Customer> findcustomer = customerRepository.findAll();

		assertEquals(2, findcustomer.size());

	}
//	
	@Test
	public void findByEmailTest () {
		LocalDate date = LocalDate.now();
		Customer testCustomer1 = new Customer("Password@1", false, false, date);
		testCustomer1.setEmailId("tushar@gmail.com");
		Customer savedCustomer = customerRepository.save(testCustomer1);
		Customer receivedCustomer = customerRepository.findByEmailId("tushar@gmail.com");
		assertEquals(receivedCustomer.getEmailId(), "tushar@gmail.com");
	}
}