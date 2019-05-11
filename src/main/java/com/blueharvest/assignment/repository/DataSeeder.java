package com.blueharvest.assignment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.blueharvest.assignment.model.Customer;

/**
 * This class creates few static users for use in the application.
 * 
 * @author nbhutada
 *
 */
@Component
public class DataSeeder implements ApplicationRunner {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Customer customer1 = new Customer();
		customer1.setFirstName("John");
		customer1.setLastName("Doe");
		customerRepository.saveAndFlush(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("Jane");
		customer2.setLastName("Doe");
		customerRepository.saveAndFlush(customer2);
	}

}
