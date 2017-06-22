package com.payulatam.prototipo.account;

import java.math.BigDecimal;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import com.payulatam.common.Constantes;
import com.payulatam.model.Account;

public class AccountDetailController extends GenericForwardComposer {

	private static final long serialVersionUID = 2409508627321213561L;
	
	private Account actualAccount;
	
	private Textbox textboxCustomer;
	private Textbox textboxNumber;
	private Textbox textboxBalance;
	private Button buttonEdit;
	private Button buttonNew;
	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(Constantes.JINI)).gigaSpace();
        
        Execution execution = Executions.getCurrent();
        String id = execution.getParameter("id");
        
        if (id == null || id.isEmpty()) {
        	buttonNew.setVisible(true);
        } else {
        	buttonEdit.setVisible(true);
        	actualAccount = gigaSpace.readById(Account.class, Integer.parseInt(id));
        	if (actualAccount != null) {
//        		textboxCustomer.setText(actualAccount.getName());
        		textboxNumber.setText(actualAccount.getNumber());
        		textboxBalance.setText(actualAccount.getBalance().toString());
        	}
        }
	}
	
	public void onClick$buttonNew() {
		actualAccount = new Account();
		
//		actualAccount.setName(textboxCustomer.getText());
		actualAccount.setNumber(textboxNumber.getText());
		actualAccount.setBalance(new BigDecimal(textboxBalance.getText()));
		actualAccount.setSpacerouting(1);
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(Constantes.JINI)).gigaSpace();
		gigaSpace.write(actualAccount);
		Executions.sendRedirect("/pages/customer/customers.zul");
	}
	
	public void onClick$buttonEdit() {
//		actualAccount.setName(textboxCustomer.getText());
		actualAccount.setNumber(textboxNumber.getText());
		actualAccount.setBalance(new BigDecimal(textboxBalance.getText()));
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(Constantes.JINI)).gigaSpace();
		gigaSpace.write(actualAccount);
		Executions.sendRedirect("/pages/customer/customers.zul");
	}
	
}
