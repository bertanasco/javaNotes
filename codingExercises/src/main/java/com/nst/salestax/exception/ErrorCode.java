/**************************************************
 * FileName - ErrorCode.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.exception;
/**
 * Enumeration of the possible error codes for salestax
 * @author blanasco (Bert L. Anasco)
 */
public enum ErrorCode {

	PRICE_IS_ZERO("0000", "Product price can't be set to zero."),
	QUANTITY_IS_ZERO("0001", "Product quantity can't be set to zero."),
	PRODUCT_IS_NULL("0002", "product should not be null"),
	BASKET_IS_NULL("0003","Shopping Basket must not be null");

	private final String code;
	private final String description;

	private ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}