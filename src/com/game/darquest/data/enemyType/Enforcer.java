package com.game.darquest.data.enemyType;

import com.game.darquest.controller.Controller;
import com.game.darquest.controller.ItemController;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Enforcer implements Classable {
	private ItemController ic;
	private int level;
	
	
	

	@Override
	public int getGenerateDef() {
		return rand.nextInt(level*5);
	}

	@Override
	public int getGenerateStealth() {
		return rand.nextInt(level);
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt(level);
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.highWeaponsList().get(rand.nextInt(ic.highWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.highArmorList().get(rand.nextInt(ic.highArmorList().size()));
	}
	
	@Override
	public Tool getGenerateTool() {
		return null;
	}


	@Override
	public String getType() {
		return "Enforcer";
	}

	@Override
	public double getGeneratedCash() {
		int min = 1;
		int max = 100;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		
	}

	@Override
	public void setController(Controller c) {
		this.ic = c.getItemController();
		
	}

}
