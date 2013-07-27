/**************************************************
 * FileName - ProductTest.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/23 $
 **************************************************/
package com.nst.salestax.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;


import org.junit.Test;

import com.nst.salestax.model.Product;
import com.nst.salestax.model.ProductTypes;

/**
 * Test class for Product
 * @author blanasco (Bert L. Anasco)
 *
 */
public class ProductTest {

	private static BigDecimal bd(String value){
		return new BigDecimal(value);
	}


	@Test(expected=IllegalArgumentException.class)
	public void zeroPrice(){
		new Product("Book", ProductTypes.BOOKS, bd("0.00"),1, Product.IMPORTED);
	}

	@Test(expected=IllegalArgumentException.class)
	public void zeroQuantity(){
		new Product("Book", ProductTypes.BOOKS, bd("22.2299"),0, Product.IMPORTED);
	}

	@Test()
	public void success(){
		Product product = new Product("Book", ProductTypes.BOOKS, bd("22.2299"),1, Product.IMPORTED);
		assertTrue(product.getPrice().doubleValue() == 22.2299);
		assertTrue(product.getType() == ProductTypes.BOOKS);
		assertTrue(product.getQuantity() == 1);
		assertTrue(product.isImported() == true);
		assertTrue(product.getTax().doubleValue() == 0 );

	}

	@Test()
	public void builderSuccess(){
		Product product = new Product.Builder("Book",bd("123.999"), 1).isImported(false).
				type(ProductTypes.BOOKS).build();
		assertNotNull(product);
		assertTrue(product.getPrice().doubleValue() == 123.999);
		assertTrue(product.getQuantity() == 1);
		assertTrue(product.isImported() == false);
		assertTrue(product.getTax().doubleValue() == 0 );
	}

}
