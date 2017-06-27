package com.payulatam.prototipo.account;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

import com.payulatam.common.Constantes;
import com.payulatam.gs.AccountRepository;
import com.payulatam.gs.CustomerRepository;
import com.payulatam.model.Account;
import com.payulatam.model.Customer;
import com.payulatam.prototipo.BaseController;
import com.payulatam.prototipo.ControllerHelper;

public class AccountController extends BaseController<Account> {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	AccountRepository<Account> respository = new AccountRepository<>(gigaSpace);
	CustomerRepository<Customer> respositoryCustomer = new CustomerRepository<>(gigaSpace);
	
	private Combobox comboboxCustomer;
	private Textbox textboxNumber;
	private Decimalbox decimalboxBalance;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        ControllerHelper.setItemDefault(comboboxCustomer);
    	
        Customer[] customers = respositoryCustomer.findByCriteria("ORDER BY name");
        for (int i = 0; i < customers.length; i++) {
        	Comboitem comboitem = new Comboitem();
        	comboitem.setValue(customers[i].getId());
        	comboitem.setLabel(customers[i].getName());
        	comboitem.setParent(comboboxCustomer);
		}
        
        textboxNumber.setText("*");
        
        gridResults.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Account prod = (Account)data;
                
                Customer customer = gigaSpace.readById(Customer.class, prod.getCustomerId());
//                Customer customer = respositoryCustomer.findById(prod.getCustomerId());
                		
                new Label(customer.getName()).setParent(row);
                new Label(prod.getNumber()).setParent(row);
                new Label(prod.getBalance().toString()).setParent(row);
                
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
            	
            	Button btnEdit = new Button();
                btnEdit.setImage(Constantes.ICON_EDIT);
            	btnEdit.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			String id = prod.getId();
            			id = id.replaceAll("\\^", ".");
            			Executions.sendRedirect(Constantes.PATH_ACCOUNT_DETAIL + "?id=" + id);
            		}
            	});
            	btnEdit.setParent(buttons);
            }
        });
    }
	
	@Override
	public void onClick$buttonSearch() {
		Comboitem itemCustomer = comboboxCustomer.getSelectedItem();
		Account[] result = respository.serach(String.valueOf(itemCustomer.getValue()), 
				textboxNumber.getText(), decimalboxBalance.getValue());
		setModel(result);
	}
	
	@Override
	public void onClick$btnNew() {
		Executions.sendRedirect(Constantes.PATH_ACCOUNT_DETAIL);
	}
	
}
