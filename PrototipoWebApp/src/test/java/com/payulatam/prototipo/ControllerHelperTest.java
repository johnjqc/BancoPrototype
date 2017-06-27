package com.payulatam.prototipo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import com.payulatam.enums.MovementType;
import com.payulatam.model.Movement;

/**
 * The Class ControllerHelperTest.
 */
public class ControllerHelperTest {

	/**
	 * Test array to list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testArrayToList() throws Exception {
		Movement[] arr = new Movement[3];
		for (int i = 0; i < 3; i++) {
			Movement m1 = new Movement();
			m1.setId("" + (i +1));
			arr[i] = m1;
		}
		 
		List<Movement>  lst = ControllerHelper.arrayToList(arr);
		List<String> ids = lst.stream().map(Movement::getId).collect(Collectors.toList());
		Assert.assertTrue("[1, 2, 3]".equals(ids.toString()));
	}
	
	/**
	 * Test set item default.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSetItemDefault() throws Exception {
		Combobox parent = new Combobox();
		ControllerHelper.setItemDefault(parent);
		Assert.assertTrue(parent.getSelectedIndex() == 0);
	}
	
	/**
	 * Test enum to combo item.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEnumToComboItem() throws Exception {
		Combobox parent = new Combobox();
		ControllerHelper.enumToComboItem(parent, MovementType.class);
		List<String> enumList =  Stream.of(MovementType.values()).map(Enum::name).collect(Collectors.toList());
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < parent.getItemCount(); i++) {
			Comboitem item = parent.getItemAtIndex(i);
			res.add("" + item.getValue());
		}
		Assert.assertTrue(enumList.toString().equals(res.toString()));
	}
	
}
