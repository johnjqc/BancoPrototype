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
	
	public List<Movement> getAllMovement();
	
	public List<Movement> getAllMovementByAccountId(String accountId);
	
	public List<TotalTypesMovement> getTotalTypeByAccountIdAndRangeDate( String accountId, Date initialDate, Date finalDate );
	
	public List<Movement> getAllMovementByAccountAndRangeDate(String customerId, Date initialDate, Date finalDate );

}
