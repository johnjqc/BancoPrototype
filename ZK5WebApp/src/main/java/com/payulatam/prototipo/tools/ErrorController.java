package com.payulatam.prototipo.tools;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class ErrorController extends GenericForwardComposer {

	private static final long serialVersionUID = -5567886876805293370L;

	public void onClick$quitSession() {
		Executions.sendRedirect("/");
	}

}
