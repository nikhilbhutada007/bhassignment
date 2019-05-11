package com.blueharvest.assignment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * API to get the customer details and associated accounts and transactions.
	 * 
	 * @param id customerId
	 * @return <code>Customer<code> information if present
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> showDetails(@PathVariable final String id) {
		Optional<Customer> customer = customerService.showDetails(Long.parseLong(id));
		return customer.isPresent() ? ResponseEntity.ok().body(customer.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
