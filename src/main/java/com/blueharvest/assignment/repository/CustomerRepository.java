package com.blueharvest.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueharvest.assignment.model.Customer;

/**
 * Marker interface to query the database using Spring Data
 * 
 * @author nbhutada
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
