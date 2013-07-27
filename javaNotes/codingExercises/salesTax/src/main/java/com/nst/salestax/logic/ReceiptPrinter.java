/**************************************************
 * FileName - ReceiptPrinter.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.logic;

import com.nst.salestax.model.ShoppingBasket;
/**
 * interface class for printing the receipt
 * @author blanasco
 *
 */
public interface ReceiptPrinter {
	public void print(ShoppingBasket basket);
}
