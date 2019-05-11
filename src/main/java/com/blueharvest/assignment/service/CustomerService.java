package com.blueharvest.assignment.service;

import java.util.Optional;

import com.blueharvest.assignment.model.Customer;

public interface CustomerService {

	Optional<Customer> showDetails(Long customerId);
}
