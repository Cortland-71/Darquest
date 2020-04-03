package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Person;
import com.game.darquest.data.items.Weapon;

public class Attack implements Fireable {

	private String output;
	private DecimalFormat f2 = new DecimalFormat("0.00");
	
	private Controller c;
	
	public Attack(Controller c) {
		this.c = c;
	}
	
	@Override
	public boolean fire(Person p, Person choosen) {
		if(p.getEat() >= .1 && p.getSleep() >= .1) {
			Weapon w = p.getEquippedWeapon();
			int def = 0;
			double finalDamage = 0;
			double damageAmount = getRandomDamageAmount(w, p);
			
			p.setEng(p.getEng() - .1);
			p.setEat(p.getEat() - .1);
			p.setSleep(p.getSleep() - .1);
			
			def = choosen.getDef();
			finalDamage = damageAmount - ((def/2d)/100d);
			choosen.setHp(choosen.getHp() - finalDamage);
			
			output = "Attacked: " + choosen.getName()
					+ "\nInit Damage: "+f2.format(damageAmount)
					+"\nEnemy Def: "+def
					+"\nFinal Damage: "+ f2.format(finalDamage);
			
			c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
			return true;
			
		}
		output = "Failed to attack. You must have .1 Eat, .1 Sleep.";
		return false;
	}
	
	public double getRandomDamageAmount(Weapon w, Person p) {
		Random rand = new Random();
		int min = w.getMinDamage();
		int max = w.getMaxDamage();
		double weaponDamage = rand.nextInt((max - min) + 1) + min;
		double engMult = p.getEng();
		w.setCondition(w.getCondition() - 1);
		return (weaponDamage / 100d) + (engMult / 2);
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
