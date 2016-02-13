package com.stockexchange.repository.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.stockexchange.model.Stock;
import com.stockexchange.repository.StockMarketDataRepository;

@Repository
public class StockMarketDataRepositoryImpl implements StockMarketDataRepository{

	private Map<Date, List<Stock>> stockMap = new HashMap<Date, List<Stock>>();
	Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
	
	public StockMarketDataRepositoryImpl(){
		readDataFromFile();
	}
	
	@Override
	public Map<Date, List<Stock>> readDataFromFile(){
		String csvFile = "dane.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				
				double value = Double.parseDouble(data[2]);
				
				try {
					date = format.parse(data[1]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<Stock> stockList = stockMap.get(date);
				if (null == stockList)
				{
					stockList = new ArrayList<Stock>();
					stockList.add(new Stock(data[0], value));
					stockMap.put(date, stockList);
				}
				else
				{
					stockList.add(new Stock(data[0], value));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return stockMap;
	  }

	@Override
	public List<Stock> getStocksFromDay(Date date) {
		return stockMap.get(date);
	}

	@Override
	public Map<Date, List<Stock>> findAll() {
		return stockMap;
	}
	
}
