package com.stockexchange.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stockexchange.simulation.Simulation;

@Controller
public class SimulationController {

	@Autowired
	private Simulation simulation;
	
    @RequestMapping(value = "/strategy/simpleStrategy", method = RequestMethod.POST)
    public String SimulationResults(Map<String, Object> params) {
    	simulation.runSimulation("SimpleStrategy");
    	params.put("balance", Math.floor(simulation.getBalance() * 100) / 100);
    	params.put("accountValue", Math.floor(simulation.getAccountValue() * 100) / 100);
    	params.put("userStocks", simulation.getUserStocks());
        return "results";
    }
	
}
