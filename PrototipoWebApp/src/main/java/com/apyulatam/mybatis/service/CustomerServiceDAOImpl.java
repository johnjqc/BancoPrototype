package com.apyulatam.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payulatam.model.Customer;
import com.payulatam.mybatis.CustomerDAO;

@Service("customerService")
public class CustomerServiceDAOImpl implements CustomerServiceDAO {
	
	@Autowired
	private CustomerDAO customerMapper;

	@Override
	public List<Customer> getAllCustomer() {
		return customerMapper.getAllCustomer();
	}
	

}
