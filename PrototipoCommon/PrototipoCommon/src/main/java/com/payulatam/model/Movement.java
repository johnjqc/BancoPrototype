package com.payulatam.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;


/**
 * The persistent class for the movement database table.
 * 
 */
@Entity
@Table(name="movement")
@SpaceClass(persist=true)
public class Movement implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer spacerouting;

	@Enumerated(EnumType.STRING)
	private String type;

	private BigDecimal value;
	
	private String accountId;
	
	private boolean processed;

	public Movement() {
	}

	@Id
	@SpaceId(autoGenerate = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Transient
	@SpaceRouting
	public Integer getSpacerouting() {
		return 1;
	}

	public void setSpacerouting(Integer spacerouting) {
		this.spacerouting = spacerouting;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Transient
	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	
	
}