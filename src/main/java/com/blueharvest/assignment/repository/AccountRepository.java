package com.blueharvest.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueharvest.assignment.model.Account;

/**
 * Marker interface to query the database using Spring Data
 * 
 * @author nbhutada
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
