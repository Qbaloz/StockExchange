package com.stockexchange.brokersoffice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockexchange.model.Stock;
import com.stockexchange.service.StockMarketService;

@Component
public class BrokersOffice {

	@Autowired
	private StockMarketService stockMarketService;
	
	public List<Stock> getStocks(Date date){
		return stockMarketService.getStocksFromDay(date);
	}
}
