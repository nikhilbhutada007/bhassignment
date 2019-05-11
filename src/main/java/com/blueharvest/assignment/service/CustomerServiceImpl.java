package com.blueharvest.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Optional<Customer> showDetails(Long customerId) {
		return customerRepository.findById(customerId);
	}

}
