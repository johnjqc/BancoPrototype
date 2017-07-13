package com.payulatam.prototipo.customer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

import com.payulatam.prototipo.BaseController;
import com.payulatam.prototipo.common.Constantes;
import com.payulatam.prototipo.gs.CustomerRepository;
import com.payulatam.prototipo.model.Customer;

/**
 * Customer controller for view
 * @author John Quiroga
 *
 */
public class CustomerController extends BaseController<Customer> {
	
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 6077674101236551588L;
	
	/**
	 * Repository DAO customer
	 */
	CustomerRepository<Customer> respository;
	
	/**
	 * Textbox component
	 */
	private Textbox textboxCustomer;

	/**
	 * Textbox component
	 */
	private Textbox textboxAddress;

	/**
	 * Textbox component
	 */
	private Textbox textboxPhone;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        respository = new CustomerRepository<>(gigaSpace);
        
        textboxCustomer.setText("*");
        textboxAddress.setText("*");
        textboxPhone.setText("*");
        
        gridResults.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Customer prod = (Customer)data;
                
                new Label(prod.getName()).setParent(row);
                new Label(prod.getAddress()).setParent(row);
                new Label(prod.getPhone()).setParent(row);
                
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
            			Executions.sendRedirect(Constantes.PATH_CUSTOMER_DETAIL + "?id=" + id);
            		}
            	});
            	btnEdit.setParent(buttons);
            	
            }
        });
    }
	
	@Override
	public void onClick$buttonSearch() {
		Customer[] result = respository.serach(textboxCustomer.getText(), textboxAddress.getText(), textboxPhone.getText());
		setModel(result);
	}
	
	@Override
	public void onClick$btnNew() {
		Executions.sendRedirect(Constantes.PATH_CUSTOMER_DETAIL);
	}
	
}
