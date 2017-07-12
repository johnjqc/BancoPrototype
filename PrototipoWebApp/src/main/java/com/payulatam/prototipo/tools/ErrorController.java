package com.payulatam.prototipo.tools;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;

/**
 * Error controller ZK for view
 * @author John
 *
 */
public class ErrorController extends GenericForwardComposer {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -5567886876805293370L;

	public void onClick$quitSession() {
		Executions.sendRedirect("/");
	}

}
