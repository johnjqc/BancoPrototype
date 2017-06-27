package com.payulatam.common;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.payulatam.model.Movement;

public class UtilsTest {

	@Test
	public void testArrayToList() throws Exception {
		Movement[] arr = new Movement[3];
		for (int i = 0; i < 3; i++) {
			Movement m1 = new Movement();
			m1.setId("" + (i +1));
			arr[i] = m1;
		}
		 
		List<Movement>  lst = Utils.arrayToList(arr);
		List<String> ids = lst.stream().map(Movement::getId).collect(Collectors.toList());
		Assert.assertTrue("[1, 2, 3]".equals(ids.toString()));
	}

}
