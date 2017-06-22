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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.common.Constantes;
import com.payulatam.model.Account;

public class AccountController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private ListModelList prodModel;
	
	private Grid gridAccounts;
	private Textbox textboxNumber;
	private Textbox textboxBalance;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        UrlSpaceConfigurer spaceConfigurer = new UrlSpaceConfigurer(Constantes.JINI);
		GigaSpace gigaSpace = new GigaSpaceConfigurer(spaceConfigurer).gigaSpace();
		Account[] spaceEntries = gigaSpace.readMultiple(new Account(), Integer.MAX_VALUE);
        setModel(spaceEntries);
        
        gridAccounts.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Account prod = (Account)data;
                
                new Label(prod.getNumber()).setParent(row);
                new Label(prod.getBalance().toString()).setParent(row);
                
                Cell buttons = new Cell();
                buttons.setParent(row);
                
                Button btnRemove = new Button();
                btnRemove.setImage("/images/icon-delete.png");
            	btnRemove.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			gigaSpace.takeIfExistsById(Account.class, prod.getId());
            			prodModel.remove(prod);
            		}
            	});
            	btnRemove.setParent(buttons);
            	
            	Button btnEdit = new Button();
                btnEdit.setImage("/images/icon-edit.png");
            	btnEdit.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			Executions.sendRedirect("/pages/account/accountDetail.zul?id=" + prod.getId());
            		}
            	});
            	btnEdit.setParent(buttons);
            	
            }
        });
    }
	
	public void onClick$buttonSearch() {
		StringBuilder stringQuery = new StringBuilder();
		if (!"*".equals(textboxNumber.getText()) && !textboxNumber.getText().isEmpty()) {
			if (textboxNumber.getText().contains("*")) {
				String toReplace = textboxNumber.getText(); 
				toReplace = toReplace.replaceAll("\\*", "\\%");
				stringQuery.append(String.format(" name like '%s' ", toReplace));
			} else {
				stringQuery.append(String.format(" name = '%s' ", textboxNumber.getText()));
			}
		}
		if (!"*".equals(textboxBalance.getText()) && !textboxBalance.getText().isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			if (textboxBalance.getText().contains("*")) {
				stringQuery.append(String.format(" address like '%s' ", textboxBalance.getText().replaceAll("\\*", "\\%")));
			} else {
				stringQuery.append(String.format(" address = '%s' ", textboxBalance.getText()));
			}
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
