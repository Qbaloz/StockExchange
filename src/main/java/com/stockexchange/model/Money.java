package com.stockexchange.model;

public class Money {

	private String currency;
	private double amount;
	
	public Money(String currency, double amount) {
		this.currency = currency;
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
		
	}
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
