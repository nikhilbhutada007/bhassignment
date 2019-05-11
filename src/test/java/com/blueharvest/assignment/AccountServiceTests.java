package com.blueharvest.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
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

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.Transaction.TransactionType;
import com.blueharvest.assignment.repository.AccountRepository;
import com.blueharvest.assignment.service.AccountService;
import com.blueharvest.assignment.service.AccountServiceImpl;
import com.blueharvest.assignment.service.CustomerService;
import com.blueharvest.assignment.service.TransactionService;

@RunWith(SpringRunner.class)
public class AccountServiceTests {

	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {
		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}

	@Autowired
	private AccountService accountService;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private TransactionService transactionService;

	@Before
	public void setUp() {
		Account account = new Account();
		Transaction transaction = new Transaction();
		account.setBalance(new BigDecimal(100));
		account.setCurrency(Currency.getInstance("USD"));
		List<Transaction> transactions = new ArrayList<>(1);
		transactions.add(transaction);
		account.setTransactions(transactions);
		account.setAccountId(2L);
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

		transaction.setType(TransactionType.OPENING);
		transaction.setAmount(new BigDecimal(100));
		transaction.setDate(Instant.now());
		transaction.setTransactionId(3L);
		Mockito.when(transactionService.execute(Mockito.any(Account.class), Mockito.any(Transaction.class)))
				.thenReturn(transaction);

		Customer customer = new Customer();
		customer.setFirstName("Nikhil");
		customer.setLastName("Bhutada");
		customer.setCustomerId(1L);
		customer.setAccounts(new HashSet<>(Arrays.asList(account)));
		Optional<Customer> cust = Optional.of(customer);
		Mockito.when(customerService.showDetails(customer.getCustomerId())).thenReturn(cust);
	}

	@Test
	public void open() {
		Account account = accountService.open(1L, new BigDecimal(100));

		assertThat(account.getBalance()).isEqualTo(new BigDecimal(100));
	}

}
