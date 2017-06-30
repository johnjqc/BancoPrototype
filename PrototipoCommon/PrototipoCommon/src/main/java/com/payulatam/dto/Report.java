package com.payulatam.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for report results
 * @author john.quiroga
 *
 */
public class Report {

	private String accountNumber;
	
	private BigDecimal balance;
	
	private List<TotalTypesMovement> movements;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<TotalTypesMovement> getMovements() {
		if (movements == null) {
			movements = new ArrayList<>();
		}
		return movements;
	}

	public void setMovements(List<TotalTypesMovement> movements) {
		this.movements = movements;
	}
	
}
