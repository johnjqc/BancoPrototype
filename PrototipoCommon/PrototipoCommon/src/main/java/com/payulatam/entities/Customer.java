package com.payulatam.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;


/**
 * The persistent class for the "Customer" database table.
 * 
 */
@Entity
@Table(name="customer")
@SpaceClass(persist=true)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="spacerouting")
	private Integer spacerouting;

	public Customer() {
	}
	
	@Id
	@SpaceId
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@SpaceRouting
	public Integer getSpacerouting() {
		return spacerouting;
	}

	public void setSpacerouting(Integer spacerouting) {
		this.spacerouting = spacerouting;
	}

}