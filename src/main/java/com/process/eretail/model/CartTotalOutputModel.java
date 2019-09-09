package com.process.eretail.model;

import java.util.List;

public class CartTotalOutputModel {
	
	private List<String> discountList;
	
	private Double cartCost;

	public CartTotalOutputModel(List<String> discountList, Double cartCost) {
		super();
		this.discountList = discountList;
		this.cartCost = cartCost;
	}
	public CartTotalOutputModel() {
		
	}

	public List<String> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<String> discountList) {
		this.discountList = discountList;
	}

	public Double getCartCost() {
		return cartCost;
	}

	public void setCartCost(Double cartCost) {
		this.cartCost = cartCost;
	}
	
    
}
