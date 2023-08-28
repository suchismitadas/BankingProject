package com.banking.fellswargo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.banking.fellswargo.exceptions.ResourceNotFoundException;
//import com.banking.fellswargo.jwt.JwtAuthenticationFilter;
//import com.banking.fellswargo.jwt.JwtHelper;
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.model.CustomerDetails;
//import com.banking.fellswargo.model.JwtResponse;
import com.banking.fellswargo.repository.CustomerDetailsRepository;
import com.banking.fellswargo.util.JwtUtil;

@Service
public class CustomerDetailsService {

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	@Autowired
	JwtUtil jwtHelper;
	@Autowired
	CustomerService customerService;
	
	public List<CustomerDetails> getAllCustomers() {
		return customerDetailsRepository.findAll();
	}
	
//	public Optional<CustomerDetails> getCustomerDetail(Long id){
//		  return  customerDetailsRepository.findById(id);
//		
//	}
	 public ResponseEntity < CustomerDetails > getCustomerDetailsById(Long employeeId)
			    throws ResourceNotFoundException {
			        CustomerDetails customer = customerDetailsRepository.findById(employeeId)
			            .orElseThrow(() -> new ResourceNotFoundException("CustomerDetail not found for this id :: " + employeeId));
			        return ResponseEntity.ok().body(customer);
			    }
	 
	public CustomerDetails createCustomerDetail(CustomerDetails detail) {
		
		CustomerDetails result =  customerDetailsRepository.save(detail);
		
//		
		return result;
		}
	
	public CustomerDetails updateCustomerDetail(Long id, CustomerDetails detail) throws Exception{
		Optional<CustomerDetails> updatedCustomer = customerDetailsRepository.findById(id);
//		if updatedCUsotme
		if(updatedCustomer.isPresent()==false) {
			throw new ResourceNotFoundException("Customer not found");
	
		}
		CustomerDetails cd = updatedCustomer.get();
		cd.setAadhar(detail.getAadhar());
		cd.setDob(detail.getDob());
		cd.setFirstName(detail.getFirstName());
		cd.setId(detail.getId());
		cd.setLastName(detail.getLastName());
		cd.setMobileNumber(detail.getMobileNumber());
		customerDetailsRepository.save(cd);
		return cd;
		
	}
	
	public void deleteCustomerDetail(long id) {
		// TODO Auto-generated method stub
		customerDetailsRepository.deleteById(id);
//		 customerRepository.dele
//		return null;
	}
}
