/**************************************************
 * FileName - MathUtilTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.utils;

import static com.nst.salestax.testUtils.TestUtil.*;
import static org.junit.Assert.*;

import java.math.RoundingMode;

import org.junit.Test;

/**
 * Test Class for MathUtil
 * @author blanasco (Bert L. Anasco)
 */
public class MathUtilTest {

	@Test
	public void testRound(){
		assertTrue( bd("7.15").equals(MathUtil.round(bd("7.125"), bd("0.05"),  RoundingMode.UP))  );
	}

	@Test(expected=NumberFormatException.class)
	public void invalidFormat(){
		assertTrue( bd("7.15").equals(MathUtil.round(bd("asdf"), bd("0.05"),  RoundingMode.UP))  );
	}
}
