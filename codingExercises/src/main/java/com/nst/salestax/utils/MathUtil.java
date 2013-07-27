/**************************************************
 * FileName - MathUtil.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Util class that contains rounding function
 * @author blanasco (Bert L. Anasco)
 *
 */
public class MathUtil {

	/**
	 * Util method for rounding number to an increment
	 * from wikipedia {@link http://en.wikipedia.org/wiki/Rounding#Rounding_to_a_specified_increment}
	 * In general, rounding a number x to a multiple of some specified increment m entails the following steps:
	 *  	Divide x by m, let the result be y;
	 *		Round y to an integer value, call it q;
	 *	    Multiply q by m to obtain the rounded value z.
	 * @param value - the value to round
	 * @param increment -
	 * @param roundingMode
	 * @return the rounded BigDecimal
	 */
	public static BigDecimal round(BigDecimal value, BigDecimal increment,
			RoundingMode roundingMode) {
		//please see method documentation
		//Divide x by m, let the result be y
		//Round y to an integer value, call it q;
		BigDecimal divided = value.divide(increment, 0, roundingMode);
		//Multiply q by m to obtain the rounded value z.
		BigDecimal result = divided.multiply(increment);
		return result;
	}

}
