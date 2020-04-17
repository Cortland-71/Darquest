package com.game.darquest.data.actions.hostile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.EfficiencyHandler;
import com.game.darquest.data.actions.Fireable;
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
			int weaponDamage = getWeaponDamage(w);
			int def = choosen.getDef();
			int minimumWeaponDamage = p.getEquippedWeapon().getMinDamage();
			
			double weaponDamageWithEngMult = getWeaponDamageWithEngMult(p, weaponDamage);
			double finalDamage = getFinalWeaponDamage(def, weaponDamageWithEngMult);
			double limitRaised = 0;
			
			if(!w.getName().equals("none")) w.setCondition(w.getCondition() - 1);
			if(p instanceof Enemy) w.setCondition(w.getCondition() + 1);
			p.setEng(p.getEng() - .3);
			p.setEat(p.getEat() - .1);
			p.setSleep(p.getSleep() - .1);
			
			if(p instanceof Player && choosen instanceof Player) {
				List<Enemy> enemyList = c.getFightClubController().getEnemyList();
				StringBuilder sb = new StringBuilder();
				
				for(Enemy enemy : enemyList) {
					
					if(enemy.getType().getName().equals("Shinobi")) {
						enemy.setLimit(enemy.getLimit() + finalDamage);
						sb.append("Shinobi: " + enemy.getName() + " +" + f2.format(finalDamage) + " Limit\n");
					}
				}
			
				choosen.setHp(choosen.getHp() - finalDamage);
				//c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
				output = "You attacked yourself.\n" + sb;
				return true;
			}
			
			if(minimumWeaponDamage < choosen.getDef()) {
				output = "Attack missed."
						+ "\nMinimum attack damage: " + weaponDamage 
						+ "\n" + choosen.getName() + "'s Deffense: " + choosen.getDef()
						+ "\nDeffense to high.";
				EfficiencyHandler.setEfficiencyScore(EfficiencyHandler.getEfficiencyScore() - 5);
				return true;
			}
			
			
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
			//c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
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
