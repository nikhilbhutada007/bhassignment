package com.blueharvest.assignment.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.Transaction.TransactionType;
import com.blueharvest.assignment.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public synchronized Transaction execute(Account account, Transaction transaction) {
		switch (transaction.getType()) {
		case OPENING:
		case CREDIT:
			account.setBalance(account.getBalance().add(transaction.getAmount()));
			transaction = transactionRepository.save(transaction);
			break;
		case DEBIT:
			account.setBalance(account.getBalance().subtract((transaction.getAmount())));
			transaction = transactionRepository.save(transaction);
			break;
		}
		return transaction;
	}

	@Override
	public synchronized Transaction execute(Long accountId, BigDecimal amount, TransactionType type) {
		Optional<Account> acc = accountService.showDetails(accountId);
		if (acc.isPresent()) {
			Account account = acc.get();
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setType(type);
			transaction = execute(account, transaction);
			account.getTransactions().add(transaction);
			return transaction;
		} else
			return null;
	}

}
