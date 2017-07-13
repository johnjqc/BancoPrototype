package com.payulatam.prototipo.gs;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payulatam.prototipo.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Test-context.xml" })
public class AccountRepositoryTest {
	
	@Autowired
    GigaSpace gigaSpace;
	private AccountRepository<Account> repo;
	
	@Before
	public void before() throws Exception {
		repo = new AccountRepository<>(gigaSpace);
		Account account = new Account();
		account.setNumberAccount("123");
		account.setBalance(new BigDecimal(100));
		gigaSpace.write(account);
		account = gigaSpace.read(account);
	}
	
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }
    
    @Test
	public void testSearch() throws Exception {
		Account[] actual = repo.serach(null, "123", null);
		Assert.assertTrue(actual.length == 1);
		Assert.assertTrue("123".equals(actual[0].getNumberAccount()));
	}
    
	@Test
	public void testGenerateStringQueryWhithDefault() throws Exception {
		String customerId = "*";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithEmpty() throws Exception {
		String customerId = "";
		String accountNumber = "";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithAllEqual() throws Exception {
		String customerId = "customerId";
		String accountNumber = "MyaccountNumber";
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue("customerId = 'customerId' and  numberAccount = 'MyaccountNumber'  and  balance = 1  ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithAllLike() throws Exception {
		String customerId = "customerId*";
		String accountNumber = "MyaccountNumber*";
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue("customerId = 'customerId*' and  numberAccount like 'MyaccountNumber%'  and  balance = 1  ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryWhithNull() throws Exception {
		String customerId = null;
		String accountNumber = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByCustomerId() throws Exception {
		String customerId = "John Quiroga";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue("customerId = 'John Quiroga' ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByCustomerIdNull() throws Exception {
		String customerId = null;
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByCustomerIdEmpty() throws Exception {
		String customerId = "";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAccountNumber() throws Exception {
		String customerId = "*";
		String accountNumber = "MyaccountNumber";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" numberAccount = 'MyaccountNumber'  ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAccountNumberLike() throws Exception {
		String customerId = "*";
		String accountNumber = "My*accountNumber";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" numberAccount like 'My%accountNumber'  ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAccountNumberNull() throws Exception {
		String customerId = "*";
		String accountNumber = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByAccountNumberEmpty() throws Exception {
		String customerId = "*";
		String accountNumber = "";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByBalance() throws Exception {
		String customerId = "*";
		String accountNumber = "*";
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" balance = 1  ORDER BY numberaccount".equals(query));
	}
	
	@Test
	public void testGenerateStringQueryByBalanceNull() throws Exception {
		String customerId = "*";
		String accountNumber = "*";
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, accountNumber, balance);
		Assert.assertTrue(" ORDER BY numberaccount".equals(query));
	}
	
}
