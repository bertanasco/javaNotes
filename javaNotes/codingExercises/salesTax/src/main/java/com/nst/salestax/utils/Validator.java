/**************************************************
 * FileName - Validator.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.utils;

import com.nst.salestax.exception.ErrorCode;

/**
 * Utility class used for validations
 * @author blanasco (Bert L. Anasco)
 *
 */
public class Validator {

	/**
	 * Check if a double input is zero,
	 * Throw an IllegalArgumentException if it is
	 * @param input
	 * @param message
	 *
	 */
	public static void checkIfZero(final double input, final ErrorCode error){
		if (input == 0){
			throw new IllegalArgumentException(error.toString());
		}
	}

	/**
	 * Check if object is null
	 * Throw an IllegalArgumentException if it is
	 * @param input
	 * @param message
	 *
	 */
	public static void checkIfNull(final Object input, final ErrorCode error){
		if (input == null){
			throw new IllegalArgumentException(error.toString());
		}
	}

}
