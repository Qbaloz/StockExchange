package com.stockexchange.model;

public class Stock {
	
	private String companyName;
	private double stockValue;
	
	public Stock(String companyName, double stockValue) {
		this.companyName = companyName;
		this.stockValue = stockValue;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}
	
	
	
}
