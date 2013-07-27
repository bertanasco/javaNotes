/**************************************************
 * FileName - Product.java
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

import com.nst.salestax.exception.ErrorCode;
import com.nst.salestax.utils.Validator;

/***
 * Domain class used to represent a product
 * @author blanasco (Bert L. Anasco)
 */
public class Product {

	public static final boolean IMPORTED = true;
	public static final boolean LOCAL = false;

	/**
	 *
	 * @param itemName
	 * @param type
	 * @param price
	 * @param quantity
	 * @param isImported
	 */
	public Product(String itemName, ProductTypes type, BigDecimal price, int quantity,boolean isImported){
		Validator.checkIfZero(price.doubleValue(), ErrorCode.PRICE_IS_ZERO);
		Validator.checkIfZero(quantity, ErrorCode.QUANTITY_IS_ZERO);
		this.itemName = itemName;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.imported = isImported;
	}


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductTypes getType() {
		return type;
	}

	public void setType(ProductTypes type) {
		this.type = type;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	private BigDecimal price;
	private BigDecimal tax = new BigDecimal("0");//default to zero
	private String itemName;
	private ProductTypes type;
	private boolean imported;
	private int quantity;

	/**
	 * Optional Builder for Product Class
	 */
	public static class Builder {

		private BigDecimal price;
		private int quantity;
		private String itemName;

		//set default parameters for optional
		private boolean imported = LOCAL;
		private BigDecimal tax =new BigDecimal(0);
		private ProductTypes type = ProductTypes.OTHERS;

		public Builder(String itemName, BigDecimal price, int quantity){
			this.itemName = itemName;
			this.price = price;
			this.quantity = quantity;
		}

		public Builder isImported(boolean imported){
			this.imported = imported;
			return this;
		}
		public Builder type(ProductTypes type){
			this.type = type;
			return this;
		}
		public Builder tax(BigDecimal tax){
			this.tax = tax;
			return this;
		}

		public Product build(){
			return new Product(this);
		}
	}

	private Product(Builder builder){
		this.itemName = builder.itemName;
		this.price = builder.price;
		this.tax = builder.tax;
		this.quantity = builder.quantity;
		this.type = builder.type;
		this.imported = builder.imported;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	@Override
	public String toString() {
		return  quantity + " " + itemName + " at " + price + (imported?" [IMPORTED]" : "");
	}

}
