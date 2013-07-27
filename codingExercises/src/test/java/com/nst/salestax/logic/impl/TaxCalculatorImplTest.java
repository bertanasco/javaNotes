/**************************************************
 * FileName - TaxCalculatorTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.logic.impl;

import static org.junit.Assert.assertTrue;
import static com.nst.salestax.testUtils.TestUtil.*;

import org.junit.Test;

import com.nst.salestax.logic.TaxCalculator;
import com.nst.salestax.testUtils.TestUtil;

/**
 * Test class for the TaxCalculatorImpl}.
 * @author blanasco (Bert L. Anasco)
 */
public class TaxCalculatorImplTest {

	private static final TaxCalculator taxCalc = new TaxCalculatorImpl();
	private static final TaxCalculator taxCalcWithScale3 = new TaxCalculatorImpl("0.05",3);
	@Test
	public void basicExcempted(){
		assertTrue("basic excempted goods should have zero tax",
				taxCalc.computeRoundedUpTax(TestUtil.BASIC_EXCEMPTED_PRODUCT).equals(bd("0.00")));
	}

	@Test
	public void checkScale(){
		assertTrue("scale should be right ",
				taxCalcWithScale3.computeRoundedUpTax(TestUtil.BASIC_EXCEMPTED_PRODUCT).equals(bd("0.000")));
	}

	@Test(expected=NumberFormatException.class)
	public void invalidPriceFormat(){
		assertTrue("scale should be right ",
				taxCalcWithScale3.computeRoundedUpTax(TestUtil.BASIC_EXCEMPTED_PRODUCT).equals(bd("asdf")));
	}

	@Test
	public void importedExcempted(){
			assertTrue("Invalid computed Tax",
				taxCalc.computeRoundedUpTax(TestUtil.IMPORT_EXCEMPTED_PRODUCT ).equals(bd("0.50")));
	}

	@Test
	public void basic(){
		assertTrue("Invalid computed Tax",
				taxCalc.computeRoundedUpTax(TestUtil.BASIC_PRODUCT).equals(bd("1.65")));
	}

	@Test
	public void basicImported(){
		assertTrue("Invalid computed Tax",
				taxCalc.computeRoundedUpTax(TestUtil.BASIC_IMPORTED_PRODUCT).equals(bd("7.15")));
	}

}
