package com.apyulatam.mybatis.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.payulatam.model.Customer;

public interface CustomerServiceDAO {
	
	@Select("select * from account")
	public List<Customer> getAllCustomer();

}
