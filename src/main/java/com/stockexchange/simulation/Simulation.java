package com.stockexchange.simulation;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockexchange.brokersoffice.BrokersOffice;
import com.stockexchange.model.Stock;
import com.stockexchange.model.StockTo;
import com.stockexchange.strategy.Strategy;

@Component
public class Simulation {

	@Autowired
	private BeanFactory beanFactory;
	
	@Autowired
	private BrokersOffice brokersOffice;
	
	@Autowired
	private SimulationConfig simulationDate;

	private double balance;
	private final String currency = "PLN";
	private List<Stock> stocks;
	private List<StockTo> stocksToBuy;
	private List<StockTo> stocksToSell;
	private Map<String, Double> userStocks;
	Date currentDay = new Date();


	public void runSimulation(String strategyName){
		Strategy strategy = beanFactory.getBean(strategyName, Strategy.class);
		
		currentDay = simulationDate.getActualDay();
		
		while(simulationDate.isNextDay()){
			balance = brokersOffice.checkBalanceInGivenCurrency(currency);
			stocks = brokersOffice.getStocks(currentDay);
			userStocks = brokersOffice.checkUserStocks();
			
			if(!(stocks == null)){
				stocksToBuy = strategy.calculateWhatUserShouldBuy(stocks, userStocks, balance);
				stocksToSell = strategy.calculateWhatUserShouldSell(stocks, userStocks);
				
				if(!(stocksToBuy == null)){
					for(int i = 0; i < stocksToBuy.size(); i++){
						brokersOffice.buyStocks(currency, stocksToBuy.get(i));
					}
				}
				
				if(!(stocksToSell == null)){
					for(int i = 0; i < stocksToSell.size(); i++){
						brokersOffice.sellStocks(currency, stocksToSell.get(i));
					}
				}
			}
			currentDay = simulationDate.getNextDay(currentDay);
		}
		
	}
	
	public void printBalance(){
		System.out.println("Balance:" + balance);
	}
	
	public void printUserStocks(){
		userStocks.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).forEach(System.out::println);
	}
	
	public void printAccountValue(){
		double stockValue = 0;
		double amountOfStocks = 0;
		String companyName = null;
		
		for (String key : userStocks.keySet()) {
			if(userStocks.get(key) > 0){
				amountOfStocks = userStocks.get(key);
				companyName = key;
			}
		}
		
		for(int i = 0; i < stocks.size(); i ++){
			if(stocks.get(i).getCompanyName().equals(companyName)){
				stockValue = stocks.get(i).getStockValue();
			}
		}

		double accountValue = balance + (stockValue*amountOfStocks);
		System.out.println("Account Value:" + accountValue);
	}
	
}
