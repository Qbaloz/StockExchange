package com.stockexchange.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.stockexchange.model.Money;
import com.stockexchange.model.Stock;

@Component
public class Account {

	private Map<String, Double> accountMoney = new HashMap<String, Double>();
	private Map<String, Double> stocks = new HashMap<String, Double>();
	private double startingMoneyInPolskiZloty = 10000;
	private double startingMoneyInEuro = 0;
	
	public Account(){
		accountMoney.put("Polski Zloty", startingMoneyInPolskiZloty);
		accountMoney.put("Euro", startingMoneyInEuro);
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
	
	public void updateStockList(List<Stock> stockList, double amount){
		String companyName;
		for(int i = 0; i < stockList.size(); i++){
			companyName = stockList.get(i).getCompanyName();
			stocks.put(companyName, stocks.get(companyName) + amount);
		}
	}
	
}
