package com.game.darquest.data.actions.primaryCommands;

import java.text.DecimalFormat;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
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
		c.getFightClubController().setOutput(output, p);
	}
	
	private void attackAction(Person p, Person choosen) {
		Weapon w = p.getEquippedWeapon();
		int weaponDamage = getWeaponDamage(w);
		int def = choosen.getDef();
		int minimumWeaponDamage = p.getEquippedWeapon().getMinDamage();
		int minWepDamageAndAttackLevel = minimumWeaponDamage + 5;
		double finalDamage = getFinalWeaponDamage(def, minWepDamageAndAttackLevel);
		
		if(!w.getName().equals("none")) w.setCondition(w.getCondition() - 1);
		if(p instanceof Enemy) w.setCondition(w.getCondition() + 1);
		
		//a p.setEng(p.getEng() - .3);
		
		if(minimumWeaponDamage < choosen.getDef()) {
			output = "Attack missed.\n"
					+ "Minimum attack damage: " + weaponDamage + "\n" 
					+ choosen.getName() + "'s Deffense: " + choosen.getDef() + "\n"
					+ "Deffense to high.\n\n";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		
		double before = choosen.getHp();
		choosen.setHp(choosen.getHp() - finalDamage);
		double after = choosen.getHp();
		//c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
		output = "Attack successful: " + p.getName() + "\n"
				+ choosen.getName() + " HP -" + finalDamage + "\n"
				+ choosen.getName() + " HP before " + before + "\n"
				+ choosen.getName() + " HP after " + after + "\n\n";
	}

	
	private double getFinalWeaponDamage(int def, int attackLevelAndMinWepDamage) {
		return attackLevelAndMinWepDamage - ((def/2d)/100d);
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

	@Override
	public int getPointCost() {
		return 4;
	}
	@Override
	public boolean isModifiable() {
		return true;
	}
}
