package com.banking.fellswargo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
         ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
//    
    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<?> passwordException(PasswordException ex, WebRequest request){
    	ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
    	return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<?> customerException(CustomerException ex, WebRequest request){
    	NewErrorDetail errorDetail = new NewErrorDetail(new Date(), ex.getMessage(), request.getDescription(false), ex.getProperty(), ex.getCode());
    	return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<?> accountException(AccountException ex, WebRequest request){
    	NewErrorDetail errorDetail = new NewErrorDetail(new Date(), ex.getMessage(), request.getDescription(false), ex.getProperty(), ex.getCode());
    	return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
//    @ExceptionHandler(UserNotFoundException.class)
}
