package com.payulatam.prototipo.customer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import com.payulatam.common.GigaSpaceController;
import com.payulatam.model.Customer;

public class CustomerDetailController extends GenericForwardComposer {

	private static final long serialVersionUID = 2409508627321213561L;
	
	private Customer actualCustomer;
	
	private Textbox textboxCustomer;
	private Textbox textboxAddress;
	private Textbox textboxPhone;
	private Button buttonEdit;
	private Button buttonNew;
	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        Execution execution = Executions.getCurrent();
        String id = execution.getParameter("id");
        
        if (id == null || id.isEmpty()) {
        	buttonNew.setVisible(true);
        } else {
        	buttonEdit.setVisible(true);
        	id = id.replaceAll("\\.", "^");
        	actualCustomer = GigaSpaceController.getGigaSpace().readById(Customer.class, id);
        	if (actualCustomer != null) {
        		textboxCustomer.setText(actualCustomer.getName());
        		textboxAddress.setText(actualCustomer.getAddress());
        		textboxPhone.setText(actualCustomer.getPhone());
        	}
        }
	}
	
	public void onClick$buttonNew() {
		actualCustomer = new Customer();
		actualCustomer.setName(textboxCustomer.getText());
		actualCustomer.setAddress(textboxAddress.getText());
		actualCustomer.setPhone(textboxPhone.getText());
		actualCustomer.setSpacerouting(1);
		GigaSpaceController.getGigaSpace().write(actualCustomer);
		Executions.sendRedirect("/pages/customer/customers.zul");
	}
	
	public void onClick$buttonEdit() {
		actualCustomer.setName(textboxCustomer.getText());
		actualCustomer.setAddress(textboxAddress.getText());
		actualCustomer.setPhone(textboxPhone.getText());
		GigaSpaceController.getGigaSpace().write(actualCustomer);
		Executions.sendRedirect("/pages/customer/customers.zul");
	}
	
}
