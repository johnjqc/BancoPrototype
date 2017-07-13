package com.payulatam.prototipo.dto;

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

	/**
	 * Cosntructor
	 * Set to default total with 0
	 */
	public TotalTypesMovement() {
		total = "0";
	}

	/**
	 * Get Movement Type
	 * @return String with CREDIT or DEBIT
	 */
	public String getTypeMovement() {
		return typeMovement;
	}

	/**
	 * Set Movment Type
	 * @param typeMovement String
	 */
	public void setTypeMovement(String typeMovement) {
		this.typeMovement = typeMovement;
	}

	/**
	 * Get Total
	 * @return String with Total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Set Total
	 * @param total String
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	
}
