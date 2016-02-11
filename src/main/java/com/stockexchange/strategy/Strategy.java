package com.stockexchange.strategy;

import java.util.List;

import com.stockexchange.model.Stock;

public interface Strategy {

	void calculateNextMove(List<Stock> stocks, double availableMoney);
	
}
