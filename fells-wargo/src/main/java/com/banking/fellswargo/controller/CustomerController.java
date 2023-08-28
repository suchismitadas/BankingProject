package com.banking.fellswargo.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.fellswargo.exceptions.AccountException;
import com.banking.fellswargo.exceptions.CustomerException;
import com.banking.fellswargo.model.ChangePasswordRequest;
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.service.CustomerDetailsService;
import com.banking.fellswargo.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerDetailsService customerDetailService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllDetails() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.FOUND);
	}
	
	@PutMapping("/change-password")
	public ResponseEntity<?> updatePassword(@RequestParam long id, @RequestBody ChangePasswordRequest changepaswordrequest) throws Exception {
		boolean result = false;
		result = customerService.updatePassword(id, changepaswordrequest);
		if(result) {
			Response response = new Response();
			response.setMessage("Password had been updated succesfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;
		
	}
	@GetMapping("/check-verification")
	public ResponseEntity<?>checkCustomerVerification(@RequestParam long id){
		boolean result = customerService.isVerified(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	 
	@PostMapping("/approve-customer/{id}")
	public ResponseEntity<?>verifyCustomer(@PathVariable long id) throws CustomerException, AccountException{
//		System.out.println(id+"---");
		Customer customer = customerService.makeCustomerVerified(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping("/deny-customer/{id}")
	public ResponseEntity<?>denyCustomer(@PathVariable long id) {
		Customer customerDeleted = customerService.getCustomerById(id);
//		System.out.println(id+"-i am inside---");
		customerService.deleteCustomer(id);
		customerDetailService.deleteCustomerDetail(id);
		return new ResponseEntity<>("DONE", HttpStatus.OK);
	}
//	@PostMapping("/forget-password?{id}")
//    public void forgetpassword(@RequestParam(value="id")int id) {
////    	bool isvalidid = customerService.get
//		retur
//    }
    

}
