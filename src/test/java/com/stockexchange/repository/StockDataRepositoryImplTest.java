package com.stockexchange.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockexchange.StockexchangeApplication;
import com.stockexchange.model.Stock;
import com.stockexchange.repository.StockMarketDataRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class StockDataRepositoryImplTest {

	@Autowired
	private StockMarketDataRepository stockRepository;
	
	Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
	
	@Test
	public void testShouldGetAllDataFromFile() {
		// given
		int stockMapSize = 247;
		// when
		Map<Date, List<Stock>> stockMap = new LinkedHashMap<Date, List<Stock>>();
		stockMap = stockRepository.findAll();
		// then
		assertNotNull(stockMap);
        assertEquals(stockMapSize, stockMap.size());
	}
	
	@Test
	public void testShouldCheckFirstDay() throws ParseException {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP","37.35"));
		stocks.add(new Stock("KGHM","193.1"));
		stocks.add(new Stock("PGNIG","5.26"));
		stocks.add(new Stock("JSW","94.6"));
		stocks.add(new Stock("TPSA","12.16"));
		// when
		date = format.parse("20130102");
		List<Stock> stocksFromFile = stockRepository.getStocksFromDay(date);
		// then
		assertNotNull(stocksFromFile);
        assertEquals(stocks.get(0).getCompanyName(), stocksFromFile.get(0).getCompanyName());
        assertEquals(stocks.get(0).getStockValue(), stocksFromFile.get(0).getStockValue());
	}
	
	@Test
	public void testShouldCheckSelectedEntryFromMap() throws ParseException {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP","34.6"));
		stocks.add(new Stock("KGHM","121.9"));
		stocks.add(new Stock("PGNIG","5.82"));
		stocks.add(new Stock("JSW","69.9"));
		stocks.add(new Stock("TPSA","7.73"));
		// when
		date = format.parse("20130624");
		List<Stock> stocksFromFile = stockRepository.getStocksFromDay(date);
		// then
		assertNotNull(stocksFromFile);
        assertEquals(stocks.get(3).getCompanyName(), stocksFromFile.get(3).getCompanyName());
        assertEquals(stocks.get(3).getStockValue(), stocksFromFile.get(3).getStockValue());
	}

}
