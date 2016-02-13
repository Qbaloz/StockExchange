package com.stockexchange.account;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockexchange.StockexchangeApplication;
import com.stockexchange.model.Money;
import com.stockexchange.model.Stock;
import com.stockexchange.model.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class AccountTest {

	@Autowired
	private Account account;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testShouldDepositMoneyInPLN(){
		//given
		account = new Account();
		Money money = new Money("PLN", 5000);
		double expectedBalance = 15000;
		//when
		account.depositMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("PLN"), 0);
	}
	
	@Test
	public void testShouldDepositMoneyInEUR(){
		//given
		account = new Account();
		Money money = new Money("EUR", 5000);
		double expectedBalance = 5000;
		//when
		account.depositMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("EUR"), 0);
	}
	
	@Test
	public void testShouldWithdrawMoneyInPLN(){
		//given
		account = new Account();
		Money money = new Money("PLN", 5000);
		double expectedBalance = 5000;
		//when
		account.withdrawMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("PLN"), 0);
	}
	
	@Test
	public void testShouldWithdrawMoneyInEUR(){
		//given
		account = new Account();
		Money money = new Money("EUR", 5000);
		double expectedBalance = -5000;
		//when
		account.withdrawMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("EUR"), 0);
	}

	@Test
	public void testShouldCheckAccountBalanceInPLN() {
		// given
		account = new Account();
		Map<String, Double> expectedBalance = new HashMap<String, Double>();
		expectedBalance.put("PLN", (double) 10000);
		expectedBalance.put("EUR", (double) 0);
		double expectedAmount = 10000;
		// when
		Map<String, Double> balance = account.getBalance();
		double amount = balance.get("PLN");
		// then
		assertNotNull(balance);
		assertEquals(expectedBalance.get("PLN"), balance.get("PLN"));
		assertEquals(amount, expectedAmount, 0);
	}
	
	@Test
	public void testShouldCheckAccountBalanceInEUR() {
		// given
		account = new Account();
		Map<String, Double> expectedBalance = new HashMap<String, Double>();
		expectedBalance.put("PLN", (double) 10000);
		expectedBalance.put("EUR", (double) 0);
		double expectedAmount = 0;
		// when
		Map<String, Double> balance = account.getBalance();
		double amount = balance.get("EUR");
		// then
		assertNotNull(balance);
		assertEquals(expectedBalance.get("EUR"), balance.get("EUR"));
		assertEquals(amount, expectedAmount, 0);
	}
	
	@Test
	public void testShouldAddStocksToUserAccount() {
		// given
		List<StockTo> expectedStockList = new ArrayList<StockTo>();
		expectedStockList.add(new StockTo(new Stock("KGHM",20), 100));
		expectedStockList.add(new StockTo(new Stock("PKOBP",20), 20));
		account = new Account();
		// when
		for(int i = 0; i < expectedStockList.size(); i++){
			account.addStocks(expectedStockList.get(i));
		}
		Map<String, Double> stocks = account.getStocks();
		// then
		assertEquals(expectedStockList.get(0).getAmount(), stocks.get("KGHM").doubleValue(),0);
		assertEquals(expectedStockList.get(1).getAmount(), stocks.get("PKOBP").doubleValue(),0);
	}
	
	@Test
	public void testShouldRemoveStocksFromUserAccount() {
		// given
		List<StockTo> expectedStockList = new ArrayList<StockTo>();
		expectedStockList.add(new StockTo(new Stock("KGHM",20), 100));
		expectedStockList.add(new StockTo(new Stock("PKOBP",20), 20));
		account = new Account();
		for(int i = 0; i < expectedStockList.size(); i++){
			account.addStocks(expectedStockList.get(i));
		}
		// when
		for(int i = 0; i < expectedStockList.size(); i++){
			account.removeStocks(expectedStockList.get(i));
		}
		Map<String, Double> stocks = account.getStocks();
		// then
		assertEquals(0, stocks.get("KGHM").doubleValue(),0);
		assertEquals(0, stocks.get("PKOBP").doubleValue(),0);
	}

}
