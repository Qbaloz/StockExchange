package com.stockexchange.model;

public class Stock {
	
	private String companyName;
	private String stockValue;
	
	public Stock(String companyName, String stockValue) {
		this.companyName = companyName;
		this.stockValue = stockValue;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStockValue() {
		return stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}
	
	
	
}
