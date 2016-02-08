package com.stockexchange.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockexchange.StockexchangeApplication;
import com.stockexchange.model.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class StockRepositoryTest {

	@Autowired
	private StockRepository stockRepository;
	
	@Test
	public void testShouldGetAllDataFromFile() {
		// given
		int stockMapSize = 247;
		// when
		Map<String, List<Stock>> stockMap = new LinkedHashMap<String, List<Stock>>();
		stockMap = stockRepository.readDataFromFile();
		// then
		assertNotNull(stockMap);
        assertEquals(stockMapSize, stockMap.size());
	}
	
	@Test
	public void testShouldCheckFirstEntryFromMap() {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		List<Stock> stocksFromFile = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP","37.35"));
		stocks.add(new Stock("KGHM","193.1"));
		stocks.add(new Stock("PGNIG","5.26"));
		stocks.add(new Stock("JSW","94.6"));
		stocks.add(new Stock("TPSA","12.16"));
		// when
		Map<String, List<Stock>> stockMap = new LinkedHashMap<String, List<Stock>>();
		stockMap = stockRepository.readDataFromFile();
		stocksFromFile = stockMap.get("20130102");
		// then
		assertNotNull(stocksFromFile);
        assertEquals(stocks.get(0).getCompanyName(), stocksFromFile.get(0).getCompanyName());
        assertEquals(stocks.get(0).getStockValue(), stocksFromFile.get(0).getStockValue());
	}
	
	@Test
	public void testShouldCheckSelectedEntryFromMap() {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		List<Stock> stocksFromFile = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP","34.6"));
		stocks.add(new Stock("KGHM","121.9"));
		stocks.add(new Stock("PGNIG","5.82"));
		stocks.add(new Stock("JSW","69.9"));
		stocks.add(new Stock("TPSA","7.73"));
		// when
		Map<String, List<Stock>> stockMap = new LinkedHashMap<String, List<Stock>>();
		stockMap = stockRepository.readDataFromFile();
		stocksFromFile = stockMap.get("20130624");
		// then
		assertNotNull(stocksFromFile);
        assertEquals(stocks.get(3).getCompanyName(), stocksFromFile.get(3).getCompanyName());
        assertEquals(stocks.get(3).getStockValue(), stocksFromFile.get(3).getStockValue());
	}

}
