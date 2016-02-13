package com.stockexchange.account;

public enum AccountStocks {

	PKOBP("PKOBP",0),
	KGHM("KGHM",0),
	PGNIG("PGNIG",0),
	JSW("JSW",0),
	TPSA("TPSA",0);
	
	private final String companyName;
	private final double amountOfStocks;
	
	AccountStocks(String currency, double amountOfMoney){
		this.companyName = currency;
		this.amountOfStocks = amountOfMoney;
	}
	
	public String asString() {
		return companyName;
	}
	
	public double asDouble() {
		return amountOfStocks;
	}
	
}
