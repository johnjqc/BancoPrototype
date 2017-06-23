package com.payulatam.prototipo.account;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.common.Constantes;
import com.payulatam.model.Account;
import com.payulatam.model.Customer;
import com.payulatam.prototipo.tools.GigaSpaceController;

public class AccountController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private ListModelList prodModel;
	
	private Grid gridAccounts;
	private Combobox comboboxCustomer;
	private Textbox textboxNumber;
	private Decimalbox decimalboxBalance;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        Comboitem comboitemDefault = new Comboitem();
        comboitemDefault.setValue("*");
        comboitemDefault.setLabel("*");
        comboitemDefault.setParent(comboboxCustomer);
        comboboxCustomer.setSelectedItem(comboitemDefault);
    	
        SQLQuery<Customer> query = new SQLQuery<Customer>(Customer.class, "ORDER BY name");
        Customer[] customers = GigaSpaceController.getGigaSpace().readMultiple(query);
        for (int i = 0; i < customers.length; i++) {
        	Comboitem comboitem = new Comboitem();
        	comboitem.setValue(customers[i].getId());
        	comboitem.setLabel(customers[i].getName());
        	comboitem.setParent(comboboxCustomer);
		}
        
        textboxNumber.setText("*");
        
        gridAccounts.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Account prod = (Account)data;
                
                Customer customers = GigaSpaceController.getGigaSpace().readById(Customer.class, prod.getCustomerId());
                		
                new Label(customers.getName()).setParent(row);
                new Label(prod.getNumber()).setParent(row);
                new Label(prod.getBalance().toString()).setParent(row);
                
                Cell buttons = new Cell();
                buttons.setParent(row);
                
                Button btnRemove = new Button();
                btnRemove.setImage("/images/icon-delete.png");
            	btnRemove.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			GigaSpaceController.getGigaSpace().takeIfExistsById(Account.class, prod.getId());
            			prodModel.remove(prod);
            		}
            	});
            	btnRemove.setParent(buttons);
            	
            	Button btnEdit = new Button();
                btnEdit.setImage("/images/icon-edit.png");
            	btnEdit.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			String id = prod.getId();
            			id = id.replaceAll("\\^", ".");
            			Executions.sendRedirect("/pages/account/accountDetail.zul?id=" + id);
            		}
            	});
            	btnEdit.setParent(buttons);
            }
        });
    }
	
	public void onClick$buttonSearch() {
		StringBuilder stringQuery = new StringBuilder();
		
		Comboitem itemCustomer = comboboxCustomer.getSelectedItem();
		if (itemCustomer != null && !"*".equals(itemCustomer.getValue())) {
			stringQuery.append("customerId = '" + itemCustomer.getValue() + "'");
		}
		
		if (!"*".equals(textboxNumber.getText()) && !textboxNumber.getText().isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			if (textboxNumber.getText().contains("*")) {
				String toReplace = textboxNumber.getText(); 
				toReplace = toReplace.replaceAll("\\*", "\\%");
				stringQuery.append(String.format(" number like %s ", toReplace));
			} else {
				stringQuery.append(String.format(" number = %s ", textboxNumber.getText()));
			}
		}
		if (decimalboxBalance.getValue() != null) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			stringQuery.append(String.format(" balance = %s ", decimalboxBalance.getValue()));
		}
		SQLQuery<Account> query = new SQLQuery<>(Account.class, stringQuery.toString());
		
		UrlSpaceConfigurer spaceConfigurer = new UrlSpaceConfigurer(Constantes.JINI);
		GigaSpace gigaSpace = new GigaSpaceConfigurer(spaceConfigurer).gigaSpace();
		Account[] result = gigaSpace.readMultiple(query);
		setModel(result);
	}
	
	private void setModel(Account[] Accounts) {
		List<Account> accountResult = new ArrayList<>();
		for (Account account : Accounts) {
			accountResult.add(account);
		}
		prodModel = new ListModelList(accountResult);
		gridAccounts.setModel(prodModel);
	}
	
	public void onClick$btnNew() {
		Executions.sendRedirect("/pages/account/accountDetail.zul");
	}
	
}
