package com.payulatam.prototipo.gs;

import java.math.BigDecimal;
import java.util.Date;

import com.payulatam.prototipo.model.Movement;

/**
 * Interface with specific methods definition
 * @author John Quiroga
 *
 */
public interface IMovementRepository extends IGenericRepository <Movement> {

	/**
	 * Search Movement by parameters
	 * @param customerId ID of Customer
	 * @param typeMovement Movement Type are DEBIT or CREDIT
	 * @param date Date of Movement
	 * @param balance Balance of Movement
	 * @return Array with Movement object
	 */
	public Movement[] serach(String customerId, String typeMovement, Date date, BigDecimal balance);
	
}
