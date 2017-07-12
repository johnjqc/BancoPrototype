package com.payulatam.prototipo.movement;

import java.text.SimpleDateFormat;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.payulatam.common.Constantes;
import com.payulatam.enums.MovementType;
import com.payulatam.model.Account;
import com.payulatam.model.Movement;
import com.payulatam.prototipo.BaseController;
import com.payulatam.prototipo.ControllerHelper;
import com.payulatam.prototipo.gs.AccountRepository;
import com.payulatam.prototipo.gs.MovementRepository;

/**
 * Controller for Movement
 * @author john.quiroga
 *
 */
public class MovementController extends BaseController<Movement> {
	
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 6077674101236551588L;
	
	/**
	 * Repository DAO Movement
	 */
	MovementRepository<Movement> respository;
	
	/**
	 * Repository DAO Account
	 */
	AccountRepository<Account> respositoryAccount;

	/**
	 * Combobox component
	 */
	private Combobox comboboxAccount;

	/**
	 * Combobox component
	 */
	private Combobox comboboxType;

	/**
	 * Datebox component
	 */
	private Datebox dateboxDate;

	/**
	 * DEcimalbox component
	 */
	private Decimalbox decimalboxValue;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        respository = new MovementRepository<>(gigaSpace);
    	respositoryAccount = new AccountRepository<>(gigaSpace);
        
        ControllerHelper.setItemDefault(comboboxAccount);
        
        Account[] accounts = respositoryAccount.findAll();
        for (int i = 0; i < accounts.length; i++) {
        	Comboitem comboitem = new Comboitem(accounts[i].getNumberAccount());
        	comboitem.setValue(accounts[i].getId());
        	comboitem.setParent(comboboxAccount);
		}
        
        ControllerHelper.setItemDefault(comboboxType);
        ControllerHelper.enumToComboItem(comboboxType, MovementType.class);
        
        gridResults.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Movement prod = (Movement)data;
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Account account = gigaSpace.readById(Account.class, prod.getAccountId());
                
                new Label(account.getNumberAccount()).setParent(row);
                new Label(prod.getType().toString()).setParent(row);
                new Label(sdf.format(prod.getMovementDate())).setParent(row);
                new Label(prod.getValue().toString()).setParent(row);
                
                Cell buttons = new Cell();
                buttons.setParent(row);
                
                Button btnRemove = new Button();
                btnRemove.setImage(Constantes.ICON_DELETE);
            	btnRemove.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			respository.deleteById(prod.getId());
            			prodModel.remove(prod);
            		}
            	});
            	btnRemove.setParent(buttons);
            }
        });
    }
	
	@Override
	public void onClick$buttonSearch() {
		Comboitem itemAccount = comboboxAccount.getSelectedItem();
		Movement[] result = respository.serach(String.valueOf(itemAccount.getValue()), comboboxType.getText(),
				dateboxDate.getValue(), decimalboxValue.getValue());
		setModel(result);
	}
	
	@Override
	public void onClick$btnNew() {
		Executions.sendRedirect(Constantes.PATH_MOVEMENT_DETAIL);
	}
	
}
