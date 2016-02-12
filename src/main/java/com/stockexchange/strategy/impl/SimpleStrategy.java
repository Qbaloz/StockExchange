package com.stockexchange.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.stockexchange.model.Stock;
import com.stockexchange.model.StockTo;
import com.stockexchange.strategy.Strategy;

//Buy cheapest stocks for 90% of money and sell when price is 10% higher

@Component
public class SimpleStrategy implements Strategy{

	private Stock stock;
	private double spentMoney;
	
	@Override
	public List<StockTo> calculateWhatUserShouldBuy(List<Stock> stocks, Map<String, Double> userStocks, double availableMoney) {
		List<StockTo> stocksToBuy = new ArrayList<StockTo>();
		
		stock = findCheapestStocks(stocks);
		double amount = Math.floor((availableMoney/stock.getStockValue()) * 0.9);
		spentMoney = amount * stock.getStockValue();
		stocksToBuy.add(new StockTo(stock, amount));
		
		return stocksToBuy;
	}

	@Override
	public List<StockTo> calculateWhatUserShouldSell(List<Stock> stocks, Map<String, Double> userStocks) {
		List<StockTo> stocksToSell = new ArrayList<StockTo>();
		String companyName;
		double stockAmount;
		double actualStockValue = 0;
		if(userStocks.isEmpty()){
			return stocksToSell;
		}else{
			companyName = userStocks.entrySet().iterator().next().getKey();
			stockAmount = userStocks.get(companyName);
			
			for(int i = 0; i < stocks.size(); i++){
				if(stocks.get(i).getCompanyName() == companyName){
					actualStockValue = stocks.get(i).getStockValue();
				}
			}
				
			if(stockAmount * actualStockValue * 1.1 > spentMoney){
				stocksToSell.add(new StockTo(new Stock(companyName, actualStockValue), stockAmount));
			}
				
		}
		return stocksToSell;
	}
	
	public Stock findCheapestStocks(List<Stock> stocks) {
		double stockValue = 0;
		double cheapestStockValue = Integer.MAX_VALUE;
		int cheapestStockIndex = 0;
		for(int i = 0; i < stocks.size(); i++){
			stockValue = stocks.get(i).getStockValue();
			if(stockValue < cheapestStockValue){
				cheapestStockValue = stockValue;
				cheapestStockIndex = i;
			}
		}
		return stocks.get(cheapestStockIndex);
	}
	
}
