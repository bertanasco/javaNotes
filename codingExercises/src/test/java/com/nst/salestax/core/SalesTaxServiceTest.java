/**************************************************
 * FileName - SalesTaxServiceTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.core;

import org.junit.Before;
import org.junit.Test;

import com.nst.salestax.model.ShoppingBasket;
import com.nst.salestax.testUtils.TestUtil;

/**
 * Test class for sales tax service
 * @author blanasco (Bert L. Anasco)
 */
public class SalesTaxServiceTest {
	SalesTaxService instance = SalesTaxService.getInstance();
	public static ShoppingBasket input1 = new ShoppingBasket();
	public static ShoppingBasket input2 = new ShoppingBasket();
	@Before
	public void setUp(){
		TestUtil.init();
	}

	@Test(expected=IllegalArgumentException.class)
	public void mustThrowIllegalArgumentException(){
		instance.generateReciept(null);
	}

	@Test
	public void generateRecieptInCommandLine1(){
		input1.addProduct(TestUtil.book);
		input1.addProduct(TestUtil.music_cd);
		input1.addProduct(TestUtil.chocolate_bar);
		instance.generateReciept(input1);
	}

	@Test
	public void generateRecieptInCommandLine2(){
		input2.addProduct(TestUtil.chocolate_box);
		input2.addProduct(TestUtil.bottle_perfume);
		instance.generateReciept(input2);
	}

}
