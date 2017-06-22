package com.payulatam.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@SpaceClass(persist=true)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_id_seq")
	@SequenceGenerator(name="customer_id_seq", sequenceName="customer_id_seq", allocationSize=1)
	private String id;

	private String address;

	private String name;

	private String phone;

	private Integer spacerouting;
	
	public Customer() {
	}

	@Id
	@SpaceId(autoGenerate = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@SpaceRouting
	public Integer getSpacerouting() {
		return this.spacerouting;
	}

	public void setSpacerouting(Integer spacerouting) {
		this.spacerouting = spacerouting;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", address=" + address + ", name=" + name + ", phone=" + phone + ", spacerouting="
				+ spacerouting + "]";
	}
	
}