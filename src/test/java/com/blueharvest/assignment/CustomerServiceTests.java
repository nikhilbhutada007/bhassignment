package com.blueharvest.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.repository.CustomerRepository;
import com.blueharvest.assignment.service.CustomerService;
import com.blueharvest.assignment.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
public class CustomerServiceTests {

	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration {
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
	}

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	@Before
	public void setUp() {
		Customer customer = new Customer();
		customer.setFirstName("Nikhil");
		customer.setLastName("Bhutada");
		customer.setCustomerId(1L);
		Optional<Customer> cust = Optional.of(customer);
		Mockito.when(customerRepository.findById(customer.getCustomerId())).thenReturn(cust);
	}

	@Test
	public void showDetails() {
		Optional<Customer> customer = customerService.showDetails(1L);
		Customer cust = customer.get();

		assertThat(cust.getFirstName()).isEqualTo("Nikhil");
	}

}
