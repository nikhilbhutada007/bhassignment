package com.blueharvest.assignment.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model class representing Transaction. Fields are given reasonable defaults
 * for assignment.
 * 
 * @author nbhutada
 *
 */
@Entity
public class Transaction {

	public enum TransactionType {
		DEBIT, CREDIT, OPENING
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long transactionId;

	@NotNull
	private TransactionType type;

	@NotNull
	private BigDecimal amount = BigDecimal.ZERO;

	@NotNull
	private Instant date = Instant.now();

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
