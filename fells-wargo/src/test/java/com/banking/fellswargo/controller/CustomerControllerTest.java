package com.banking.fellswargo.controller;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;

import com.banking.fellswargo.model.ChangePasswordRequest;
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.model.Response;
import com.banking.fellswargo.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.banking.fellswargo.controller")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerControllerTest {
private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CustomerService customerService;

	@Before
	public void init() {
		LocalDate date = LocalDate.now();
		Customer testCustomer = new Customer("Password@1", false, false, date);


		when(customerService.createCustomer(any(Customer.class))).thenReturn(testCustomer);

	}

	@Test
	public void testCreateUser() {
		LocalDate date = LocalDate.now();
		Customer testCustomer2 = new Customer("Password@1", false, false, date);
		List<Customer> cuList = new ArrayList<>();
		cuList.add(testCustomer2);
		when(customerService.getAllCustomers()).thenReturn(cuList);

			try {
				mockMvc.perform(get("/customers")

				        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				        .andExpect(jsonPath("$.[0]password", is(testCustomer2.getPassword())))
				        .andExpect(jsonPath("$.[0]emailId", is(testCustomer2.getEmailId())))
				        .andExpect(jsonPath("$.[0]internetBankingAllowed", is(testCustomer2.isInternetBankingAllowed())));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			verify(customerService,times(0)).createCustomer(any(Customer.class));

	}
	@Test 
	public void testPasswordChnage() {
		LocalDate date = LocalDate.now();
		Customer testCustomer2 = new Customer("Password@1", false, false, date);
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("OldPassowrd", "newPassword");
		Response res = new Response();
		res.setMessage("Password had been upded succesfully");
		try {
			when(customerService.updatePassword(any(Integer.class), changePasswordRequest)).thenReturn(true);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String message = "Password had been upded succesfully";
		try {
			mockMvc.perform(post("/change-password")
						.content(om.writeValueAsString(String.valueOf(12)))
					   .content(om.writeValueAsString(changePasswordRequest))
					   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
//						.andExpect(jsonPath("$.message", is(message)));

		}catch(Exception e) {
			System.out.println("-----------------");
			e.printStackTrace();
		}

	}




}