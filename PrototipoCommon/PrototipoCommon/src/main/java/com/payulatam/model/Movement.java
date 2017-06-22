package com.payulatam.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.payulatam.enums.MovementType;


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

	private MovementType type;

	private BigDecimal value;

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

	@SpaceRouting
	public Integer getSpacerouting() {
		return this.spacerouting;
	}

	public void setSpacerouting(Integer spacerouting) {
		this.spacerouting = spacerouting;
	}

	public MovementType getType() {
		return this.type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}