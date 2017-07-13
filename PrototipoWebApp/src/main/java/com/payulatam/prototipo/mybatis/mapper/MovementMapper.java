package com.payulatam.prototipo.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.payulatam.prototipo.dto.TotalTypesMovement;
import com.payulatam.prototipo.model.Movement;

/**
 * Mapper for Account of MyBatis
 * @author john.quiroga
 *
 */
public interface MovementMapper {
	
	@Select("select * from movement")
	public List<Movement> getAllMovement();
	
	@Select("select * from movement where accountId = #{accountId}")
	public List<Movement> getAllMovementByAccountId(@Param("accountId") String accountId);
	
	@Select(" select type as typeMovement, count(type) as total "
			+ " from movement "
			+ "where accountid = #{accountId} "
			+ "  and (movementdate >= #{initialDate} "
			+ "  and movementdate <= #{finalDate}) "
			+ "group by type")
	public List<TotalTypesMovement> getTotalTypeByAccountIdAndRangeDate(
			@Param("accountId") String accountId,
			@Param("initialDate") Date initialDate, 
			@Param("finalDate") Date finalDate );
	
	@Select(" SELECT account.* FROM movement "
			+ " JOIN account on account.id = movement.accountid "
			+ " JOIN customer on customer.id = account.customerId "
			+ "WHERE  AND customer.id = #{customerId} "
			+ "  AND (movementdate >= #{initialDate} "
			+ "  and movementdate <= #{finalDate} )")
	public List<Movement> getAllMovementByAccountAndRangeDate(
			@Param("customerId") String customerId,
			@Param("initialDate") Date initialDate, 
			@Param("finalDate") Date finalDate );

}
