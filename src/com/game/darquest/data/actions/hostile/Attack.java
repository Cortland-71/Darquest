package com.game.darquest.data.actions.hostile;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.items.Weapon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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
		
		double weaponDamageWithEngMult = getWeaponDamageWithEngMult(p, weaponDamage);
		double finalDamage = getFinalWeaponDamage(def, weaponDamageWithEngMult);
		double limitRaised = 0;
		
		if(!w.getName().equals("none")) w.setCondition(w.getCondition() - 1);
		if(p instanceof Enemy) w.setCondition(w.getCondition() + 1);
		
		//this is here because if the player breaks an item it will requip the none item.
		
		
		p.setEng(p.getEng() - .3);
		
		if(minimumWeaponDamage < choosen.getDef()) {
			output = "Attack missed."
					+ "\nMinimum attack damage: " + weaponDamage 
					+ "\n" + choosen.getName() + "'s Deffense: " + choosen.getDef()
					+ "\nDeffense to high.\n\n";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		
		double before = choosen.getHp();
		choosen.setHp(choosen.getHp() - finalDamage);
		double after = choosen.getHp();
		//c.getPlayerInvStatsController().removeItemWhenUsedUp(w);
		output = "Attack successful: " + p.getName() + "\n"
				+ "Eng lost: -.1\n"
				+ choosen.getName() + " HP -" + finalDamage + "\n"
				+ choosen.getName() + " HP before " + before + "\n"
				+ choosen.getName() + " HP after " + after + "\n\n";
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
