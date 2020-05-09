package com.game.darquest.data.actions.primaryCommands;

import java.text.DecimalFormat;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
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
	public void fire(Person p, Person choosen) {
		attackAction(p, choosen);
	}
	
	private void attackAction(Person p, Person choosen) {
		Weapon w = p.getEquippedWeapon();
		int weaponDamage = getWeaponDamage(w);
		int def = choosen.getDef();
		int minimumDamage = p.getAttack();
		double finalDamage = getFinalWeaponDamage(def, minimumDamage+weaponDamage);
		
		if(!w.getName().equals("none")) w.setCondition(w.getCondition() - 1);
		if(p instanceof Enemy) w.setCondition(w.getCondition() + 1);
		
		if(minimumDamage < choosen.getDef()) {
			output = "Attack missed.\n"
					+ "Minimum attack damage: " + weaponDamage + "\n" 
					+ choosen.getName() + "'s Deffense: " + choosen.getDef() + "\n"
					+ "Deffense to high.\n\n";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		
		finalDamage = playerIsAttackingEnforcer(p, choosen, finalDamage);
		finalDamage = enemyIsAttacking(p, choosen, finalDamage);
		finalDamage = limitIsFull(p, choosen, finalDamage);
		
		double before = choosen.getHp();
		choosen.setHp(choosen.getHp() - finalDamage);
		double after = choosen.getHp();
		//c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
		output = "Attack successful: " + p.getName() + "\n"
				+ "Target:    " + choosen.getName() + "\n" 
				+ "HP:       -" + finalDamage + "\n"
				+ "HP before: " + before + "\n"
				+ "HP after:  " + after + "\n\n";
	}

	private double limitIsFull(Person p, Person choosen, double finalDamage) {
		if(p instanceof Player && choosen instanceof Enemy) {
			Enemy e = (Enemy)choosen;
			if(e.getLimit() == 1) {
				finalDamage *= 5;
			}
		}
		return finalDamage;
	}
	
	private double getFinalWeaponDamage(int def, int attackLevelAndWepDamage) {
		double attackAmount = (attackLevelAndWepDamage/100d) - ((def/2d)/100d);
		return attackAmount;
	}
	
	private int getWeaponDamage(Weapon w) {
		Random rand = new Random();
		int min = w.getMinDamage();
		int max = w.getMaxDamage();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	private double enemyIsAttacking(Person p, Person choosen, double finalDamage) {
		if(p instanceof Enemy && choosen instanceof Player) {
			return finalDamage *= 2;
		}
		return finalDamage;
	}
	
	private double playerIsAttackingEnforcer(Person p, Person choosen, double finalDamage) {
		if(p instanceof Player && choosen instanceof Enemy) {
			Enemy e = (Enemy)choosen;
			if(e.getType().getName().equals("Enforcer")) {
				if(e.getLimit() < 1)
					e.setLimit(e.getLimit() + finalDamage*2);
				else
					return finalDamage *= 3;
			}
		} 
		return finalDamage;
	}

	@Override
	public String getCommandId() {
		return "att";
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
