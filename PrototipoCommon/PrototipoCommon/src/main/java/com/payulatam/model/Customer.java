package com.payulatam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gigaspaces.annotation.pojo.SpaceClass;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@SpaceClass(persist=true)
public class Customer extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Address of Customer
	 */
	private String address;

	/**
	 * Name of Customer
	 */
	private String name;

	/**
	 * Phone of Customer
	 */
	private String phone;

	public Customer() {
	}

	/**
	 * Get Address
	 * @return String with Address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Set Address
	 * @param address String
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get Name
	 * @return String with Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set Name
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get Phone
	 * @return String with Phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Set Phone
	 * @param phone String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [address=" + address + ", name=" + name + ", phone=" + phone + "]";
	}
	
}