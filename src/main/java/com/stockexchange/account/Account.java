package com.stockexchange.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.stockexchange.model.Money;
import com.stockexchange.model.StockTo;

@Component
public class Account {

	private Map<String, Double> accountMoney = new HashMap<String, Double>();
	private Map<String, Double> stocks = new HashMap<String, Double>();
	private double startingMoneyInPolskiZloty = 10000;
	private double startingMoneyInEuro = 0;
	private double newAccountNumberOfStocks = 0;
	
	public Account(){
		accountMoney.put("Polski Zloty", startingMoneyInPolskiZloty);
		accountMoney.put("Euro", startingMoneyInEuro);
		initializeNewAccountStocks();
	}
	
	public Account(double zlotyPolski, double Euro){
		accountMoney.put("Polski Zloty", zlotyPolski);
		accountMoney.put("Euro", Euro);
		initializeNewAccountStocks();
	}
	
	private void initializeNewAccountStocks(){
		stocks.put("PKOBP", newAccountNumberOfStocks);
		stocks.put("KGHM", newAccountNumberOfStocks);
		stocks.put("PGNIG", newAccountNumberOfStocks);
		stocks.put("JSW", newAccountNumberOfStocks);
		stocks.put("TPSA", newAccountNumberOfStocks);
	}
	
	public void depositMoney(Money money){
		String currency = money.getCurrency();
		accountMoney.put(currency, accountMoney.get(currency) + money.getAmount());
	}
	
	public void withdrawMoney(Money money){
		String currency = money.getCurrency();
		accountMoney.put(currency, accountMoney.get(money.getCurrency()) - money.getAmount());
	}
	
	public Map<String, Double> getBalance(){
		return accountMoney;
	}
	
	public Map<String, Double> getStockList(){
		return stocks;
	}
	
	public void addStocks(StockTo stockTo, double amount){
		String companyName = stockTo.getStock().getCompanyName();
		stocks.put(companyName, stocks.get(companyName) + amount);
	}
	
	public void removeStocks(StockTo stockTo, double amount){
		String companyName = stockTo.getStock().getCompanyName();
		stocks.put(companyName, stocks.get(companyName) - amount);
	}
	
}
