package com.payulatam.mybatis;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.payulatam.model.Account;

/**
 * Mapper for Account 
 * @author john.quiroga
 *
 */
public interface AccountMapper {
	
	@Select("select * from account")
	public List<Account> getAllAccount();
	
	@Select("select * from account where customerId = #{customerId}")
	public List<Account> getAllAccountByCustormerId(@Param("customerId") String customerId);
	
	@Select("SELECT account.id, account.balance, account.numberaccount "
		+ "    FROM movement JOIN account on account.id = movement.accountid "
		+ "    JOIN customer on customer.id = account.customerId "
		+ "   WHERE customer.id = #{customerId} "
		+ "     AND (movementdate >= #{initialDate} and movementdate <= #{finalDate} ) "
		+ "   GROUP BY account.id, account.balance, account.numberaccount")
	public List<Account> getAllAccountByCustomerIdAndRangeDateOfMovement(
			@Param("customerId") String customerId,
			@Param("initialDate") Date initialDate, 
			@Param("finalDate") Date finalDate );

}
