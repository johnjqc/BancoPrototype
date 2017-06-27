package com.payulatam.gs;

import org.openspaces.core.GigaSpace;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.model.Customer;

/**
 * Implementation of repository by GigaSpace for Customer
 * @author John Quiroga
 *
 * @param <BaseEntity>
 */
public class CustomerRepository<BaseEntity> extends AbstractRepository<Customer> implements ICustomerRepository {

	public CustomerRepository(GigaSpace gigaSpace) {
		super.gigaSpace = gigaSpace;
	}

	@Override
	public Customer[] serach(String name, String address, String phone) {
		StringBuilder stringQuery = new StringBuilder();
		if (!"*".equals(name) && !name.isEmpty()) {
			if (name.contains("*")) {
				String toReplace = name; 
				toReplace = toReplace.replaceAll("\\*", "\\%");
				stringQuery.append(String.format(" name like '%s' ", toReplace));
			} else {
				stringQuery.append(String.format(" name = '%s' ", name));
			}
		}
		if (!"*".equals(address) && !address.isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			if (address.contains("*")) {
				stringQuery.append(String.format(" address like '%s' ", address.replaceAll("\\*", "\\%")));
			} else {
				stringQuery.append(String.format(" address = '%s' ", address));
			}
		}
		if (!"*".equals(phone) && !phone.isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			if (address.contains("*")) {
				stringQuery.append(String.format(" phone like '%s' ", phone).replaceAll("\\*", "\\%"));
			} else {
				stringQuery.append(String.format(" phone = '%s' ", phone));
			}
		}
		stringQuery.append(" ORDER BY name");
		SQLQuery<Customer> query = new SQLQuery<Customer>(Customer.class, stringQuery.toString());
		
		Customer[] result = gigaSpace.readMultiple(query);
		
		return result;
	}
	
}
