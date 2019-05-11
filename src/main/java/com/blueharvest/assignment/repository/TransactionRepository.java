package com.blueharvest.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueharvest.assignment.model.Transaction;

/**
 * Marker interface to query the database using Spring Data
 * 
 * @author nbhutada
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
