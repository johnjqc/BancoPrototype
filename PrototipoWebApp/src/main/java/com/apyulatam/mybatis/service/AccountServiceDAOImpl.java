package com.apyulatam.mybatis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payulatam.model.Account;
import com.payulatam.mybatis.AccountMapper;

/**
 * DAO Implementation for Account
 * @author John
 *
 */
@Service
@Transactional
public class AccountServiceDAOImpl implements AccountServiceDAO {
	
	@Autowired
	private AccountMapper accountDAOMapper;

	@Override
	public List<Account> getAllAccount() {
		return accountDAOMapper.getAllAccount();
	}
	
	@Override
	public List<Account> getAllAccountByCustormerId(String customerId) {
		return accountDAOMapper.getAllAccountByCustormerId(customerId);
	}

	@Override
	public List<Account> getAllAccountByCustomerIdAndRangeDateOfMovement(String customerId, Date initialDate, Date finalDate) {
		return accountDAOMapper.getAllAccountByCustomerIdAndRangeDateOfMovement(customerId, initialDate, finalDate);
	}

}
