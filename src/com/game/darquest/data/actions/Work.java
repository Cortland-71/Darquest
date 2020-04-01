package com.game.darquest.data.actions;

import java.text.NumberFormat;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

public class Work implements Fireable {
	
	private Controller c;
	private final double basePay = 8.5;
	private boolean isValid;
	private String output;
	
	public Work(Controller c) {
		this.c = c;
	}
	
	@Override
	public boolean fire(Person p) {
		if(p.getWork()+.1 <= p.getMaxWork()) {
			
			p.setWork(p.getWork()+.1);
			double finalPay = (p.getWork()*10) * basePay;
			double lostEng = 0;
			if(p.getEng() > p.getMinEng()) lostEng = p.getWork()/4;
			
			p.setCash(p.getCash()+finalPay);
			p.setEng(p.getEng()-lostEng);
			
			output = "Your hard work is paying off.\nYou gained: +"+NumberFormat.getCurrencyInstance().format(finalPay) +
					"\nEng lost: "+lostEng;
			return true;
		}
		p.setWork(p.getMaxWork());
		output = "You cannot work anymore...\n"
				+ "Try sleeping to recover.";
		return false;
	}

	@Override
	public String getCommandId() {
		return "work";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
