package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
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
		
		if(playerAttackedThemselves(p, choosen)) return false;
		
		
		
		if(p.getEat() >= .1 && p.getSleep() >= .1) {
			
			
				
			
			Weapon w = p.getEquippedWeapon();
			int weaponDamage = getWeaponDamage(w);
			double weaponDamageWithEngMult = getWeaponDamageWithEngMult(p, weaponDamage);
			int def = choosen.getDef();
			double finalDamage = getFinalWeaponDamage(def, weaponDamageWithEngMult);
			
			w.setCondition(w.getCondition() - 1);
			p.setEng(p.getEng() - .3);
			p.setEat(p.getEat() - .1);
			p.setSleep(p.getSleep() - .1);
			
			if(weaponDamage < choosen.getDef()) {
				output = "Attack failed."
						+ "\nAttack damage: " + weaponDamage 
						+ "\n" + choosen.getName() + "'s Deffense: " + choosen.getDef()
						+ "\nDeffense to high.";
				return true;
			}
			
			double limitRaised = 0;
			if(choosen instanceof Enemy) {
				Enemy e = (Enemy)choosen;
				if(e.getType().getName().equals("Enforcer")) {
					if(e.getLimit() < 1) {
						limitRaised = finalDamage * 2;
						e.setLimit(e.getLimit() + limitRaised);
						finalDamage /= 4;
					}
				} else {
					if(e.getLimit() < 1) {
						e.setLimit(e.getLimit() + limitRaised);
						finalDamage /= 4;
					}
				}
			} 
			
			
			choosen.setHp(choosen.getHp() - finalDamage);
			c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
			output = "Limit raised: +" + Math.round(limitRaised * 100d) / 100d
					+ "\n\nAttacked: " + choosen.getName()
					+ "\nWeapon damage: " + weaponDamage
					+ "\nDamage + Eng multiplyer: "+f2.format(weaponDamageWithEngMult)
					+ "\n"+ choosen.getName() + "'s defense: " + choosen.getDef()
					+ "\nFinal Damage: "+ f2.format(finalDamage);
			return true;
		}
		output = "Failed to attack. You must have .1 Eat, .1 Sleep.";
		return false;
	}
	
	private boolean playerAttackedThemselves(Person p, Person choosen) {
		if(p instanceof Player && choosen instanceof Player) {
			output = "You can't attack yourself.";
			return true;
		}
		return false;
	}
	
//	private boolean enteredBadId(Person choosen) {
//		if(choosen==null) {
//			System.out.println("inside bad id");
//			output = "That id is not recognized.";
//			return true;
//		}
//		return false;
//	}
	
	private double getFinalWeaponDamage(int def, double weaponDamageWithEngMult) {
		return weaponDamageWithEngMult - ((def/2d)/100d);
	}
	
	private double getWeaponDamageWithEngMult(Person p, int weaponDamage) {
		double engMult = p.getEng();
		return (weaponDamage / 100d) + (engMult / 2);
	}
	
	private int getWeaponDamage(Weapon w) {
		Random rand = new Random();
		int min = w.getMinDamage();
		int max = w.getMaxDamage();
		return rand.nextInt((max - min) + 1) + min;
	}

	@Override
	public String getCommandId() {
		return "att";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
