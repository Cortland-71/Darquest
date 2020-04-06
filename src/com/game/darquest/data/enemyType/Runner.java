package com.game.darquest.data.enemyType;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Runner implements Classable {

	private ItemHub ic;
	private int level;

	@Override
	public int getGenerateDef() {
		return rand.nextInt(level);
	}

	@Override
	public int getGenerateStealth() {
		return rand.nextInt(level*2);
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt(level*2);
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.midWeaponsList().get(rand.nextInt(ic.midWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.lowArmorList().get(rand.nextInt(ic.lowArmorList().size()));
	}

	@Override
	public String getType() {
		return "Runner";
	}

	@Override
	public double getGeneratedCash() {
		int min = 100;
		int max = 5000;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	@Override
	public Tool getGenerateTool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		
	}
	
	@Override
	public void setController(Controller c) {
		this.ic = c.getItemHub();
		
	}
}
