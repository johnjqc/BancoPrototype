package com.payulatam.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gigaspaces.annotation.pojo.SpaceClass;


/**
 * The persistent class for the account database table.
 * @author John
 */
@Entity
@Table(name="account")
@SpaceClass(persist=true)
public class Account extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Customer ID
	 */
	private String customerId;

	/**
	 * Blanace
	 */
	private BigDecimal balance;

	/**
	 * Number Account
	 */
	private String numberaccount;

	public Account() {
	}

	/**
	 * Get Customer Id
	 * @return String with Id
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * Set Customer Id
	 * @param customerId String
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Get Balance
	 * @return BigDecimal with Balance
	 */
	public BigDecimal getBalance() {
		return this.balance;
	}

	/**
	 * Set Balance
	 * @param balance BigDecimal
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * Get Number Account
	 * @return String with Account
	 */
	public String getNumberAccount() {
		return this.numberaccount;
	}

	/**
	 * Set Bumber Account
	 * @param number String
	 */
	public void setNumberAccount(String number) {
		this.numberaccount = number;
	}

}