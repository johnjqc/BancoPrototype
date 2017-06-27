package com.payulatam.gs;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payulatam.model.Movement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MovementRepositoryTest {
	
	@Autowired
    GigaSpace gigaSpace;
	private MovementRepository<Movement> repo = new MovementRepository<>(gigaSpace);
	
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }
    
    @Test
	public void generateStringQueryWhithDefaultTest() throws Exception {
		String customerId = "*";
		String typeMovement = "*";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
    
    @Test
	public void generateStringQueryWhithEmptyTest() throws Exception {
		String customerId = "";
		String typeMovement = "";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithAllEqualTest() throws Exception {
		String customerId = "customerId";
		String typeMovement = "MytypeMovement";
		Date date = new Date();
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		
		String sDate = query.substring(query.indexOf("date = '") + 8, query.indexOf("'  and  value"));
		Assert.assertTrue(("accountId = 'customerId' and  type = 'MytypeMovement'  and  date = '" + sDate + "'  and  value = 1  ORDER BY com.payulatam.model.Movement.date").equals(query));
	}
	
	@Test
	public void generateStringQueryWhithAllLikeTest() throws Exception {
		String customerId = "customerId*";
		String typeMovement = "MytypeMovement*";
		Date date = null;
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue("accountId = 'customerId*' and  type = 'MytypeMovement*'  and  value = 1  ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryWhithNullTest() throws Exception {
		String customerId = null;
		String typeMovement = null;
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByCustomerIdTest() throws Exception {
		String customerId = "John Quiroga";
		String typeMovement = "*";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue("accountId = 'John Quiroga' ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByCustomerIdNullTest() throws Exception {
		String customerId = null;
		String typeMovement = "*";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByCustomerIdEmptyTest() throws Exception {
		String customerId = "";
		String typeMovement = "*";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByTypeTest() throws Exception {
		String customerId = "*";
		String typeMovement = "MytypeMovement";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" type = 'MytypeMovement'  ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByTypeLikeTest() throws Exception {
		String customerId = "*";
		String typeMovement = "My*typeMovement";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" type = 'My*typeMovement'  ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByTypeNullTest() throws Exception {
		String customerId = "*";
		String typeMovement = null;
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByTypeEmptyTest() throws Exception {
		String customerId = "*";
		String typeMovement = "";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByBalanceTest() throws Exception {
		String customerId = "*";
		String typeMovement = "*";
		Date date = null;
		BigDecimal balance = new BigDecimal(1);
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" value = 1  ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
	@Test
	public void generateStringQueryByBalanceNullTest() throws Exception {
		String customerId = "*";
		String typeMovement = "*";
		Date date = null;
		BigDecimal balance = null;
		
		String query = repo.generateStringQuery(customerId, typeMovement, date, balance);
		Assert.assertTrue(" ORDER BY com.payulatam.model.Movement.date".equals(query));
	}
	
}
