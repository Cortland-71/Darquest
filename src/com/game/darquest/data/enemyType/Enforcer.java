package com.game.darquest.data.enemyType;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Enforcer extends EnemyType {

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
		return (Weapon)getHighWeaponsList().get(rand.nextInt(getHighWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)getHighArmorList().get(rand.nextInt(getHighArmorList().size()));
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

}
