package com.payulatam.prototipo;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

/**
 * 
 * @author john.quiroga
 *
 */
public class ToolBarNavigation extends GenericForwardComposer {

	private static final long serialVersionUID = -2722298524312259715L;
	
	/**
	 * 
	 * @param evt
	 */
	public void onClick$btnHome(Event evt) {
		Executions.getCurrent().sendRedirect("/index.zul");
	}

	/**
	 * 
	 * @param evt
	 */
	public void onClick$btnCustomers(Event evt) {
		Executions.getCurrent().sendRedirect("/pages/customer/customers.zul");
	}

	/**
	 * 
	 * @param evt
	 */
	public void onClick$btnAccount(Event evt) {
		Executions.getCurrent().sendRedirect("/pages/account/account.zul");
	}

	/**
	 * 
	 * @param evt
	 */
	public void onClick$btnMovements(Event evt) {
		Executions.getCurrent().sendRedirect("/pages/movement/movement.zul");
	}

	/**
	 * 
	 * @param evt
	 */
	public void onClick$btnReports(Event evt) {
		Executions.getCurrent().sendRedirect("/pages/report/report.zul");
	}

}
