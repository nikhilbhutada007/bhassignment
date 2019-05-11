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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueharvest.assignment.exception.IncompletePayloadException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * API to open the current account of already existing customer.
	 * 
	 * @param accountDetails with the 2 keys customerId, initialCredit
	 * @return <code>Account<code> if creation is successful
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> openAccount(@RequestBody final Map<String, String> accountDetails) {
		String arg1 = accountDetails.get("customerId");
		String arg2 = accountDetails.get("initialCredit");
		if (StringUtils.isEmpty(arg1) || StringUtils.isEmpty(arg2))
			throw new IncompletePayloadException("Required fields are: customerId, initialCredit");
		Long customerId = Long.parseLong(arg1);
		BigDecimal initialCredit = new BigDecimal(arg2);
		Account account = accountService.open(customerId, initialCredit);
		return ResponseEntity.status(HttpStatus.CREATED).body(account);
	}

}
