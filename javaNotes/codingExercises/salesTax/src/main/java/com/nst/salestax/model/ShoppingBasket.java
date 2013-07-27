/**************************************************
 * FileName - ShoppingBasket.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nst.salestax.exception.ErrorCode;
import com.nst.salestax.utils.Validator;

/**
 * Domain class for shopping basket
 * @author blanasco (Bert L. Anasco)
 */
public class ShoppingBasket {
	private List<Product> productList = new ArrayList<Product>();
	private BigDecimal totalPrice;
	private BigDecimal totalTax;

	public void addProduct(Product product){
		Validator.checkIfNull(product, ErrorCode.PRODUCT_IS_NULL);
		productList.add(product);
	}

	public List<Product> getProducts( ){
		return productList;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	@Override
	public String toString() {
		String value = "";
		for(Product p : productList){
			value+= p.toString() + "\n";
		}
		return  value;
	}

}
