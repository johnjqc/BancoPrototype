package com.payulatam.common;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

/**
 * Get GigaSpace Connection object
 * @author john.quiroga
 *
 */
public class GigaSpaceHelper {

	/**
	 * Connect to Space specific in Constantes.JINI
	 * @return GigaSpace object
	 */
	public static GigaSpace getGigaSpace() {
		UrlSpaceConfigurer spaceConfigurer = new UrlSpaceConfigurer(Constantes.JINI);
		GigaSpace gigaSpace = new GigaSpaceConfigurer(spaceConfigurer).gigaSpace();
		return gigaSpace;
	}

}
