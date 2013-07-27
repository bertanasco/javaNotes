package com.nst.salestax.logic;

import com.nst.salestax.model.ShoppingBasket;

public interface Summarizer {
	public ShoppingBasket summarize(ShoppingBasket basket);
}
