/**************************************************
 * FileName - ProductTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.nst.salestax.testUtils.TestUtil;


/**
 * Test class for ShoppingBasket
 * @author blanasco
 *
 */
public class ShoppingBasketTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowIllegalArgument(){
		ShoppingBasket basket = new ShoppingBasket();
		basket.addProduct(null);
	}


	@Test
	public void success(){
		ShoppingBasket basket = new ShoppingBasket();
		basket.addProduct(TestUtil.BASIC_EXCEMPTED_PRODUCT);
		assertNotNull(basket);
	}

}
