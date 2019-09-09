package com.process.eretail.model;

import java.util.List;

public class CartValueDiscountModel {

	private Double totalCartCost;
	
	private String department;
	
	private List<String> discountList;

	public CartValueDiscountModel() {
	  }

	public CartValueDiscountModel(Double totalCartCost, String department, List<String> discountList) {
		super();
		this.totalCartCost = totalCartCost;
		this.department = department;
		this.discountList = discountList;
	}

	public Double getTotalCartCost() {
		return totalCartCost;
	}

	public void setTotalCartCost(Double totalCartCost) {
		this.totalCartCost = totalCartCost;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<String> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<String> discountList) {
		this.discountList = discountList;
	}

	
	
}
