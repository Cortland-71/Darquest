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
		
		double requiredSleep = .2;
		double requiredCash = 50;
		
		if(p.getCash() >= requiredCash) {
			if(p.getSleep() >= requiredSleep) {
				p.setSleep(p.getSleep() - requiredSleep);
				p.setCash(p.getCash() - requiredCash);
				double hpGained = rand.nextDouble();
				choosen.setHp(choosen.getHp() + hpGained);
				
				output = "Heal successful: " + p.getName() +
						"\nTarget: " + choosen.getName() +
						"\nHP: +" + f2.format(hpGained) +
						"\nSleep: -" +requiredSleep +
						"\nCash: -" + NumberFormat.getCurrencyInstance().format(requiredCash);
				return true;
			}
			output = "Heal Failed: Must have at least " + requiredSleep + " Sleep";
			return false;
		}
		output = "Heal Failed: Must have at least " + NumberFormat.getCurrencyInstance().format(requiredCash);
		return false;
	}
	

	@Override
	public String getCommandId() {
		return "he";
	}

	@Override
	public String getOutput() {
		return output;
	}
	
	
}
