package com.stockexchange.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.stockexchange.model.Stock;

@Repository
public class StockRepository {

	Map<String, List<Stock>> stockMap = new LinkedHashMap<String, List<Stock>>();
	
	public Map<String, List<Stock>> readDataFromFile(){
		String csvFile = "dane.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				
				List<Stock> stockList = stockMap.get(data[1]);
				if (null == stockList)
				{
					stockList = new ArrayList<Stock>();
					stockList.add(new Stock(data[0], data[2]));
					stockMap.put(data[1], stockList);
				}
				else
				{
					stockList.add(new Stock(data[0], data[2]));
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
	
}
