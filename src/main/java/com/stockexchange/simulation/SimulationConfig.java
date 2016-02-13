package com.stockexchange.simulation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class SimulationConfig {

	private final String firstSimulationDay = "20130102";
	private final String lastSimulationDay = "20131230";
	private boolean isSimulationRunning = true;
	
	Date actualDay = new Date();
	Date lastDay = new Date();
	DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
	
	public SimulationConfig() throws ParseException{
		actualDay = format.parse(firstSimulationDay);
		lastDay = format.parse(lastSimulationDay);
	}
	
	public Date getActualDay() {
		return actualDay;
	}

	public Date getNextDay(Date actualDay){
		Calendar nextDay = Calendar.getInstance(); 
		nextDay.setTime(actualDay); 
		nextDay.add(Calendar.DATE, 1);
		actualDay = nextDay.getTime();
		return actualDay;
	}
	
	public boolean isNextDay(){
		actualDay = getNextDay(actualDay);
		if(actualDay.after(lastDay)){
			isSimulationRunning = false;
		}
		return isSimulationRunning;
	}
	
}
