package com.banking.fellswargo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.fellswargo.exceptions.CustomerException;
import com.banking.fellswargo.model.AuthRequest;
import com.banking.fellswargo.model.AuthResponse;
import com.banking.fellswargo.model.Customer;
import com.banking.fellswargo.model.Response;
import com.banking.fellswargo.service.CustomerService;
//import com.banking.fellswargo.model.JwtRequest;
//import com.banking.fellswargo.model.JwtResponse;
import com.banking.fellswargo.util.JwtUtil;
//import com.banking.fellswargo.jwt.JwtHelper;
//import java.util.*
@RestController
//@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomerService customerService;
   
  
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getId(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        Customer customer = customerService.getCustomerById(Long.parseLong(authRequest.getId()));
        if(customer.isVerifiedUser()==false) {
        	throw new CustomerException("Customer not verifeid. Please wait for admin approval/kyc", "Custmoer id", "not verified");
        }
        String token =  jwtUtil.generateToken(authRequest.getId());
//        System.out.println(token);
        Response response = new Response(token,Integer.parseInt(authRequest.getId()) );
//        System.out.println(response);
//        return response;
//        return token;
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//        return new ResponseEntity<>(new AuthResponse(token, true), HttpStatus.ACCEPTED);
    }

    
    
    @GetMapping(value = "/validate")
	public boolean getValidation(@RequestHeader("Authorization") String token){
		token = token.substring(7);
		AuthResponse auth = new AuthResponse();
	
		//log.info("Token validation for "+jwtUtil.extractUsername(token));
		
		if(jwtUtil.validateToken(token)) {
			
			System.out.println("Token validated");
			return true;
		}
		else {
			System.out.println("Token NOT validated");
			return false;

	}
    }






 

}
