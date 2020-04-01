package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

public class Eat implements Fireable {
	
	private Controller c;
	private DecimalFormat f2 = new DecimalFormat("0.00");
	private String output;
	public Eat(Controller c) {
		this.c = c;
	}

	@Override
	public boolean fire(Person p) {
		if(p.getEat()+.1 <= p.getMaxEat()) {
			double costToEat = (p.getEat()+.1)*150.5;
			if(p.getCash() >= costToEat) {
				p.setEat(p.getEat()+.1);
				p.setCash(p.getCash()-costToEat);
				
				double hpGained = p.getEat()*.1;
				p.setHp(p.getHp()+hpGained);
				output = "You ate and feel much better.\n"
						+ "HP gained: +"+f2.format(hpGained)+"\n"
						+ "Cost: "+NumberFormat.getCurrencyInstance().format(costToEat);
				return true;
			}
			output = "You can't aford to eat at this point...\n"
					+ "It would cost: "+NumberFormat.getCurrencyInstance().format(costToEat);
			return false;
		}
		p.setEat(p.getMaxEat());
		output = "You can't eat anymore or you'll explode...";
		return false;
		
	}

	@Override
	public String getCommandId() {
		return "eat";
	}

	@Override
	public String getOutput() {
		return output;
	}
}

