package com.stockexchange.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stockexchange.model.Money;
import com.stockexchange.model.StockTo;

@Component
@Scope("prototype")
public class Account {

	private Map<String, Double> accountMoney = new HashMap<String, Double>();
	private Map<String, Double> stocks = new HashMap<String, Double>();
	private double startingMoneyInPLN = 10000;
	private double startingMoneyInEUR = 0;
	private double newAccountNumberOfStocks = 0;
	
	public Account(){
		accountMoney.put("PLN", startingMoneyInPLN);
		accountMoney.put("EUR", startingMoneyInEUR);
		initializeNewAccountStocks();
	}
	
	public Account(double PLN, double EUR){
		accountMoney.put("PLN", PLN);
		accountMoney.put("EUR", EUR);
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
	
	public void addStocks(StockTo stockTo){
		String companyName = stockTo.getStock().getCompanyName();
		stocks.put(companyName, stocks.get(companyName) + stockTo.getAmount());
	}
	
	public void removeStocks(StockTo stockTo){
		String companyName = stockTo.getStock().getCompanyName();
		stocks.put(companyName, stocks.get(companyName) - stockTo.getAmount());
	}
	
	public Map<String, Double> getStocks(){
		return stocks;
	}
	
}
