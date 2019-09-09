package com.process.eretail.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductModel {

	@Id
	@GeneratedValue
	private int id;
	
	private String productName;
	
	private String department;
	
	private String company;
	
	private Date lifecycleStartDate;
	
	private Date lifecycleEndDate;
	
	private boolean active;

	public ProductModel(int id, String productName, String department, String company, Date lifecycleStartDate,
			Date lifecycleEndDate, boolean active) {
		super();
		this.id = id;
		this.productName = productName;
		this.department = department;
		this.company = company;
		this.lifecycleStartDate = lifecycleStartDate;
		this.lifecycleEndDate = lifecycleEndDate;
		this.active = active;
	}

	public ProductModel() {
		// TODO Auto-generated constructor stub
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

	
	
}
