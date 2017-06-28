package com.payulatam.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.payulatam.model.Customer;

public interface CustomerDAO {
	
	@Select("select * from account")
	public List<Customer> getAllCustomer();

}
