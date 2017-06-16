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

import com.payulatam.entities.Cliente;

public class ClienteController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 6077674101236551588L;
	
	private List<Cliente> clientes;
	
	private Grid customers;

	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        clientes = new ArrayList<>();
        GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer("jini://*/*/prototipo")).gigaSpace();

		Cliente[] spaceEntries = gigaSpace.readMultiple(new Cliente(), Integer.MAX_VALUE);
		
		for (Cliente cl : spaceEntries) {
			System.out.println(cl.getNombre());
			clientes.add(cl);
		}
        
        
//        for (int i = 0; i < 3; i++) {
//        	Cliente cl = new Cliente();
//        	cl.setNombre("n" + i);
//			cl.setDireccion("dir" + i);
//			cl.setTelefono("tel" + i);
//			clientes.add(cl);
//		}
        
        ListModelList prodModel = new ListModelList(clientes);
        customers.setModel(prodModel);
        
        customers.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Cliente prod = (Cliente)data;
                 
                
                new Label(prod.getNombre()).setParent(row);
                new Label(""+prod.getDireccion()).setParent(row);
                new Label(""+prod.getTelefono()).setParent(row);
//                initOperation(prod).setParent(row);
            }
             
        });
        
    }
	
}
