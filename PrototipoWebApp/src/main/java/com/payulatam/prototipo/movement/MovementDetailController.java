package com.payulatam.prototipo.movement;

import java.math.BigDecimal;
import java.util.Date;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.prototipo.ControllerHelper;
import com.payulatam.prototipo.common.Constantes;
import com.payulatam.prototipo.enums.MovementType;
import com.payulatam.prototipo.model.Account;
import com.payulatam.prototipo.model.Movement;

/**
 * Controller Detal for Movements
 * @author john.quiroga
 *
 */
public class MovementDetailController extends GenericForwardComposer {
	
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 6077674101236551588L;
	
	@GigaSpaceContext
    protected GigaSpace gigaSpace;
	
	/**
	 * Actual movement object
	 */
	private Movement actualMovement;
	
	/**
	 * New button object
	 */
	private Button buttonNew;
	
	/**
	 * Combobox account object
	 */
	private Combobox comboboxAccount;
	
	/**
	 * Combobox type objetc
	 */
	private Combobox comboboxType;
	
	/**
	 * Datebox date object
	 */
	private Datebox dateboxDate;
	
	/**
	 * Decimabox value object
	 */
	private Decimalbox decimalboxValue;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        SQLQuery<Account> query = new SQLQuery<>(Account.class, "");
        Account[] accounts = gigaSpace.readMultiple(query);
        for (int i = 0;accounts!= null && i < accounts.length; i++) {
        	Comboitem comboitem = new Comboitem(accounts[i].getNumberAccount());
        	comboitem.setValue(accounts[i].getId());
        	comboitem.setParent(comboboxAccount);
		}
        
        if (accounts != null && accounts.length > 0) {
        	comboboxAccount.setSelectedIndex(0);
        }
        
        ControllerHelper.enumToComboItem(comboboxType, MovementType.class);
        comboboxType.setSelectedIndex(0);
        
        dateboxDate.setValue(new Date());
        decimalboxValue.setValue(new BigDecimal(0));
        
        buttonNew.setVisible(true);
    }
	
	public void onClick$buttonNew() {
		actualMovement = new Movement();
		actualMovement.setAccountId("" + comboboxAccount.getSelectedItem().getValue());
		actualMovement.setType("" + comboboxType.getSelectedItem().getValue());
		actualMovement.setMovementDate(dateboxDate.getValue());
		actualMovement.setValue(decimalboxValue.getValue());
		actualMovement.setProcessed(false);
		actualMovement.setSpacerouting(1L);
		gigaSpace.write(actualMovement);
		
		Executions.sendRedirect(Constantes.PATH_MOVEMENT);
	}
	
}
