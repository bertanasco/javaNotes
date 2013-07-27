/**************************************************
 * FileName - TaxCalculatorImpl.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.logic.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.nst.salestax.constants.TaxRates;
import com.nst.salestax.logic.TaxCalculator;
import com.nst.salestax.model.Product;
import com.nst.salestax.model.ProductTypes;
import static com.nst.salestax.utils.MathUtil.*;

/**
 * Default implementation of {@link TaxCalculator}.
 * @author blanasco (Bert L. Anasco)
 */
public class TaxCalculatorImpl implements TaxCalculator {

	private static final String DEFAULT_INCREMENT = "0.05";
	private static final int DEFAULT_SCALE = 2;
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.UP;
	private static final BigDecimal DIVISOR = new BigDecimal("100");

	private BigDecimal increment;
	private RoundingMode roundingMode;
	private int scale;

	/**
	 * Default constructor, sets the default increment and scale
	 */
	public TaxCalculatorImpl(){
		this(DEFAULT_INCREMENT,DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
	}

	public TaxCalculatorImpl(String increment){
		this(increment,DEFAULT_SCALE ,DEFAULT_ROUNDING_MODE);
	}


	public TaxCalculatorImpl(String increment, int scale ){
		this(increment,scale ,DEFAULT_ROUNDING_MODE);
	}

	public TaxCalculatorImpl(String increment, int scale, RoundingMode mode){
		this.setIncrement(new BigDecimal(increment));
		this.setScale(scale);
		this.roundingMode = mode;
	}


	/**
	 * Compute tax without rounding up the result
	 * @return double
	 *   the tax
	 */
	public BigDecimal computeTax(final Product product){
		BigDecimal price = product.getPrice();
		BigDecimal quantity = new BigDecimal(product.getQuantity());
		BigDecimal total_price = price.multiply(quantity);
		BigDecimal rate = determineRate(product);
		BigDecimal tax = total_price.multiply(rate).setScale(scale);
		tax = tax.divide(DIVISOR);
		return tax;
	}

	/**
	 * Rounds up to the nearest increment
	 * increment is 0.05 by default
	 * @param product
	 *   the product
	 * @return double
	 *   the tax rounded up to the nearest increment
	 */
	public BigDecimal computeRoundedUpTax(final Product product){
		return round(computeTax(product), increment, roundingMode)
				.setScale(scale) ;
	}

	/**
	 * Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
	 * products that are exempt. Import duty is an additional scales tax applicable on all imported
	 * goods at a rate of 5%, with no exemptions.
	 * @param product
	 * @return double
	 *   the tax rate
	 */
	private BigDecimal determineRate(final Product product){
		double rate = 0;
		if(product.getType() == ProductTypes.OTHERS){
			rate = rate + TaxRates.BASIC_OTHERS;
		}
		if (product.isImported() == true){
			rate = rate + TaxRates.IMPORTED_ADDITIONAL;
		}
		BigDecimal bdRate = new BigDecimal(rate);
		bdRate.setScale(scale);
		return bdRate;
	}

	public BigDecimal getIncrement() {
		return increment;
	}

	public void setIncrement(BigDecimal increment) {
		this.increment = increment;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}


}
