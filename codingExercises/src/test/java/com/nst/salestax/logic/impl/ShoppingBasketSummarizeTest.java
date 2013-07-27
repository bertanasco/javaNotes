/**************************************************
 * FileName - ShoppingBasketSummarizeTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.logic.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nst.salestax.logic.Summarizer;
import com.nst.salestax.model.ShoppingBasket;
import com.nst.salestax.testUtils.TestUtil;
import static com.nst.salestax.testUtils.TestUtil.*;
/**
 * Test class for summarize
 * @author blanasco (Bert L. Anasco)
 */
public class ShoppingBasketSummarizeTest {
	public static Summarizer summarizer = new ShoppingBasketSummarizerImpl();
	public static ShoppingBasket input1 = new ShoppingBasket();
	public static ShoppingBasket input2 = new ShoppingBasket();
	@Before
	public void setUp(){
		TestUtil.init();
	}

	@Test
	public void updateBasketWithLocal(){
		input1.addProduct(TestUtil.book);
		input1.addProduct(TestUtil.music_cd);
		input1.addProduct(TestUtil.chocolate_bar);

		summarizer.summarize(input1);
		//System.out.println("test price:" + input1.getTotalPrice());
		//System.out.println("test tax :" + input1.getTotalTax());
		assertTrue(input1.getTotalPrice().equals(bd("29.83")));
		assertTrue(input1.getTotalTax().equals(bd("1.50")));
	}

	@Test
	public void updateBasketWithImported(){

		input2.addProduct(TestUtil.chocolate_box);
		input2.addProduct(TestUtil.bottle_perfume);

		summarizer.summarize(input2);
		//System.out.println("test price:" + input2.getTotalPrice());
		//System.out.println("test tax :" + input2.getTotalTax());
		assertTrue(input2.getTotalPrice().equals(bd("65.15")));
		assertTrue(input2.getTotalTax().equals(bd("7.65")));
	}

}
