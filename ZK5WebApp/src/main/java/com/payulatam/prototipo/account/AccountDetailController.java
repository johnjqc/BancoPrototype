package com.payulatam.prototipo.account;

import java.math.BigDecimal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.model.Account;
import com.payulatam.model.Customer;
import com.payulatam.prototipo.tools.GigaSpaceController;

public class AccountDetailController extends GenericForwardComposer {

	private static final long serialVersionUID = 2409508627321213561L;
	
	private Account actualAccount;
	
	private Combobox comboboxCustomer;
	private Textbox textboxNumber;
	private Textbox textboxBalance;
	private Button buttonEdit;
	private Button buttonNew;
	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
		SQLQuery<Customer> query = new SQLQuery<Customer>(Customer.class, "ORDER BY name");
        Customer[] customers = GigaSpaceController.getGigaSpace().readMultiple(query);
        for (int i = 0; i < customers.length; i++) {
        	Comboitem comboitem = new Comboitem();
        	comboitem.setValue(customers[i].getId());
        	comboitem.setLabel(customers[i].getName());
        	comboitem.setParent(comboboxCustomer);
		}
        if (customers != null && customers.length > 0) {
        	comboboxCustomer.setSelectedIndex(0);
        }
        
        Execution execution = Executions.getCurrent();
        String id = execution.getParameter("id");
        
        if (id == null || id.isEmpty()) {
        	buttonNew.setVisible(true);
        } else {
        	buttonEdit.setVisible(true);
        	id = id.replaceAll("\\.", "^");
        	actualAccount = GigaSpaceController.getGigaSpace().readById(Account.class, id);
        	if (actualAccount != null) {
        		int indexItem = 0;
        		for (int i = 0; i < customers.length; i++) {
        			if (customers[i].getId().equals(actualAccount.getCustomerId())) {
        				indexItem = i;
        			}
        		}
        		comboboxCustomer.setSelectedIndex(indexItem);
        		textboxNumber.setText(actualAccount.getNumber());
        		textboxBalance.setText(actualAccount.getBalance().toString());
        	}
        }
	}
	
	public void onClick$buttonNew() {
		actualAccount = new Account();
		
		actualAccount.setNumber(textboxNumber.getText());
		actualAccount.setBalance(new BigDecimal(textboxBalance.getText()));
		actualAccount.setCustomerId("" + comboboxCustomer.getSelectedItem().getValue());
		actualAccount.setSpacerouting(1);
		GigaSpaceController.getGigaSpace().write(actualAccount);
		Executions.sendRedirect("/pages/account/account.zul");
	}
	
	public void onClick$buttonEdit() {
		actualAccount.setNumber(textboxNumber.getText());
		actualAccount.setBalance(new BigDecimal(textboxBalance.getText()));
		actualAccount.setCustomerId("" + comboboxCustomer.getSelectedItem().getValue());
		GigaSpaceController.getGigaSpace().write(actualAccount);
		Executions.sendRedirect("/pages/account/account.zul");
	}
	
}
