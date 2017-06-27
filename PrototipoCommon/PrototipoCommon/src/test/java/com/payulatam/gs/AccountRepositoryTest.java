package com.payulatam.gs;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payulatam.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AccountRepositoryTest {
	
	@Autowired
    GigaSpace gigaSpace;
	private AccountRepository<Account> repo = new AccountRepository<>(gigaSpace);
	
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }
    
	@Test
	public void generateStringQueryWhithDefaultTest() throws Exception {
		String customerId = "*";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryWhithEmptyTest() throws Exception {
		String customerId = "";
		String accountNumber = "";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryWhithAllEqualTest() throws Exception {
		String customerId = "customerId";
		String accountNumber = "MyaccountNumber";
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue("customerId = 'customerId' and  number = 'MyaccountNumber'  and  balance = 1 ".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithAllLikeTest() throws Exception {
		String customerId = "customerId*";
		String accountNumber = "MyaccountNumber*";
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue("customerId = 'customerId*' and  number like 'MyaccountNumber%'  and  balance = 1 ".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithNullTest() throws Exception {
		String customerId = null;
		String accountNumber = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryByCustomerIdTest() throws Exception {
		String customerId = "John Quiroga";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue("customerId = 'John Quiroga'".equals(query));
	}
	
	@Test
	public void generateStringQueryByCustomerIdNullTest() throws Exception {
		String customerId = null;
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryByCustomerIdEmptyTest() throws Exception {
		String customerId = "";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryByAccountNumberTest() throws Exception {
		String customerId = "*";
		String accountNumber = "MyaccountNumber";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" number = 'MyaccountNumber' ".equals(query));
	}
	
	@Test
	public void generateStringQueryByAccountNumberLikeTest() throws Exception {
		String customerId = "*";
		String accountNumber = "My*accountNumber";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" number like 'My%accountNumber' ".equals(query));
	}
	
	@Test
	public void generateStringQueryByAccountNumberNullTest() throws Exception {
		String customerId = "*";
		String accountNumber = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryByAccountNumberEmptyTest() throws Exception {
		String customerId = "*";
		String accountNumber = "";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
	@Test
	public void generateStringQueryByBalanceTest() throws Exception {
		String customerId = "*";
		String accountNumber = "*";
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" balance = 1 ".equals(query));
	}
	
	@Test
	public void generateStringQueryByBalanceNullTest() throws Exception {
		String customerId = "*";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(query.isEmpty());
	}
	
}
