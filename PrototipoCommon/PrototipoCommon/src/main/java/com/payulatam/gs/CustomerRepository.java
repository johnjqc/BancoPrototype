package com.payulatam.gs;

import org.openspaces.core.GigaSpace;

import com.payulatam.model.Customer;

/**
 * Implementation of repository by GigaSpace for Customer
 * @author John Quiroga
 *
 * @param <BaseEntity>
 */
public class CustomerRepository<J extends Customer> extends AbstractRepository<Customer> implements ICustomerRepository {

	public CustomerRepository(GigaSpace gigaSpace) {
		super.gigaSpace = gigaSpace;
	}

	@Override
	public Customer[] serach(String name, String address, String phone) {
		String queryString = generateStringQuery(name, address, phone);
		Customer[] result = findByCriteria(queryString);
		return result;
	}
	
	public String generateStringQuery(String name, String address, String phone) {
		StringBuilder query = new StringBuilder();
		if (name != null && !"*".equals(name) && !name.isEmpty()) {
			if (name.contains("*")) {
				String toReplace = name; 
				toReplace = toReplace.replaceAll("\\*", "\\%");
				query.append(String.format(" name like '%s' ", toReplace));
			} else {
				query.append(String.format(" name = '%s' ", name));
			}
		}
		if (address != null && !"*".equals(address) && !address.isEmpty()) {
			if (!query.toString().isEmpty()) {
				query.append(" and ");
			}
			if (address.contains("*")) {
				query.append(String.format(" address like '%s' ", address.replaceAll("\\*", "\\%")));
			} else {
				query.append(String.format(" address = '%s' ", address));
			}
		}
		if (phone != null && !"*".equals(phone) && !phone.isEmpty()) {
			if (!query.toString().isEmpty()) {
				query.append(" and ");
			}
			if (phone.contains("*")) {
				query.append(String.format(" phone like '%s' ", phone).replaceAll("\\*", "\\%"));
			} else {
				query.append(String.format(" phone = '%s' ", phone));
			}
		}
		query.append(" ORDER BY name");
		
		return query.toString();
	}
	
}
