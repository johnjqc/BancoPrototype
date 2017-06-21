package com.payulatam.model;

import java.io.Serializable;
import javax.persistence.*;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the movement database table.
 * 
 */
@Entity
@Table(name="movement")
@SpaceClass(persist=true)
public class Movement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer spacerouting;

	private String type;

	private BigDecimal value;

	//bi-directional many-to-one association to Account
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;

	public Movement() {
	}

	@SpaceId
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@SpaceRouting
	public Integer getSpacerouting() {
		return this.spacerouting;
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

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}