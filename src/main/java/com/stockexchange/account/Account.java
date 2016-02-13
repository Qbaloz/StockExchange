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
	
	public Account(){
		for (AccountCurrencies currencies : AccountCurrencies.values()) {
			accountMoney.put(currencies.asString(), currencies.asDouble());
		}
		for (AccountStocks companies : AccountStocks.values()) {
			stocks.put(companies.asString(), companies.asDouble());
		}
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
	
	public double getBalanceInGivenCurrency(String currency){
		return accountMoney.get(currency);
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
