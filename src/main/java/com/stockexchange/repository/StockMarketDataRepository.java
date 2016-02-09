package com.stockexchange.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stockexchange.model.Stock;

public interface StockMarketDataRepository {

	Map<Date, List<Stock>> readDataFromFile();
	Map<Date, List<Stock>> findAll();
	List<Stock> getStocksFromDay(Date date);
	
}
