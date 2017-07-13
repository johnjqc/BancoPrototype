package com.payulatam.prototipo.gs;

import java.math.BigDecimal;

import com.payulatam.prototipo.model.Account;

/**
 * Interface with specific methods definition
 * @author John Quiroga
 *
 */
public interface IAccountRepository extends IGenericRepository <Account> {
	
	/**
	 * Search Account by parameters
	 * @param customerId ID from Customer
	 * @param accountNumber Number Account of Customer
	 * @param balance Balance of Account
	 * @return Array with Account object
	 */
	public Account[] serach(String customerId, String accountNumber, BigDecimal balance);
	
}
