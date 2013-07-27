/**************************************************
 * FileName - TaxCalculator.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.logic;

import java.math.BigDecimal;

import com.nst.salestax.model.Product;

/**
 * interface for tax calculator
 * @author blanasco (Bert L. Anasco)
 */
public interface TaxCalculator {
	public BigDecimal computeTax(Product product);
	public BigDecimal computeRoundedUpTax(Product product);

}
