package com.banking.fellswargo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.fellswargo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//	@Quecry("insert into CUSTOMER VALUES({ID}, 1234, 12") 
	public Optional<Customer> findById(int id);
	Customer findByEmailId(String email);
}
