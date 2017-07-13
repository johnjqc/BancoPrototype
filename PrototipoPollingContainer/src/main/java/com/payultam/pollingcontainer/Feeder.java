package com.payultam.pollingcontainer;

import java.math.BigDecimal;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.payulatam.prototipo.common.Constantes;
import com.payulatam.prototipo.model.Movement;

public class Feeder {
	
	public Feeder() {
		UrlSpaceConfigurer spaceConfigurer = new UrlSpaceConfigurer(Constantes.JINI);
		GigaSpace gigaspace = new GigaSpaceConfigurer(spaceConfigurer).gigaSpace();
		
		Movement mov = new Movement();
		mov.setSpacerouting(1L);
		mov.setType("DEBIT");
		mov.setValue(new BigDecimal(1001));
		mov.setProcessed(false);
		
		gigaspace.write(mov);
	}
	
	public static void main(String[] args) {
		new Feeder();
	}

}
