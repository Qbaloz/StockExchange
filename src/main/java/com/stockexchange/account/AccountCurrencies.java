package com.stockexchange.account;

public enum AccountCurrencies {

	PLN("PLN",10000),
	EUR("EUR",0);
	
	private final String currency;
	private final double amountOfMoney;
	
	AccountCurrencies(String currency, double amountOfMoney){
		this.currency = currency;
		this.amountOfMoney = amountOfMoney;
	}
	
	public String asString() {
		return currency;
	}
	
	public double asDouble() {
		return amountOfMoney;
	}
	
}
