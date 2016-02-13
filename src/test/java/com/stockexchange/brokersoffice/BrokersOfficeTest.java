package com.stockexchange.brokersoffice;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.stockexchange.model.StockTo;
import com.stockexchange.service.StockMarketService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class BrokersOfficeTest {

	@Autowired
	private StockMarketService stockMarketService;
	
	@Autowired
	private BrokersOffice brokersOffice;

	Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
	
	@Test
	public void testShouldGetStockListFromOneDay() throws ParseException {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP",34.6));
		stocks.add(new Stock("KGHM",121.9));
		stocks.add(new Stock("PGNIG",5.82));
		stocks.add(new Stock("JSW",69.9));
		stocks.add(new Stock("TPSA",7.73));
		// when
		date = format.parse("20130624");
		List<Stock> stocksFromFile = stockMarketService.getStocksFromDay(date);
		// then
		assertNotNull(stocksFromFile);
        assertEquals(stocks.get(3).getCompanyName(), stocksFromFile.get(3).getCompanyName());
        assertEquals(stocks.get(3).getStockValue(), stocksFromFile.get(3).getStockValue(),0);
	}
	
	@Test
	public void testShouldCheckAccountBalanceInPLN() {
		// given
		Map<String, Double> expectedBalance = new HashMap<String, Double>();
		expectedBalance.put("PLN", (double) 10000);
		expectedBalance.put("EUR", (double) 0);
		double expectedAmount = 10000;
		// when
		Map<String, Double> balance = brokersOffice.checkUserBalance();
		double amount = balance.get("PLN");
		// then
		assertNotNull(balance);
		assertEquals(expectedBalance.get("PLN"), balance.get("PLN"));
		assertEquals(amount, expectedAmount, 0);
	}
	
	@Test
	public void testShouldCheckAccountBalanceInEUR() {
		// given
		Map<String, Double> expectedBalance = new HashMap<String, Double>();
		expectedBalance.put("PLN", (double) 10000);
		expectedBalance.put("EUR", (double) 0);
		double expectedAmount = 0;
		// when
		Map<String, Double> balance = brokersOffice.checkUserBalance();
		double amount = balance.get("EUR");
		// then
		assertNotNull(balance);
		assertEquals(expectedBalance.get("EUR"), balance.get("EUR"));
		assertEquals(amount, expectedAmount, 0);
	}
	
	@Test
	public void testShouldBuyStocksUsingPLNCurrency(){
		// given
		StockTo stockTo = new StockTo(new Stock("PKOBP",10),50);
		double expectedBalance = 9475;
		double expectedStocksAmount = 50;
		String currencyToUse = "PLN";
		// when
		brokersOffice.buyStocks(currencyToUse, stockTo);
		Map<String, Double> userBalance = brokersOffice.checkUserBalance();
		Map<String, Double> userStocks = brokersOffice.checkUserStocks();
		//then
		assertEquals(expectedBalance,userBalance.get(currencyToUse), 0);
		assertEquals(expectedStocksAmount,userStocks.get("PKOBP"), 0);
	}
	
	@Test
	public void testShouldSellStocksUsingPLNCurrency(){
		// given
		StockTo stockTo = new StockTo(new Stock("PKOBP",10),50);
		double expectedBalance = 9950;
		double expectedStocksAmount = 0;
		String currencyToUse = "PLN";
		// when
		brokersOffice.sellStocks(currencyToUse, stockTo);
		Map<String, Double> userBalance = brokersOffice.checkUserBalance();
		Map<String, Double> userStocks = brokersOffice.checkUserStocks();
		//then
		assertEquals(expectedBalance,userBalance.get(currencyToUse), 0);
		assertEquals(expectedStocksAmount,userStocks.get("PKOBP"), 0);
	}

}
