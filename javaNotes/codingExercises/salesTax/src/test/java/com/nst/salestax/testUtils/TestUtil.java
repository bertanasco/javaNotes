/**************************************************
 * FileName - TestUtil.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.testUtils;

import java.math.BigDecimal;

import org.junit.Ignore;

import com.nst.salestax.model.Product;
import com.nst.salestax.model.ProductTypes;

/**
 * Utility class used for testing
 * @author blanasco (Bert L. Anasco)
 */
@Ignore
public  class TestUtil {


	public static final Product BASIC_EXCEMPTED_PRODUCT = new Product.Builder("box of chocolates",bd("10.00"), 1).isImported(Product.LOCAL).
			type(ProductTypes.BOOKS).build();
	public static final Product IMPORT_EXCEMPTED_PRODUCT = new Product.Builder("box of chocolates",bd("10.00"), 1).isImported(Product.IMPORTED).
			type(ProductTypes.BOOKS).build();
	public static final Product BASIC_PRODUCT = new Product.Builder("music CD",bd("16.49"), 1).isImported(Product.LOCAL).
			type(ProductTypes.OTHERS).build();
	public static final Product BASIC_IMPORTED_PRODUCT = new Product.Builder("bottle of perfume",bd("47.50"), 1).isImported(true).
			type(ProductTypes.OTHERS).build();

	public static  Product book = new Product.Builder("book",bd("12.49"), 1).isImported(Product.LOCAL).
			type(ProductTypes.BOOKS).build();
	public static  Product music_cd = new Product.Builder("music CD",bd("14.99"), 1).isImported(Product.LOCAL).
			type(ProductTypes.OTHERS).build();
	public static  Product chocolate_bar = new Product.Builder("chocolate bar",bd("0.85"), 1).isImported(Product.LOCAL).
			type(ProductTypes.FOOD).build();

	public static  Product chocolate_box = new Product.Builder("chocolate box",bd("10.00"), 1).isImported(Product.IMPORTED).
			type(ProductTypes.BOOKS).build();
	public static  Product bottle_perfume = new Product.Builder("bottle of perfume",bd("47.50"), 1).isImported(Product.IMPORTED).
			type(ProductTypes.OTHERS).build();


	public  static void init(){
		book = new Product.Builder("book",bd("12.49"), 1).isImported(Product.LOCAL).
				type(ProductTypes.BOOKS).build();
		music_cd = new Product.Builder("music CD",bd("14.99"), 1).isImported(Product.LOCAL).
				type(ProductTypes.OTHERS).build();
		chocolate_bar = new Product.Builder("chocolate bar",bd("0.85"), 1).isImported(Product.LOCAL).
				type(ProductTypes.FOOD).build();

		chocolate_box = new Product.Builder("chocolate box",bd("10.00"), 1).isImported(Product.IMPORTED).
				type(ProductTypes.BOOKS).build();
		bottle_perfume = new Product.Builder("bottle of perfume",bd("47.50"), 1).isImported(Product.IMPORTED).
				type(ProductTypes.OTHERS).build();
	}

	public static BigDecimal bd(String value){
		return new BigDecimal(value);
	}


}
