package com.payulatam.prototipo;

import java.util.Arrays;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

/**
 * Utilities for controllers
 * @author John
 *
 */
public class ControllerHelper  {

	/**
	 * Set default Comboitem with * on Combobox object
	 * @param parent
	 */
	public static void setItemDefault(Combobox parent) {
		Comboitem comboitemDefault = new Comboitem("*");
        comboitemDefault.setValue("*");
        comboitemDefault.setParent(parent);
        parent.setSelectedItem(comboitemDefault);
	}
	
	/**
	 * Fill Combobox with items from Enum values
	 * @param parent Combobox object to fill
	 * @param enumValues Enum class
	 */
	public static <J extends Enum<J>> void enumToComboItem(Combobox parent, Class<J> enumValues) {
		Arrays.stream(enumValues.getEnumConstants()).forEach(item -> {
        	Comboitem comboitemDebit = new Comboitem(item.toString());
            comboitemDebit.setValue(item.toString());
            comboitemDebit.setParent(parent);
        });
	}
}
