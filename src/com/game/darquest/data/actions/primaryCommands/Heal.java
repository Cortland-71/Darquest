package com.game.darquest.data.actions.primaryCommands;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Heal implements Fireable {
	private DecimalFormat f2 = new DecimalFormat("0.00");
	private String output;
	private Random rand = new Random();
	public Heal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		
		double requiredCash = 50;
		
		if(p.getCash() >= requiredCash) {
			p.setCash(p.getCash() - requiredCash);
			double hpGained = (choosen.getMutation()/2d) /10;
			choosen.setHp(choosen.getHp() + hpGained);
			
			output = "Heal successful: " + p.getName() +
					"\nTarget: " + choosen.getName() +
					"\nHP: +" + f2.format(hpGained) +
					"\nCash: -" + NumberFormat.getCurrencyInstance().format(requiredCash) + "\n\n";
			return;
		}
		output = "Heal Failed: Must have at least " + NumberFormat.getCurrencyInstance().format(requiredCash);
	}
	

	@Override
	public String getCommandId() {
		return "he";
	}

	@Override
	public String getOutput() {
		return output;
	}
	
	@Override
	public int getPointCost() {
		return 3;
	}
	@Override
	public boolean isModifiable() {
		return true;
	}
}
