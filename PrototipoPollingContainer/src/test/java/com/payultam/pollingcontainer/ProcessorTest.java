package com.payultam.pollingcontainer;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gigaspaces.internal.utils.Assert;
import com.payulatam.enums.MovementType;
import com.payulatam.model.Account;
import com.payulatam.model.Customer;
import com.payulatam.model.Movement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProcessorTest {
	
	@Autowired
    GigaSpace gigaSpace;
	
	@Before
	public void prepareSpace() {
		gigaSpace.clear(null);
		
		System.out.println("Before test");
		Customer customer = new Customer();
		customer.setName("John Quiroga");
		customer.setPhone("3013684621");
		customer.setAddress("trv 49C # 75-42 sur");
		gigaSpace.write(customer);
		
		Account account = new Account();
		account.setNumber("123456");
		account.setBalance(new BigDecimal(100));
		
		customer = gigaSpace.read(customer);
		account.setCustomerId(customer.getId());
		gigaSpace.write(account);
		
	}

    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }

    @Test
    public void verifyProcessing() throws Exception {
    	Movement mov = new Movement();
    	
    	Account account = new Account();
		account.setNumber("123456");
		account = gigaSpace.read(account);
		System.out.println("account: " + account);
    	
    	mov.setAccountId(account.getId());
    	mov.setValue(new BigDecimal(20));
    	mov.setType(MovementType.CREDIT.toString());
    	mov.setProcessed(false);
        gigaSpace.write(mov);

        Movement template = new Movement();
        template.setProcessed(true);

        Movement result = gigaSpace.read(template);
        
        Assert.notNull(result);
        Assert.isTrue(result.isProcessed());
    }
	
}
