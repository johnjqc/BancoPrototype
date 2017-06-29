package com.apyulatam.mybatis.service;

import java.util.Date;
import java.util.List;

import com.payulatam.model.Account;

/**
 * DAO for Account 
 * @author john.quiroga
 *
 */
public interface AccountServiceDAO {
	
	/**
	 * Get all account on data base
	 * @return
	 */
	public List<Account> getAllAccount();
	
	public List<Account> getAllAccountByCustormerId(String customerId);
	
	public List<Account> getAllAccountByCustomerIdAndRangeDateOfMovement(String customerId, Date initialDate, Date finalDate);

}
