package com.stockexchange.model;

public class StockTo {

	private Stock stock;
	private double amount;
	
	public StockTo(Stock stock, double amount) {
		this.stock = stock;
		this.amount = amount;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
