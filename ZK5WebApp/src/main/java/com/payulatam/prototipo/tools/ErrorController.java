package com.payulatam.prototipo.tools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

public class ErrorController extends GenericForwardComposer {

	private static final long serialVersionUID = -5567886876805293370L;

	private Component modalWindow;

	private Textbox stacktrace;

	private boolean isHelpLink = false;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("pageErrorController", this, true);
		logError();
		modalWindow = comp;
		if (stacktrace != null) {
			stacktrace.setValue(getStacktrace());
		}
	}

	private void logError() {
		String urlPath = (String) Executions.getCurrent().getAttribute("javax.servlet.forward.servlet_path");
		Throwable exception = (Throwable) Executions.getCurrent().getAttribute("javax.servlet.error.exception");
		String errorMessage = (String) Executions.getCurrent().getAttribute("javax.servlet.error.message");
		Integer code = (Integer) Executions.getCurrent().getAttribute("javax.servlet.error.status_code");

		if (urlPath != null && urlPath.contains("help")) {
			isHelpLink = true;
		}

		if (code != null) {
			errorMessage += " [Status Code: " + code + "]";
			if (code == HttpServletResponse.SC_FORBIDDEN) {
				String uri = (String) Executions.getCurrent().getAttribute("javax.servlet.error.request_uri");
				errorMessage += " [Request URI: " + uri + "]";
			}
		}
//		LOG.error(errorMessage, exception);
	}

	public void onClick$continueWorking() {
		modalWindow.detach();
	}

	public void onClick$reload() {
		Executions.sendRedirect(null);
	}

	public void onClick$quitSession() {
		HttpServletRequest nativeRequest = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		nativeRequest.getSession().invalidate();
		Executions.sendRedirect("/");
	}

	private String getStacktrace() {
		Throwable exception = (Throwable) Executions.getCurrent().getAttribute("javax.servlet.error.exception");
		if (exception != null) {
			Writer stacktrace = new StringWriter();
			exception.printStackTrace(new PrintWriter(stacktrace));

			return stacktrace.toString();
		}
		return "";
	}

	public boolean isVisibleOnHelpPage() {
		return isHelpLink;
	}
}
