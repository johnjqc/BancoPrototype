package com.payulatam.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.payulatam.model.Customer;

public interface CustomerMapper {
	
	@Select("select * from customer")
	public List<Customer> getAllCustomer();

}
