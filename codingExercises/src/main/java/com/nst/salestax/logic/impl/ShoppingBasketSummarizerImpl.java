/**************************************************
 * FileName - ShoppingBasketSummarizerImpl.java
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

import com.nst.salestax.exception.ErrorCode;
import com.nst.salestax.logic.Summarizer;
import com.nst.salestax.logic.TaxCalculator;
import com.nst.salestax.model.Product;
import com.nst.salestax.model.ShoppingBasket;
import com.nst.salestax.utils.Validator;

/**
 * Class use to update a shopping basket with the computed tax
 * @author blanasco (Bert L. Anasco)
 *
 */
public class ShoppingBasketSummarizerImpl implements Summarizer{

	private TaxCalculator taxCalculator = new TaxCalculatorImpl();

	public TaxCalculator getTaxCalculator() {
		return taxCalculator;
	}

	public void setTaxCalculator(TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

	/**
	 *
	 */
	public ShoppingBasket summarize(ShoppingBasket basket) {
		return updateBasketWithTax(basket);
	}

	/**
	 * updates each product in the basket with tax
	 * @param basket
	 * @return ShoppingBasket
	 *
	 */
	public ShoppingBasket updateBasketWithTax(ShoppingBasket basket){
		Validator.checkIfNull(basket, ErrorCode.BASKET_IS_NULL);
		BigDecimal totalPrice = new BigDecimal("0");
		BigDecimal totalTax =  new BigDecimal("0");
		//TODO refactor to a seperate method
		for(Product product: basket.getProducts()){
			product = updateProductWithTax( product);
			totalPrice = totalPrice.add(product.getPrice());
			totalTax = totalTax.add( product.getTax());
		}
		basket.setTotalPrice(totalPrice);
		basket.setTotalTax(totalTax);
		//System.out.println("total price : " + basket.getTotalPrice() + " total tax : " + basket.getTotalTax()  );
		return basket;
	}

	/**
	 * update the product with the tax
	 * @param product
	 * @return product
	 *    the product with updated tax and price
	 */
	public Product updateProductWithTax(Product product){
		Validator.checkIfNull(product, ErrorCode.PRODUCT_IS_NULL);
		BigDecimal tax = taxCalculator.computeRoundedUpTax(product);
		product.setTax(tax);
		product.setPrice(product.getPrice().add(tax));
		return product;
	}


}
