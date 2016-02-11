package com.stockexchange.brokersoffice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stockexchange.account.Account;
import com.stockexchange.model.Money;
import com.stockexchange.model.StockTo;

@Component
@Scope("singleton")
public class BrokersOfficeManager {

	@Autowired
	private Account account;
	
	public void performBuyOperation(String currency, StockTo stockTo){
		double stocksWorth = stockTo.getStock().getStockValue() * stockTo.getAmount();
		account.withdrawMoney(new Money(currency, stocksWorth));
		account.addStocks(stockTo);
	}
	
	public void performSellOperation(String currency, StockTo stockTo){
		double stocksWorth = stockTo.getStock().getStockValue() * stockTo.getAmount();
		account.depositMoney(new Money(currency, stocksWorth));
		account.removeStocks(stockTo);
	}
	
	public Map<String, Double> checkUserBalance(){
		return account.getBalance();
	}
	
	public Map<String, Double> checkUserStocks(){
		return account.getStocks();
	}
	
	public double checkBalanceInGivenCurrency(String currency){
		return account.getBalanceInGivenCurrency(currency);
	}
}
