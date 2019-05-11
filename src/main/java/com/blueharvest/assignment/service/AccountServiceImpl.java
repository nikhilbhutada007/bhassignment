package com.blueharvest.assignment.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueharvest.assignment.exception.ResourceNotFoundException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.Transaction.TransactionType;
import com.blueharvest.assignment.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionService transactionService;

	@Override
	public Account open(Long customerId, BigDecimal initialCredit) {
		Account account = null;
		Optional<Customer> cust = customerService.showDetails(customerId);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			account = accountRepository.save(new Account());
			if (0 != BigDecimal.ZERO.compareTo(initialCredit)) {
				Transaction transaction = new Transaction();
				transaction.setType(TransactionType.CREDIT);
				transaction.setAmount(initialCredit);
				transaction = transactionService.execute(account, transaction);
				account.getTransactions().add(transaction);
			}
			customer.getAccounts().add(account);
		} else
			throw new ResourceNotFoundException("Customer " + customerId + " is not present!");
		return account;
	}

	@Override
	public Optional<Account> showDetails(Long accountId) {
		return accountRepository.findById(accountId);
	}

}
