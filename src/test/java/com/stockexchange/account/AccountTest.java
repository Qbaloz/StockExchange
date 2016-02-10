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
	public void testShouldDepositMoneyInPolskiZloty(){
		//given
		account = new Account(10000,0);
		Money money = new Money("Polski Zloty", 5000);
		double expectedBalance = 15000;
		//when
		account.depositMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("Polski Zloty"), 0);
	}
	
	@Test
	public void testShouldDepositMoneyInEuro(){
		//given
		account = new Account(10000,0);
		Money money = new Money("Euro", 5000);
		double expectedBalance = 5000;
		//when
		account.depositMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("Euro"), 0);
	}
	
	@Test
	public void testShouldWithdrawMoneyInPolskiZloty(){
		//given
		account = new Account(10000,0);
		Money money = new Money("Polski Zloty", 5000);
		double expectedBalance = 5000;
		//when
		account.withdrawMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("Polski Zloty"), 0);
	}
	
	@Test
	public void testShouldWithdrawMoneyInEuro(){
		//given
		account = new Account(10000,5000);
		Money money = new Money("Euro", 5000);
		double expectedBalance = 0;
		//when
		account.withdrawMoney(money);
		Map<String, Double> balance = account.getBalance();
		//then
		assertEquals(expectedBalance, balance.get("Euro"), 0);
	}

	@Test
	public void testShouldCheckAccountBalanceInPolskiZloty() {
		// given
		account = new Account(10000,5000);
		Map<String, Double> expectedBalance = new HashMap<String, Double>();
		expectedBalance.put("Polski Zloty", (double) 10000);
		expectedBalance.put("Euro", (double) 0);
		double expectedAmount = 10000;
		// when
		Map<String, Double> balance = account.getBalance();
		double amount = balance.get("Polski Zloty");
		// then
		assertNotNull(balance);
		assertEquals(expectedBalance.get("Polski Zloty"), balance.get("Polski Zloty"));
		assertEquals(amount, expectedAmount, 0);
	}
	
	@Test
	public void testShouldCheckAccountBalanceInEuro() {
		// given
		account = new Account(10000,5000);
		Map<String, Double> expectedBalance = new HashMap<String, Double>();
		expectedBalance.put("Polski Zloty", (double) 10000);
		expectedBalance.put("Euro", (double) 5000);
		double expectedAmount = 5000;
		// when
		Map<String, Double> balance = account.getBalance();
		double amount = balance.get("Euro");
		// then
		assertNotNull(balance);
		assertEquals(expectedBalance.get("Euro"), balance.get("Euro"));
		assertEquals(amount, expectedAmount, 0);
	}
	
	@Test
	public void testShouldAddStocksToUserAccount() {
		// given
		List<StockTo> expectedStockList = new ArrayList<StockTo>();
		expectedStockList.add(new StockTo(new Stock("KGHM",20), 100));
		expectedStockList.add(new StockTo(new Stock("PKOBP",20), 20));
		account = new Account(10000,5000);
		// when
		for(int i = 0; i < expectedStockList.size(); i++){
			account.addStocks(expectedStockList.get(i), expectedStockList.get(i).getAmount());
		}
		Map<String, Double> stocks = account.getStockList();
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
		account = new Account(10000,5000);
		for(int i = 0; i < expectedStockList.size(); i++){
			account.addStocks(expectedStockList.get(i), expectedStockList.get(i).getAmount());
		}
		// when
		for(int i = 0; i < expectedStockList.size(); i++){
			account.removeStocks(expectedStockList.get(i), expectedStockList.get(i).getAmount());
		}
		Map<String, Double> stocks = account.getStockList();
		// then
		assertEquals(0, stocks.get("KGHM").doubleValue(),0);
		assertEquals(0, stocks.get("PKOBP").doubleValue(),0);
	}

}
