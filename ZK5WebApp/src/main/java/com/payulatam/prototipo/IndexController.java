package com.payulatam.prototipo;

import java.util.List;
import java.util.Locale;

import org.zkoss.util.Locales;
import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;

public class IndexController extends GenericForwardComposer {
 
    private static final long serialVersionUID = 1L;
 
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        setLanguage(self);
    }
    
    @SuppressWarnings("unchecked")
    private void setLanguage(Component rootComponent) {
        List<Component> components = (List<Component>) rootComponent
                .getChildren();
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            setComponentLanguage(component);
            if (component.getChildren().size() > 0)
                setLanguage(component);
        }
    }
    
    private void setComponentLanguage(Component component) {
        String path = component.getPage().getRequestPath();
        String i18n = Labels.getLabel(path + "/" + component.getId());
        if (i18n != null) {
            if (component instanceof Label)
                ((Label) component).setValue(i18n);
            else if (component instanceof Button)
                ((Button) component).setLabel(i18n);
        }
    }
    
    private void changeLocale(String locale) {
        Session session = Sessions.getCurrent();
        Locale preferredLocale = Locales.getLocale(locale);
        session.setAttribute(Attributes.PREFERRED_LOCALE, preferredLocale);
        Locales.setThreadLocal(preferredLocale);
        setLanguage(self);
    }
}