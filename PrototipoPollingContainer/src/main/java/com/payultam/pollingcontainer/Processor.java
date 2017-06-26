package com.payultam.pollingcontainer;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.openspaces.events.polling.ReceiveHandler;
import org.openspaces.events.polling.receive.ReceiveOperationHandler;
import org.openspaces.events.polling.receive.SingleTakeReceiveOperationHandler;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.common.GigaSpaceController;
import com.payulatam.enums.MovementType;
import com.payulatam.model.Account;
import com.payulatam.model.Movement;


/**
 * The processor simulates work done no un-processed Data object. The processData
 * accepts a Data object, simulate work by sleeping, and then sets the processed
 * flag to true and returns the processed Data.
 */
@EventDriven
@Polling
public class Processor {

    Logger log= Logger.getLogger(this.getClass().getName());
    
    @GigaSpaceContext
    GigaSpace gigaSpace;
    
    public Processor() {
		System.out.println(this.getClass() + " constructed");
	}
    
    @ReceiveHandler
    ReceiveOperationHandler receiveHandler() {
        SingleTakeReceiveOperationHandler receiveHandler = new SingleTakeReceiveOperationHandler();
        receiveHandler.setNonBlocking(true);
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
        data.setProcessed(true);
        System.out.println("PollingContainer : " + data.getId());
        
        String id = data.getAccountId();
		SQLQuery<Account> query = new SQLQuery<Account>(Account.class, "id = '" + id + "'");
		Account account = GigaSpaceController.getGigaSpace().read(query);
		if (MovementType.DEBIT.toString().equals(data.getType())) {
			BigDecimal result = account.getBalance().add(data.getValue());
			account.setBalance(result);
			gigaSpace.write(account);
		} else {
			BigDecimal result = account.getBalance().subtract(data.getValue());
			if (result.signum() >= 0) {
				account.setBalance(result);
				gigaSpace.write(account);
			} else {
				System.out.println("El movimiento no se registra por dejar la cuenta con Saldo negativo");
				return null;
			}
		}
        
        return data;
    }

}
