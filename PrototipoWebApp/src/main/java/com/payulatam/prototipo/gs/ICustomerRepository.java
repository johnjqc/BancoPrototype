package com.payulatam.prototipo.gs;

import com.payulatam.model.Customer;

/**
 * Interface with specific methods definition
 * @author John Quiroga
 *
 */
public interface ICustomerRepository extends IGenericRepository <Customer> {

	/**
	 * Search Customer by parameters
	 * @param name Customer Name
	 * @param address Customer Address
	 * @param phone Customer Phone
	 * @return Array with Customer object
	 */
	public Customer[] serach(String name, String address, String phone);
	
}
