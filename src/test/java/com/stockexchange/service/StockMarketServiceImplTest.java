package com.stockexchange.service;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockexchange.StockexchangeApplication;
import com.stockexchange.model.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class StockMarketServiceImplTest {

	@Autowired
	private StockMarketService stockMarketService;
	
	Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);

	@Test
	public void shouldReturnStocksListFromSelectedDay() throws ParseException {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP","34.6"));
		stocks.add(new Stock("KGHM","121.9"));
		stocks.add(new Stock("PGNIG","5.82"));
		stocks.add(new Stock("JSW","69.9"));
		stocks.add(new Stock("TPSA","7.73"));
		// when
		date = format.parse("20130624");
		List<Stock> stocksFromRepository = stockMarketService.getStocksFromDay(date);
		// then
		assertNotNull(stocksFromRepository);
        assertEquals(stocks.get(3).getCompanyName(), stocksFromRepository.get(3).getCompanyName());
        assertEquals(stocks.get(3).getStockValue(), stocksFromRepository.get(3).getStockValue());
	}

}
