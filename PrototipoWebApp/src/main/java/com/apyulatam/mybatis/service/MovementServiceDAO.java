package com.apyulatam.mybatis.service;

import java.util.Date;
import java.util.List;

import com.payulatam.dto.TotalTypesMovement;
import com.payulatam.model.Movement;

/**
 * Mapper for Account 
 * @author john.quiroga
 *
 */
public interface MovementServiceDAO {
	
	/**
	 * Get all Movements from data base
	 * @return List<Movement>
	 */
	public List<Movement> getAllMovement();
	
	/**
	 * Get all movements by account Id
	 * @param accountId String with account Id
	 * @return List<Movement>
	 */
	public List<Movement> getAllMovementByAccountId(String accountId);
	
	/**
	 * Get total type movements by account Id and range date
	 * @param accountId String with account Id
	 * @param initialDate Date with initial date
	 * @param finalDate Date with final date
	 * @return List<Movement>
	 */
	public List<TotalTypesMovement> getTotalTypeByAccountIdAndRangeDate( String accountId, Date initialDate, Date finalDate );
	
	/**
	 * Get all movements by account and range date
	 * @param customerId String with customer Id
	 * @param initialDate Date with initial date
	 * @param finalDate Date with final date
	 * @return List<Movement>
	 */
	public List<Movement> getAllMovementByAccountAndRangeDate(String customerId, Date initialDate, Date finalDate );

}
