package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

public class Attack implements Fireable {

	private String output;
	private DecimalFormat f2 = new DecimalFormat("0.00");
	
	@Override
	public boolean fire(Person p, Person choosen) {
		if(p.getEat() >= .1 && p.getSleep() >= .1 && p.getWork() >= .1) {
			
			int def = 0;
			double finalDamage = 0;
			double damageAmount = p.getRandomDamageAmount();
			
			p.setEng(p.getEng() - .1);
			p.setEat(p.getEat() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setWork(p.getWork() - .1);
			
			def = choosen.getDef();
			finalDamage = damageAmount - ((def/2d)/100d);
			choosen.setHp(choosen.getHp() - finalDamage);
			
			output = "Attack landed successfully!"
					+ "\nInit Damage: "+f2.format(damageAmount)
					+"\nEnemy Def: "+def
					+"\nFinal Damage: "+ f2.format(finalDamage);
			return true;
			
		}
		output = "You failed to attack. You must have .1 Eat, .1 Sleep and .1 Work...";
		return false;
	}

	@Override
	public String getCommandId() {
		return "attack";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
