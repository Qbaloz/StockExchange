package com.stockexchange.service;

import java.util.Date;
import java.util.List;

import com.stockexchange.model.Stock;

public interface StockMarketService {

	List<Stock> getStocksFromDay(Date date);
	
}
