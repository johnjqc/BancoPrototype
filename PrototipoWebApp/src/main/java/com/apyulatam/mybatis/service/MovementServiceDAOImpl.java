package com.apyulatam.mybatis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payulatam.dto.TotalTypesMovement;
import com.payulatam.model.Movement;
import com.payulatam.mybatis.MovementMapper;

/**
 * DAO Implementation for Account 
 * @author john.quiroga
 *
 */
@Service
@Transactional
public class MovementServiceDAOImpl implements MovementServiceDAO {

	@Autowired
	private MovementMapper movementDAOMapper;
	
	@Override
	public List<Movement> getAllMovement() {
		return movementDAOMapper.getAllMovement();
	}

	@Override
	public List<Movement> getAllMovementByAccountId(String accountId) {
		return movementDAOMapper.getAllMovementByAccountId(accountId);
	}

	@Override
	public List<TotalTypesMovement> getTotalTypeByAccountIdAndRangeDate(String accountId, Date initialDate,
			Date finalDate) {
		return movementDAOMapper.getTotalTypeByAccountIdAndRangeDate(accountId, initialDate, finalDate);
	}

	@Override
	public List<Movement> getAllMovementByAccountAndRangeDate(String customerId, Date initialDate, Date finalDate) {
		return movementDAOMapper.getAllMovementByAccountAndRangeDate(customerId, initialDate, finalDate);
	}
	

}
