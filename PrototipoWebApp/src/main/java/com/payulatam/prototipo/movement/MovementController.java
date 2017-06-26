package com.payulatam.prototipo.movement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.common.GigaSpaceController;
import com.payulatam.enums.MovementType;
import com.payulatam.model.Account;
import com.payulatam.model.Movement;

public class MovementController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private ListModelList prodModel;
	
	private Grid gridMovements;
	private Combobox comboboxAccount;
	private Combobox comboboxType;
	private Datebox dateboxDate;
	private Decimalbox decimalboxValue;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        Comboitem comboitemDefault = new Comboitem("*");
        comboitemDefault.setValue("*");
        comboitemDefault.setParent(comboboxAccount);
        comboboxAccount.setSelectedItem(comboitemDefault);
        
        SQLQuery<Account> query = new SQLQuery<>(Account.class, "");
        Account[] accounts = GigaSpaceController.getGigaSpace().readMultiple(query);
        for (int i = 0; i < accounts.length; i++) {
        	Comboitem comboitem = new Comboitem(accounts[i].getNumber());
        	comboitem.setValue(accounts[i].getId());
        	comboitem.setParent(comboboxAccount);
		}
        
        Comboitem comboitemDefaultType = new Comboitem("*");
        comboitemDefaultType.setValue("*");
        comboitemDefaultType.setParent(comboboxType);
        comboboxType.setSelectedItem(comboitemDefaultType);
        
        Comboitem comboitemDebit = new Comboitem(MovementType.DEBIT.toString());
        comboitemDebit.setValue(MovementType.DEBIT.toString());
        comboitemDebit.setParent(comboboxType);
        
        Comboitem comboitemCredit = new Comboitem(MovementType.CREDIT.toString());
        comboitemCredit.setValue(MovementType.CREDIT.toString());
        comboitemCredit.setParent(comboboxType);
        
        gridMovements.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Movement prod = (Movement)data;
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Account account = GigaSpaceController.getGigaSpace().readById(Account.class, prod.getAccountId());
                
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
            			GigaSpaceController.getGigaSpace().takeIfExistsById(Movement.class, prod.getId());
            			prodModel.remove(prod);
            		}
            	});
            	btnRemove.setParent(buttons);
            	
            }
        });
    }
	
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
		
		Movement[] result = GigaSpaceController.getGigaSpace().readMultiple(query);
		setModel(result);
	}
	
	private void setModel(Movement[] movements) {
		List<Movement> movementResult = new ArrayList<>();
		for (Movement movement : movements) {
			movementResult.add(movement);
		}
		prodModel = new ListModelList(movementResult);
		gridMovements.setModel(prodModel);
	}
	
	public void onClick$btnNew() {
		Executions.sendRedirect("/pages/movement/movementDetail.zul");
	}
	
}
