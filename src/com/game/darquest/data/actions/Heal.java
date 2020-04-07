package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import com.game.darquest.data.Person;

public class Heal implements Fireable {
	private DecimalFormat f2 = new DecimalFormat("0.00");
	private String output;
	private Random rand = new Random();
	public Heal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		if(Action.badId(choosen)) {
			output = "That id is not recognized.";
			return false;
		}
		
		if(p.getSleep() >= .2) {
			double sleepLost = .2;
			p.setSleep(p.getSleep() - sleepLost);
			double hpGained = rand.nextDouble();
			choosen.setHp(choosen.getHp() + hpGained);
			
			output = p.getName() + " healed " + choosen.getName() + "\nHP gained: +" + f2.format(hpGained) + 
					"\n" + "Sleep lost: " + NumberFormat.getCurrencyInstance().format(sleepLost);
			return true;
		}
		
		output = p.getName() + " failed to heal. Must have at least .2 sleep.";
		return false;
	}

	@Override
	public String getCommandId() {
		return "ea";
	}

	@Override
	public String getOutput() {
		return output;
	}
	
	
}
