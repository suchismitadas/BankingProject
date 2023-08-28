package com.banking.fellswargo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.banking.fellswargo.model.CustomerDetails;
import com.banking.fellswargo.service.CustomerDetailsService;

//@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerDetailsServiceTest {

	CustomerDetailsService customerDetailService;

	@BeforeAll
	void creatSErvice() {
		customerDetailService = new CustomerDetailsService();
	}

	@Test
	void countAddmition() {
		CustomerDetails customerDetail = new CustomerDetails();
		customerDetailService.createCustomerDetail(customerDetail);

        assertEquals(1, customerDetailService.getAllCustomers().size());
	}

	@Test
	void updateCustomerDetail() throws Exception {
		CustomerDetails customerDetail = new CustomerDetails();
		customerDetail.setAadhar("1234");
		customerDetail = customerDetailService.createCustomerDetail(customerDetail);

		customerDetail.setAadhar("4321");
		CustomerDetails updatedcustomerDetail = customerDetailService.updateCustomerDetail(customerDetail.getId(), customerDetail);
		CustomerDetails newCustomerDetail=null;
		try {
		 newCustomerDetail = customerDetailService.getCustomerDetailsById(customerDetail.getId()).getBody();
		}catch(Exception e) {
			assertFalse(true);
		}
        assertEquals("4321", newCustomerDetail.getAadhar());

	}

}