package com.banking.fellswargo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.repository.CustomerRepository;
import com.banking.fellswargo.service.CustomerService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

//	@Autowired
//	userController userC;
//	@Autowired
//	UserStatus us;
//
//	@Autowired
//	User user;
//	
	@Test
	public void createCustomerTest() {
		LocalDate date = LocalDate.now();
		Customer customer = new Customer("pass", false,false,date );
		//Mock database
		when(customerRepository.save(customer)).thenReturn(customer);

		assertEquals(customer.getPassword(), customerService.createCustomer(customer).getPassword());

	}
	@Test
	public void findAllCustomer() {
		LocalDate date = LocalDate.now();
		Customer customer = new Customer("pass", false,false,date );
		Customer customer2 = new Customer("pass", false,false,date );

		List<Customer> list = new ArrayList<>();
		list.add(customer);
		list.add(customer2);
		when(customerRepository.findAll()).thenReturn(list);
		assertEquals(2, customerService.getAllCustomers().size());
	}

	@Test
	public void getUsersTest() {

		assertEquals(1, 1);
	}












}
