package com.stockexchange.brokersoffice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stockexchange.model.Stock;
import com.stockexchange.model.StockTo;
import com.stockexchange.service.StockMarketService;

@Component
@Scope("singleton")
public class BrokersOffice {

	@Autowired
	private StockMarketService stockMarketService;
	@Autowired
	private BrokersOfficeManager brokersOfficeManager;
	
	public List<Stock> getStocks(Date date){
		return stockMarketService.getStocksFromDay(date);
	}
	
	public void buyStocks(String currency, StockTo stockTo){
		brokersOfficeManager.performBuyOperation(currency, stockTo);
	}
	
	public void sellStocks(String currency, StockTo stockTo){
		brokersOfficeManager.performSellOperation(currency, stockTo);
	}
	
	public Map<String, Double> checkUserBalance(){
		return brokersOfficeManager.checkUserBalance();
	}
	
	public Map<String, Double> checkUserStocks(){
		return brokersOfficeManager.checkUserStocks();
	}
	
	public double checkBalanceInGivenCurrency(String currency){
		return brokersOfficeManager.checkBalanceInGivenCurrency(currency);
	}
	
	
}
