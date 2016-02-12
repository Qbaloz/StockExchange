package com.stockexchange.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockexchange.StockexchangeApplication;
import com.stockexchange.model.Stock;
import com.stockexchange.model.StockTo;
import com.stockexchange.strategy.impl.SimpleStrategy;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class SimpleStrategyTest {

	@Autowired
	private SimpleStrategy simpleStrategy;

	@Test
	public void testShouldFindCheapestStockFromGivenStocksList() {
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP",10));
		stocks.add(new Stock("JSW",40));
		stocks.add(new Stock("KGHM",70));
		// when
		Stock stock = simpleStrategy.findCheapestStocks(stocks);
		// then
		assertNotNull(stock);
        assertEquals(stocks.get(0).getCompanyName(), stock.getCompanyName());
        assertEquals(stocks.get(0).getStockValue(), stock.getStockValue(),0);
	}
	
	@Test
	public void testShouldCalculateStocksToBuy(){
		// given
		double availableMoney = 9874;
		List<Stock> stocks = new ArrayList<Stock>();
		Map<String, Double> userStocks = new HashMap<String, Double>();
		stocks.add(new Stock("PKOBP",10.4));
		stocks.add(new Stock("JSW",40));
		stocks.add(new Stock("KGHM",70));
		// when
		List<StockTo> stocksToBuy = simpleStrategy.calculateWhatUserShouldBuy(stocks, userStocks, availableMoney);
		// then
		assertNotNull(stocksToBuy);
        assertEquals(stocks.get(0).getCompanyName(), stocksToBuy.get(0).getStock().getCompanyName());
        assertEquals(stocks.get(0).getStockValue(), stocksToBuy.get(0).getStock().getStockValue(),0);
        assertEquals(854, stocksToBuy.get(0).getAmount(),0);
	}
	
	@Test
	public void testShouldCalculateStocksToSell(){
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP",14.4));
		stocks.add(new Stock("JSW",40));
		stocks.add(new Stock("KGHM",70));
		
		Map<String, Double> userStocks = new HashMap<String, Double>();
		userStocks.put("PKOBP", (double) 854);
		// when
		List<StockTo> stocksToSell = simpleStrategy.calculateWhatUserShouldSell(stocks, userStocks);
		// then
		assertNotNull(stocksToSell);
        assertEquals(stocks.get(0).getCompanyName(), stocksToSell.get(0).getStock().getCompanyName());
        assertEquals(stocks.get(0).getStockValue(), stocksToSell.get(0).getStock().getStockValue(),0);
        assertEquals(854, stocksToSell.get(0).getAmount(),0);
	}
	
	@Test
	public void testShouldCalculateStocksToSellAndReturnNoStocksToSell(){
		// given
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("PKOBP",5.4));
		stocks.add(new Stock("JSW",40));
		stocks.add(new Stock("KGHM",70));
		
		Map<String, Double> userStocks = new HashMap<String, Double>();
		userStocks.put("PKOBP", (double) 854);
		// when
		List<StockTo> stocksToSell = simpleStrategy.calculateWhatUserShouldSell(stocks, userStocks);
		// then
		assertEquals(0,stocksToSell.size());
	}

}
