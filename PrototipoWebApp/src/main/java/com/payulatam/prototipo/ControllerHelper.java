package com.payulatam.prototipo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import com.payulatam.model.BaseEntity;

/**
 * Utilities for controllers
 * @author John
 *
 */
public class ControllerHelper  {
	
	/**
	 * Convert array Object to List<Object>
	 * @param array Data to process
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	public static <J extends BaseEntity> List<J> arrayToList(J[] array) {
		return (List<J>)(Object)Stream.of(array).map(J::clone).collect(Collectors.toList());
	}

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
        	Comboitem comboItem = new Comboitem(item.toString());
            comboItem.setValue(item.toString());
            comboItem.setParent(parent);
        });
	}
}
