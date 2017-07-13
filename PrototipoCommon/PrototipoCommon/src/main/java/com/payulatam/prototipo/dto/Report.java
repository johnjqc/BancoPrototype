package com.payulatam.prototipo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for report results
 * @author john.quiroga
 *
 */
public class Report {

	/**
	 * Numeo de cuenta
	 */
	private String accountNumber;
	
	/**
	 * Saldo de cuenta
	 */
	private BigDecimal balance;
	
	/**
	 * Total de movimientos por tipo
	 */
	private List<TotalTypesMovement> movements;
 
	/**
	 * Get Account Number
	 * @return String with account
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Set Account number
	 * @param accountNumber String
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Get Balance
	 * @return BigDecimal with Balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Set Balance
	 * @param balance BigDecimal
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * Get Movements
	 * @return List<TotalTypesMovement> with movement types
	 */
	public List<TotalTypesMovement> getMovements() {
		if (movements == null) {
			movements = new ArrayList<>();
		}
		return movements;
	}

	/**
	 * Set Movement types
	 * @param movements List<TotalTypesMovement>
	 */
	public void setMovements(List<TotalTypesMovement> movements) {
		this.movements = movements;
	}
	
}
