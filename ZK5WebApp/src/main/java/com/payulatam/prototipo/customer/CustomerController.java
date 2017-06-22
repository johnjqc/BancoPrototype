package com.payulatam.prototipo.customer;

import java.util.ArrayList;
import java.util.List;

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
import com.payulatam.model.Customer;
import com.payulatam.prototipo.tools.GigaSpaceController;

public class CustomerController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private ListModelList prodModel;
	
	private Grid gridCustomers;
	private Textbox textboxCustomer;
	private Textbox textboxAddress;
	private Textbox textboxPhone;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        textboxCustomer.setText("*");
        textboxAddress.setText("*");
        textboxPhone.setText("*");
        
        gridCustomers.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Customer prod = (Customer)data;
                
                new Label(prod.getName()).setParent(row);
                new Label(prod.getAddress()).setParent(row);
                new Label(prod.getPhone()).setParent(row);
                
                Cell buttons = new Cell();
                buttons.setParent(row);
                
                Button btnRemove = new Button();
                btnRemove.setImage("/images/icon-delete.png");
            	btnRemove.addEventListener("onClick", new EventListener() {
            		public void onEvent(Event event) {
            			GigaSpaceController.getGigaSpace().takeIfExistsById(Customer.class, prod.getId());
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
            			Executions.sendRedirect("/pages/customer/customerDetail.zul?id=" + id);
            		}
            	});
            	btnEdit.setParent(buttons);
            	
            }
        });
    }
	
	public void onClick$buttonSearch() {
		StringBuilder stringQuery = new StringBuilder();
		if (!"*".equals(textboxCustomer.getText()) && !textboxCustomer.getText().isEmpty()) {
			if (textboxCustomer.getText().contains("*")) {
				String toReplace = textboxCustomer.getText(); 
				toReplace = toReplace.replaceAll("\\*", "\\%");
				stringQuery.append(String.format(" name like '%s' ", toReplace));
			} else {
				stringQuery.append(String.format(" name = '%s' ", textboxCustomer.getText()));
			}
		}
		if (!"*".equals(textboxAddress.getText()) && !textboxAddress.getText().isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			if (textboxAddress.getText().contains("*")) {
				stringQuery.append(String.format(" address like '%s' ", textboxAddress.getText().replaceAll("\\*", "\\%")));
			} else {
				stringQuery.append(String.format(" address = '%s' ", textboxAddress.getText()));
			}
		}
		if (!"*".equals(textboxPhone.getText()) && !textboxPhone.getText().isEmpty()) {
			if (!stringQuery.toString().isEmpty()) {
				stringQuery.append(" and ");
			}
			if (textboxAddress.getText().contains("*")) {
				stringQuery.append(String.format(" phone like '%s' ", textboxPhone.getText()).replaceAll("\\*", "\\%"));
			} else {
				stringQuery.append(String.format(" phone = '%s' ", textboxPhone.getText()));
			}
		}
		stringQuery.append(" ORDER BY name");
		SQLQuery<Customer> query = new SQLQuery<Customer>(Customer.class, stringQuery.toString());
		
//		UrlSpaceConfigurer spaceConfigurer = new UrlSpaceConfigurer(Constantes.JINI);
//		GigaSpace gigaSpace = new GigaSpaceConfigurer(spaceConfigurer).gigaSpace();
		Customer[] result = GigaSpaceController.getGigaSpace().readMultiple(query);
		setModel(result);
	}
	
	private void setModel(Customer[] customers) {
		List<Customer> customerResult = new ArrayList<>();
		for (Customer customer : customers) {
			customerResult.add(customer);
		}
		prodModel = new ListModelList(customerResult);
		gridCustomers.setModel(prodModel);
	}
	
	public void onClick$btnNew() {
		Executions.sendRedirect("/pages/customer/customerDetail.zul");
	}
	
}
