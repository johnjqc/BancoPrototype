package com.payulatam.dto;

/**
 * DTO for MyBatis query total of types movement
 * @author john.quiroga
 *
 */
public class TotalTypesMovement {

	/**
	 * Type of Movement {@link MovementType}
	 */
	private String typeMovement;
	
	/**
	 * Total value of types
	 */
	private String total;

	public String getTypeMovement() {
		return typeMovement;
	}

	public void setTypeMovement(String typeMovement) {
		this.typeMovement = typeMovement;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}
