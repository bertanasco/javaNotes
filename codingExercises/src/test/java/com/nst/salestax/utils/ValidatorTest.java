/**************************************************
 * FileName - ValidatorTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.nst.salestax.exception.ErrorCode;

/**
 * Test class for Validator
 * @author blanasco (Bert L. Anasco)
 */
public class ValidatorTest {

	private static final String TEST_MESSAGE = ErrorCode.PRICE_IS_ZERO.toString();
	@Rule
	public ExpectedException expected =  ExpectedException.none();

	@Test
	public  void checkIfZero(){
		expected.expect(IllegalArgumentException.class);
		expected.expectMessage(TEST_MESSAGE);
		Validator.checkIfZero(0, ErrorCode.PRICE_IS_ZERO);
	}

	@Test
	public  void checkIfNull(){
		expected.expect(IllegalArgumentException.class);
		expected.expectMessage(TEST_MESSAGE);
		Validator.checkIfNull(null, ErrorCode.PRICE_IS_ZERO);
	}

}
