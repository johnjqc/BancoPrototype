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
	
	private CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
	
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }
    
	@Test
	public void generateStringQueryWhithDefaultTest() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithEmptyTest() throws Exception {
		String name = "";
		String address = "";
		String phone = "";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithAllEqualTest() throws Exception {
		String name = "Name";
		String address = "MyAddress";
		String phone = "MyPhone";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name = 'Name'  and  address = 'MyAddress'  and  phone = 'MyPhone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithAllLikeTest() throws Exception {
		String name = "Name*";
		String address = "MyAddress*";
		String phone = "MyPhone*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name like 'Name%'  and  address like 'MyAddress%'  and  phone like 'MyPhone%'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithNullTest() throws Exception {
		String name = null;
		String address = null;
		String phone = null;
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameTest() throws Exception {
		String name = "John Quiroga";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name = 'John Quiroga'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameLikeTest() throws Exception {
		String name = "John *Quiroga";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name like 'John %Quiroga'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameNullTest() throws Exception {
		String name = null;
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByNameEmptyTest() throws Exception {
		String name = "";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressTest() throws Exception {
		String name = "*";
		String address = "MyAddress";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" address = 'MyAddress'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressLikeTest() throws Exception {
		String name = "*";
		String address = "My*Address";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" address like 'My%Address'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressNullTest() throws Exception {
		String name = "*";
		String address = null;
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByAddressEmptyTest() throws Exception {
		String name = "*";
		String address = "";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneTest() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "MyPhone";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" phone = 'MyPhone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneLikeTest() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "My*Phone";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" phone like 'My%Phone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneNullTest() throws Exception {
		String name = "*";
		String address = "*";
		String phone = null;
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void generateStringQueryByPhoneEmptyTest() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
}
