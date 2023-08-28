package com.banking.fellswargo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.fellswargo.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	public List<Transaction> findByFromAccountOrToAccount(int fromAccount, int toAccount);
	public List<Transaction> findByType(String type);
}
