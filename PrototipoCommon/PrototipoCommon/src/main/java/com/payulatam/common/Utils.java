package com.payulatam.common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.payulatam.model.BaseEntity;

/**
 * Utilities for Prototipe Aplication
 * @author john.quiroga
 *
 */
public class Utils {
	
	/**
	 * Convert array Object to List<Object>
	 * @param array Data to process
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	public static <J extends BaseEntity> List<J> arrayToList(J[] array) {
		return (List<J>)(Object)Stream.of(array).map(J::clone).collect(Collectors.toList());
	}

}
