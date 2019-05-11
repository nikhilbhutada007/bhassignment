package com.blueharvest.assignment.service;

import java.math.BigDecimal;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.Transaction.TransactionType;

public interface TransactionService {

	Transaction execute(Account account, Transaction transaction);

	Transaction execute(Long accountId, BigDecimal amount, TransactionType type);
}
