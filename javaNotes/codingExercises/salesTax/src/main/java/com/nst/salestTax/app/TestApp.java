/**************************************************
 * FileName - TestApp.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestTax.app;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nst.salestax.core.SalesTaxService;
import com.nst.salestax.model.Product;
import com.nst.salestax.model.ProductTypes;
import com.nst.salestax.model.ShoppingBasket;

/**
 * Sample test application using the salextax api
 * @author blanasco (Bert L. Anasco)
 */
public class TestApp {

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
	public static ShoppingBasket input1 = new ShoppingBasket();
	public static ShoppingBasket input2 = new ShoppingBasket();


	public static void main(String [] args){
		init();
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SalesTaxService instance =(SalesTaxService)ctx.getBean("salesTaxService");
		System.out.println("\nBasket Original values");
		System.out.println(input1.toString());
		System.out.println(input2.toString());
		System.out.println("\nGenerated Reciepts");
		instance.generateReciept(input1);
		instance.generateReciept(input2);
	}

	public static BigDecimal bd(String value){
		return new BigDecimal(value);
	}

	private static void init(){
		input1.addProduct(book);
		input1.addProduct(music_cd);
		input1.addProduct(chocolate_bar);

		input2.addProduct(chocolate_box);
		input2.addProduct(bottle_perfume);
	}


}
