package com.payulatam.gs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payulatam.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CustomerRepositoryTest {
	
	@Autowired
    GigaSpace gigaSpace;
	
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }
    
	@Test
	public void generateStringQueryWhithDefaultTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithEmptyTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "";
		String address = "";
		String phone = "";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithNullTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = null;
		String address = null;
		String phone = null;
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "John Quiroga";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name = 'John Quiroga'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameNullTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = null;
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameEmptyTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = "MyAddress";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" address = 'MyAddress'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressNullTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = null;
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressEmptyTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = "";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = "*";
		String phone = "MyPhone";
		
		String query = repo.generateStringQuery(name, address, phone);
		System.out.println(query);
		Assert.assertTrue(" phone = 'MyPhone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneNullTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = "*";
		String phone = null;
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneEmptyTest() throws Exception {
		CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
		String name = "*";
		String address = "*";
		String phone = "";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
}
