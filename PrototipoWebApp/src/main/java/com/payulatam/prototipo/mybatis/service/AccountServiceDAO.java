package com.payulatam.prototipo.mybatis.service;

import java.util.Date;
import java.util.List;

import com.payulatam.prototipo.model.Account;

/**
 * DAO for Account 
 * @author john.quiroga
 *
 */
public interface AccountServiceDAO {
	
	/**
	 * Get all account on data base
	 * @return List<Account>
	 */
	public List<Account> getAllAccount();
	
	/**
	 * Get all account by customer by Id
	 * @param customerId String with customer Id
	 * @return List<Account>
	 */
	public List<Account> getAllAccountByCustormerId(String customerId);
	
	/**
	 * Get all accounts by customer Id and range date of Movement
	 * @param customerId String with Customer Id
	 * @param initialDate Date with initial date
	 * @param finalDate Date with final date
	 * @return List<Account>
	 */
	public List<Account> getAllAccountByCustomerIdAndRangeDateOfMovement(String customerId, Date initialDate, Date finalDate);

}
