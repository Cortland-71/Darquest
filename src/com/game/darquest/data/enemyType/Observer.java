package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Observer implements Classable {

	private ItemHub ic;
	private Controller c;

	private int minStat = 5;
	private int maxStat;
	
	

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.lowWeaponsList().get(rand.nextInt(ic.lowWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.midArmorList().get(rand.nextInt(ic.midArmorList().size()));
	}
	
	

	@Override
	public String getName() {
		return "Observer";
	}

	@Override
	public double getGeneratedCash() {
		int min = 1000;
		int max = 10000;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	

	@Override
	public void setController(Controller c) {
		this.ic = c.getItemHub();
		this.c = c;
		
	}
	
	
	@Override
	public String getDescription() {
		return "Observer description";
	}
}
