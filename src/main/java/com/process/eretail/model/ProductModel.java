package com.process.eretail.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductModel {

	@Id
	@Column(name="PRODUCT_ID")
	private int id;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="DEPARTMENT")
	private String department;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="LIFECYCLE_START_DATE")
	private Date lifecycleStartDate;
	
	@Column(name="LIFECYCLE_END_DATE")
	private Date lifecycleEndDate;
	
	private boolean active;
	
	private Double cost;
	
	private String currency;

	
	public ProductModel() {
		// TODO Auto-generated constructor stub
	}


	public ProductModel(int id, String productName, String department, String company, Date lifecycleStartDate,
			Date lifecycleEndDate, boolean active, Double cost, String currency) {
		super();
		this.id = id;
		this.productName = productName;
		this.department = department;
		this.company = company;
		this.lifecycleStartDate = lifecycleStartDate;
		this.lifecycleEndDate = lifecycleEndDate;
		this.active = active;
		this.cost = cost;
		this.currency = currency;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public Date getLifecycleStartDate() {
		return lifecycleStartDate;
	}


	public void setLifecycleStartDate(Date lifecycleStartDate) {
		this.lifecycleStartDate = lifecycleStartDate;
	}


	public Date getLifecycleEndDate() {
		return lifecycleEndDate;
	}


	public void setLifecycleEndDate(Date lifecycleEndDate) {
		this.lifecycleEndDate = lifecycleEndDate;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
	
	
}
