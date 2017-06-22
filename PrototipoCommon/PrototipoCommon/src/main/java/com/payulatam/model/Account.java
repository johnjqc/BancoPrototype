package com.payulatam.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
@SpaceClass(persist=true)
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String customerId;

	private BigDecimal balance;

	private String number;

	private Integer spacerouting;


	public Account() {
	}

	@Id
	@SpaceId(autoGenerate = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@SpaceRouting
	public Integer getSpacerouting() {
		return this.spacerouting;
	}

	public void setSpacerouting(Integer spacerouting) {
		this.spacerouting = spacerouting;
	}

}