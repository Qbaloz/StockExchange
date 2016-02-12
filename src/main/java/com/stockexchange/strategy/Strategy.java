package com.stockexchange.strategy;

import java.util.List;
import java.util.Map;

import com.stockexchange.model.Stock;
import com.stockexchange.model.StockTo;

public interface Strategy {

	List<StockTo> calculateWhatUserShouldBuy(List<Stock> stocks, Map<String, Double> userStocks, double availableMoney);
	List<StockTo> calculateWhatUserShouldSell(List<Stock> stocks, Map<String, Double> userStocks);
	
}
