package com.payulatam.prototipo;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import com.payulatam.prototipo.common.Constantes;

/**
 * Navigation controller for Tool Bar menu on template view
 * @author john.quiroga
 *
 */
public class ToolBarNavigation extends GenericForwardComposer {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -2722298524312259715L;
	
	/**
	 * Action of Button Home 
	 * @param evt
	 */
	public void onClick$btnHome(Event evt) {
		Executions.getCurrent().sendRedirect(Constantes.PATH_HOME);
	}

	/**
	 * Action of Button Customer
	 * @param evt
	 */
	public void onClick$btnCustomers(Event evt) {
		Executions.getCurrent().sendRedirect(Constantes.PATH_CUSTOMER);
	}

	/**
	 * Action of Button Account
	 * @param evt
	 */
	public void onClick$btnAccount(Event evt) {
		Executions.getCurrent().sendRedirect(Constantes.PATH_ACCOUNT);
	}

	/**
	 * Action of Button Movement
	 * @param evt
	 */
	public void onClick$btnMovements(Event evt) {
		Executions.getCurrent().sendRedirect(Constantes.PATH_MOVEMENT);
	}

	/**
	 * Action of Button Report
	 * @param evt
	 */
	public void onClick$btnReports(Event evt) {
		Executions.getCurrent().sendRedirect(Constantes.PATH_REPORT);
	}

}
