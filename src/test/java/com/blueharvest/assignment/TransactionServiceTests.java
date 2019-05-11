package com.blueharvest.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;

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
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.Transaction.TransactionType;
import com.blueharvest.assignment.repository.TransactionRepository;
import com.blueharvest.assignment.service.AccountService;
import com.blueharvest.assignment.service.TransactionService;
import com.blueharvest.assignment.service.TransactionServiceImpl;

@RunWith(SpringRunner.class)
public class TransactionServiceTests {

	@TestConfiguration
	static class TransactionServiceImplTestContextConfiguration {
		@Bean
		public TransactionService transactionService() {
			return new TransactionServiceImpl();
		}
	}

	@Autowired
	private TransactionService transactionService;

	@MockBean
	private AccountService accountService;

	@MockBean
	private TransactionRepository transactionRepository;

	@Before
	public void setUp() {
		Transaction transaction = new Transaction();
		transaction.setAmount(new BigDecimal(100));
		transaction.setDate(Instant.now());
		transaction.setTransactionId(1L);
		transaction.setType(TransactionType.CREDIT);
		Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
	}

	@Test
	public void execute() {
		Transaction transaction = new Transaction();
		transaction.setAmount(new BigDecimal(100));
		transaction.setDate(Instant.now());
		transaction.setType(TransactionType.CREDIT);
		Account account = new Account();
		account.setBalance(new BigDecimal(100));
		transaction = transactionService.execute(account, transaction);

		assertThat(account.getBalance()).isEqualTo(new BigDecimal(200));
	}
}
