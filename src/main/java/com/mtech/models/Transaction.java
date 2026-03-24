package com.mtech.models;

import java.util.List;

public class Transaction {
	private Long userId;
	private double amount;

	public Transaction(Long userId, double amount) {
		this.userId = userId;
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}
	public double getAmount() {
		return amount;
	}

	public static List<Transaction> getTransactions() {
		List<Transaction> transactions = List.of(new Transaction(1L, 100),
				new Transaction(2L, 200), new Transaction(1L, 300),
				new Transaction(2L, 150), new Transaction(3L, 500));
		return transactions;
	}
}