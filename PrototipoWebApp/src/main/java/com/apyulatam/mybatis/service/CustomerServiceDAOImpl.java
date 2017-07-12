package com.apyulatam.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payulatam.model.Customer;
import com.payulatam.mybatis.CustomerMapper;

/**
 * DAO Implementation for Customer
 * @author John
 *
 */
@Service
@Transactional
public class CustomerServiceDAOImpl implements CustomerServiceDAO {
	
	@Autowired
	private CustomerMapper customerDAOMapper;

	@Override
	public List<Customer> getAllCustomer() {
		return customerDAOMapper.getAllCustomer();
	}
	

}
