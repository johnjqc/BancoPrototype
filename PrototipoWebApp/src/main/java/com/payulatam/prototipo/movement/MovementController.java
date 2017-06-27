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

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.enums.MovementType;
import com.payulatam.model.Account;
import com.payulatam.model.Movement;
import com.payulatam.prototipo.BaseController;
import com.payulatam.prototipo.ControllerHelper;

public class MovementController extends BaseController<Movement> {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private Combobox comboboxAccount;
	private Combobox comboboxType;
	private Datebox dateboxDate;
	private Decimalbox decimalboxValue;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        ControllerHelper.setItemDefault(comboboxAccount);
        
        SQLQuery<Account> query = new SQLQuery<>(Account.class, "");
        Account[] accounts = gigaSpace.readMultiple(query);
        for (int i = 0; i < accounts.length; i++) {
        	Comboitem comboitem = new Comboitem(accounts[i].getNumber());
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
                
                new Label(account.getNumber()).setParent(row);
                new Label(prod.getType().toString()).setParent(row);
                new Label(sdf.format(prod.getDate())).setParent(row);
                new Label(prod.getValue().toString()).setParent(row);
                
                Cell buttons = new Cell();
                buttons.setParent(row);
                
                Button btnRemove = new Button();
                btnRemove.setImage("/images/icon-delete.png");
            	btnRemove.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			gigaSpace.takeIfExistsById(Movement.class, prod.getId());
            			prodModel.remove(prod);
            		}
            	});
            	btnRemove.setParent(buttons);
            	
            }
        });
    }
	
	@Override
	public void onClick$buttonSearch() {
		StringBuilder stringQuery = new StringBuilder();
		
		Comboitem itemAccount = comboboxAccount.getSelectedItem();
		if (itemAccount != null && !"*".equals(itemAccount.getValue())) {
			stringQuery.append("accountId = '" + itemAccount.getValue() + "'");
		}
		
		if (!"*".equals(comboboxType.getText()) && !comboboxType.getText().isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			stringQuery.append(String.format(" type = '%s' ", comboboxType.getText()));
		}
		if (dateboxDate.getValue() != null) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			stringQuery.append(String.format(" date = '%s' ", dateboxDate.getValue()));
		}
		
		if (decimalboxValue.getValue() != null) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			stringQuery.append(String.format(" value = %s ", decimalboxValue.getValue()));
		}
		stringQuery.append(" ORDER BY com.payulatam.model.Movement.date");
		SQLQuery<Movement> query = new SQLQuery<>(Movement.class, stringQuery.toString());
		
		Movement[] result = gigaSpace.readMultiple(query);
		setModel(result);
	}
	
	@Override
	public void onClick$btnNew() {
		Executions.sendRedirect("/pages/movement/movementDetail.zul");
	}
	
}
