package com.stockexchange.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockexchange.model.Stock;
import com.stockexchange.repository.StockMarketDataRepository;
import com.stockexchange.service.StockMarketService;

@Service
public class StockMarketServiceImpl implements StockMarketService{

	@Autowired
	private StockMarketDataRepository stockMarketDataRepository;
	
	@Override
	public List<Stock> getStocksFromDay(Date date) {
		return stockMarketDataRepository.getStocksFromDay(date);
	}

}
