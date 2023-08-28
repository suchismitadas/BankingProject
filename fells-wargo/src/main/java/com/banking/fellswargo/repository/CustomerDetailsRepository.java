package com.banking.fellswargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.fellswargo.model.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long>{

}
