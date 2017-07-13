package com.payulatam.prototipo.mybatis.service;

import java.util.List;

import com.payulatam.prototipo.model.Customer;

/**
 * DAO for Customer Service
 * @author John
 *
 */
public interface CustomerServiceDAO {
	
	/**
	 * Get all Customers
	 * @return List<Customer>
	 */
	public List<Customer> getAllCustomer();

}
