/**************************************************
 * FileName - SalesTaxService.java
 *
 * (c) NST. All rights reserved.
 *
 * $Author: bertanasco $
 * $Revision: #1 $
 * $Change: 1 $
 * $Date: 2013/07/24 $
 **************************************************/
package com.nst.salestax.core;

import com.nst.salestax.exception.ErrorCode;
import com.nst.salestax.logic.ReceiptPrinter;
import com.nst.salestax.logic.Summarizer;
import com.nst.salestax.logic.impl.CommandLineReceiptPrinter;
import com.nst.salestax.logic.impl.ShoppingBasketSummarizerImpl;
import com.nst.salestax.model.ShoppingBasket;
import com.nst.salestax.utils.Validator;

/**
 * Service class for the Sales Tax coding exercise
 * @author blanasco (Bert L. Anasco)
 */
public class SalesTaxService {
	private static SalesTaxService instance;
	private Summarizer summarizer ;
	private ReceiptPrinter receiptPrinter;

	/**
	 *
	 * @return
	 *	the instance of SalesTaxService
	 */
	public static synchronized SalesTaxService getInstance(){
		if(instance == null){
			instance = new SalesTaxService();
		}
		return instance;
	}

	private SalesTaxService( ){
		//load defaults if not set
		if(summarizer == null){
			summarizer = new ShoppingBasketSummarizerImpl();
		}
		if(receiptPrinter == null) {
			receiptPrinter = new CommandLineReceiptPrinter();
		}

	}

	/**
	 * @param basket
	 */
	public  void generateReciept(ShoppingBasket basket){
		Validator.checkIfNull(basket, ErrorCode.BASKET_IS_NULL);
		basket = summarizer.summarize(basket);
		receiptPrinter.print(basket);
	}

	public Summarizer getSummarizer() {
		return summarizer;
	}
	public void setSummarizer(Summarizer summarizer) {
		this.summarizer = summarizer;
	}


	public ReceiptPrinter getReceiptPrinter() {
		return receiptPrinter;
	}
	public void setReceiptPrinter(ReceiptPrinter receiptPrinter) {
		this.receiptPrinter = receiptPrinter;
	}
}
