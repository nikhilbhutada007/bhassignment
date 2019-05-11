package com.blueharvest.assignment.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blueharvest.assignment.exception.IncompletePayloadException;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.Transaction.TransactionType;
import com.blueharvest.assignment.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	/**
	 * API to perform the transaction on accounts. Transactions can be DEBIT and
	 * CREDIT.
	 * 
	 * @param transactionDetails contains 3 keys accountId, amount, and type
	 * @return <code>Transaction<code> if successful
	 */
	@PostMapping(value = "/transact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> transact(@RequestBody final Map<String, String> transactionDetails) {
		String arg1 = transactionDetails.get("accountId");
		String arg2 = transactionDetails.get("amount");
		String arg3 = transactionDetails.get("type");
		if (StringUtils.isEmpty(arg1) || StringUtils.isEmpty(arg2) || StringUtils.isEmpty(arg3))
			throw new IncompletePayloadException("Required fields are: accountId, amount, type [DEBIT/ CREDIT]");
		Long accountId = Long.parseLong(arg1);
		BigDecimal amount = new BigDecimal(arg2);
		TransactionType type = arg3.equalsIgnoreCase("DEBIT") ? TransactionType.DEBIT
				: arg3.equalsIgnoreCase("CREDIT") ? TransactionType.CREDIT : null;
		Transaction transaction = transactionService.execute(accountId, amount, type);
		return null == transaction ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
				: ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}
}
