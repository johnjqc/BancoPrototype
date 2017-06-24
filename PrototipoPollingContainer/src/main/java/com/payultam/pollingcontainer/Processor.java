package com.payultam.pollingcontainer;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.openspaces.core.GigaSpace;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.openspaces.events.polling.ReceiveHandler;
import org.openspaces.events.polling.receive.ReceiveOperationHandler;
import org.openspaces.events.polling.receive.SingleTakeReceiveOperationHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.model.Movement;


/**
 * The processor simulates work done no un-processed Data object. The processData
 * accepts a Data object, simulate work by sleeping, and then sets the processed
 * flag to true and returns the processed Data.
 */
@EventDriven
@Polling
@Service
@TransactionalEvent(isolation = Isolation.READ_COMMITTED, timeout = 1000)
public class Processor {

    Logger log= Logger.getLogger(this.getClass().getName());
    
    public Processor() {
		System.out.println(this.getClass() + " constructed");
	}
    
    @ReceiveHandler
    ReceiveOperationHandler receiveHandler() {
        SingleTakeReceiveOperationHandler receiveHandler = new SingleTakeReceiveOperationHandler();
        receiveHandler.setNonBlocking(true);
        receiveHandler.setNonBlockingFactor(10);
        return receiveHandler;
    }
    
    @EventTemplate
    public SQLQuery<Movement> getTemplate() {
    	SQLQuery<Movement> template = new SQLQuery<Movement>(Movement.class, "processed = false");
    	return template;
    }


    /**
     * Process the given Data object and returning the processed Data.
     *
     * Can be invoked using OpenSpaces Events when a matching event
     * occurs.
     */
    @SpaceDataEvent
    public Movement processData(Movement data, GigaSpace space) {
        // sleep to simulate some work
        data.setProcessed(true);
//        data.setData("PROCESSED : " + data.getRawData());
        System.out.println("PollingContainer : " + data.getId());
        log.info(" ------ PROCESSED : " + data);
        
        if (data.getValue().equals(new BigDecimal(100))) {
        	return null;
        }
        
        return data;
    }

}
