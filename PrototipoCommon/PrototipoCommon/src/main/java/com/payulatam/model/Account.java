package com.payulatam.model;

import java.math.BigDecimal;

import javax.persistence.Column;
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

	private String customerId;

	private BigDecimal balance;

	@Column(name="numberaccount")
	private String numberAccount;

	public Account() {
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getNumberAccount() {
		return this.numberAccount;
	}

	public void setNumberAccount(String number) {
		this.numberAccount = number;
	}

}