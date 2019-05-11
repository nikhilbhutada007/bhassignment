package com.blueharvest.assignment.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.blueharvest.assignment.model.Account;

public interface AccountService {

	Account open(Long customerId, BigDecimal initialCredit);

	Optional<Account> showDetails(Long accountId);
}
