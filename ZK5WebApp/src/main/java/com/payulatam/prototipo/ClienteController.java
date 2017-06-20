package com.payulatam.prototipo;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.payulatam.entities.Customer;

public class ClienteController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private List<Customer> clientes;
	
	private Grid customers;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        clientes = new ArrayList<>();
        
        GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer("jini://*/*/prototipo")).create();
        Customer[] spaceEntries = gigaSpace.readMultiple(new Customer(), Integer.MAX_VALUE);
        
		for (Customer cl : spaceEntries) {
			clientes.add(cl);
		}
        
        ListModelList prodModel = new ListModelList(clientes);
        customers.setModel(prodModel);
        
        customers.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Customer prod = (Customer)data;
                
                new Label(prod.getName()).setParent(row);
                new Label(""+prod.getAddress()).setParent(row);
                new Label(""+prod.getPhone()).setParent(row);
//                initOperation(prod).setParent(row);
            }
             
        });
        
    }
	
}
