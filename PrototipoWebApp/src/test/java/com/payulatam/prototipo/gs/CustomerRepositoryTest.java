package com.payulatam.prototipo.gs;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payulatam.model.Customer;
import com.payulatam.prototipo.gs.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Test-context.xml" })
public class CustomerRepositoryTest {
	
	@Autowired
    GigaSpace gigaSpace;
	
	private CustomerRepository<Customer> repo = new CustomerRepository<>(gigaSpace);
	
	@Before
	public void before() throws Exception {
		repo = new CustomerRepository<>(gigaSpace);
		Customer customer = new Customer();
		customer.setName("John Quiroga");
		customer.setPhone("3013684621");
		customer.setAddress("trv 49C # 75-42 sur");
		gigaSpace.write(customer);
		customer = gigaSpace.read(customer);
	}
	
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }
    
    @Test
	public void testSearch() throws Exception {
		Customer[] actual = repo.serach("John Quiroga", null, null);
		Assert.assertTrue(actual.length == 1);
		Assert.assertTrue("John Quiroga".equals(actual[0].getName()));
	}
    
    @Test
	public void testFindById() throws Exception {
    	Customer expected = new Customer();
		expected.setName("John Quiroga");
		expected = gigaSpace.read(expected);
		
		Customer actual = repo.findById(expected.getId());
		Assert.assertTrue(expected.getId().equals(actual.getId()));
	}
    
    @Test
	public void testFindAll() throws Exception {
    	Customer expected = new Customer();
		expected.setName("John Quiroga");
    	Customer[] expecteds = { expected };
		Customer[] actual = repo.findAll();
		for (Customer customer : expecteds) {
			Assert.assertTrue(
				Arrays.stream(actual)
					.map(Customer::getName)
					.map(name -> name.equals(customer.getName()))
					.count() == 1
			);
		}
	}
    
    @Test
	public void testSave() throws Exception {
    	Customer customer = new Customer();
		customer.setName("John Quiroga 2");
		customer.setPhone("3013684621-2");
		customer.setAddress("trv 49C # 75-42 sur");
		repo.save(customer);
		
		Assert.assertTrue(repo.findAll().length == 2);
	}
    
    @Test
	public void testDeleteById() throws Exception {
    	Customer[] actual = repo.serach("John Quiroga", null, null);
    	repo.deleteById(actual[0].getId());
    	Assert.assertTrue(repo.findById(actual[0].getId()) == null);
	}
    
	@Test
	public void testGenerateStringQueryWhithDefault() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithEmpty() throws Exception {
		String name = "";
		String address = "";
		String phone = "";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithAllEqual() throws Exception {
		String name = "Name";
		String address = "MyAddress";
		String phone = "MyPhone";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name = 'Name'  and  address = 'MyAddress'  and  phone = 'MyPhone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithAllLike() throws Exception {
		String name = "Name*";
		String address = "MyAddress*";
		String phone = "MyPhone*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name like 'Name%'  and  address like 'MyAddress%'  and  phone like 'MyPhone%'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithNull() throws Exception {
		String name = null;
		String address = null;
		String phone = null;
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByName() throws Exception {
		String name = "John Quiroga";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name = 'John Quiroga'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByNameLike() throws Exception {
		String name = "John *Quiroga";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" name like 'John %Quiroga'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByNameNull() throws Exception {
		String name = null;
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByNameEmpty() throws Exception {
		String name = "";
		String address = "*";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAddress() throws Exception {
		String name = "*";
		String address = "MyAddress";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" address = 'MyAddress'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAddressLike() throws Exception {
		String name = "*";
		String address = "My*Address";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" address like 'My%Address'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAddressNull() throws Exception {
		String name = "*";
		String address = null;
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAddressEmpty() throws Exception {
		String name = "*";
		String address = "";
		String phone = "*";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByPhone() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "MyPhone";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" phone = 'MyPhone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByPhoneLike() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "My*Phone";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" phone like 'My%Phone'  ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByPhoneNull() throws Exception {
		String name = "*";
		String address = "*";
		String phone = null;
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByPhoneEmpty() throws Exception {
		String name = "*";
		String address = "*";
		String phone = "";
		
		String query = repo.generateStringQuery(name, address, phone);
		Assert.assertTrue(" ORDER BY name".equals(query));
	}
	
}
