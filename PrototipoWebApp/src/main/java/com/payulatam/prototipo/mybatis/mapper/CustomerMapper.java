package com.payulatam.prototipo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.payulatam.prototipo.model.Customer;

/**
 * Customer mapper of MyBatis
 * @author John
 *
 */
public interface CustomerMapper {
	
	@Select("select * from customer")
	public List<Customer> getAllCustomer();

}
