/**************************************************
 * FileName - CommandLineReceiptPrinter.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.logic.impl;

import com.nst.salestax.exception.ErrorCode;
import com.nst.salestax.logic.ReceiptPrinter;
import com.nst.salestax.model.Product;
import com.nst.salestax.model.ShoppingBasket;
import com.nst.salestax.utils.Validator;

/**
 * Test class for the TaxCalculatorImpl}.
 * @author blanasco (Bert L. Anasco)
 */
public class CommandLineReceiptPrinter implements ReceiptPrinter{

	/**
	 *
	 */
	public void print(ShoppingBasket basket) {
		Validator.checkIfNull(basket, ErrorCode.BASKET_IS_NULL);
		System.out.println("Receipt Details");
		for(Product p : basket.getProducts()){
			System.out.println(p.getQuantity() + " " + p.getItemName() + " : " + p.getPrice());
		}
		System.out.println("Sales Taxes : " +  basket.getTotalTax() + " Total :" +
				basket.getTotalPrice() + "\n");
	}

}
