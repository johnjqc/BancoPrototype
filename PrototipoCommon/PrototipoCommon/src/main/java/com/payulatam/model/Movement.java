package com.payulatam.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceRouting;


/**
 * The persistent class for the movement database table.
 * @author John
 */
@Entity
@Table(name="movement")
@SpaceClass(persist=true)
public class Movement extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Movement Date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="movementdate")
	private Date movementDate;

	/**
	 * Type of Movement (DEBIT or CREDIT)
	 */
	@Enumerated(EnumType.STRING)
	private String type;

	/**
	 * Value of Movement
	 */
	private BigDecimal value;
	
	/**
	 * AccountId of Movement
	 */
	private String accountId;
	
	/**
	 * Processed by Polling Container on GigaSpaces
	 */
	private boolean processed;

	public Movement() {
	}

	/**
	 * Get Movement Date
	 * @return Date
	 */
	public Date getMovementDate() {
		return this.movementDate;
	}

	/**
	 * Set Movement Date
	 * @param movementDate Date
	 */
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	/**
	 * Get Space Routing of GigaSpaces
	 */
	@Override
	@SpaceRouting
	public Long getSpacerouting() {
		return this.spacerouting;
	}

	/**
	 * Set Space Routing of GigaSpaces
	 */
	@Override
	public void setSpacerouting(Long spacerouting) {
		this.spacerouting = spacerouting;
	}

	/**
	 * Get Type of Movement
	 * @return String
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Set Type of Movement
	 * @param type String
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get Value of Movement
	 * @return BigDecimal
	 */
	public BigDecimal getValue() {
		return this.value;
	}

	/**
	 * Set Value of Movement
	 * @param value BigDecimal
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * Get Account Id
	 * @return String
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * Set Account Id
	 * @param accountId String
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * Is Processed
	 * @return Boolean
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * Set Processed
	 * @param processed Boolean
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
}