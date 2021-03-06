package com.stockexchange.simulation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockexchange.StockexchangeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockexchangeApplication.class)
public class SimulationTest {

	@Autowired
	private Simulation simulation;
	
	@Test
	public void testShouldRunSimpleStrategy(){
		// given
		String strategyName = "SimpleStrategy";
		// when
		simulation.runSimulation(strategyName);
		// then
	}

}
