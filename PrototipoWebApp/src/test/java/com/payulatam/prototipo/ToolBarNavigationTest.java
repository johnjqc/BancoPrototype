package com.payulatam.prototipo;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zats.mimic.Zats;
import org.zkoss.zul.Label;

public class ToolBarNavigationTest {
	
	@BeforeClass
	public static void beforeClass() {
		Zats.init(System.getProperty("user.dir") + "/WebContent");
	}
	
	@Test
	public void testName() throws Exception {
		DesktopAgent desktop = Zats.newClient().connect("/pages/home.zul");
		ComponentAgent button = desktop.query("#btnHome");
		ComponentAgent label = desktop.query("#lblAplication");
		
		button.click();
		Assert.assertTrue("Aplicacion Prototipo".equals(label.as(Label.class).getValue()));
	}
	
}
