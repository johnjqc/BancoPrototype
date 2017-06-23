package com.payultam.pollingcontainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gigaspaces.internal.utils.Assert;
import com.payulatam.model.Movement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProcessorTest {
	
	@Autowired
    GigaSpace gigaSpace;

    @Before
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }

    @Test
    public void verifyProcessing() throws Exception {
        // write the data to be processed to the Space
    	Movement data = new Movement();
        gigaSpace.write(data);

        // create a template of the processed data (processed)
        Movement template = new Movement();
        template.setProcessed(true);

        // wait for the result
        Movement result = gigaSpace.take(template, 500);
        
        Assert.notNull(result);
        Assert.isTrue(result.isProcessed());
    }
	
}
