package com.blueharvest.assignment.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.PositiveOrZero;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model class representing Account. Fields are given reasonable defaults for
 * assignment.
 * 
 * @author nbhutada
 *
 */
@Entity
public class Account {

	public enum ACCOUNT_TYPE {
		CURRENT
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long accountId;

	private ACCOUNT_TYPE type = ACCOUNT_TYPE.CURRENT;

	@PositiveOrZero
	private volatile BigDecimal balance = BigDecimal.ZERO;

	private Currency currency = Currency.getInstance("USD");

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public ACCOUNT_TYPE getType() {
		return type;
	}

	public void setType(ACCOUNT_TYPE type) {
		this.type = type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	// Return the reverse sorted transactions showing most recent transaction on top
	public List<Transaction> getTransactions() {
		Collections.sort(transactions,
				Collections.reverseOrder((a, b) -> a.getTransactionId().compareTo(b.getTransactionId())));
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
