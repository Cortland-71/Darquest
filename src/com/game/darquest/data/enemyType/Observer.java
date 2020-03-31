package com.game.darquest.data.enemyType;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public class Observer extends EnemyType {

	@Override
	public int getGenerateDef() {
		return rand.nextInt(level*2);
	}

	@Override
	public int getGenerateStealth() {
		return rand.nextInt(level);
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt(level*5);
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)getLowWeaponsList().get(rand.nextInt(getLowWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)getMidArmorList().get(rand.nextInt(getMidArmorList().size()));
	}

	@Override
	public String getType() {
		return "Observer";
	}

	@Override
	public double getGeneratedCash() {
		int min = 100;
		int max = 1000;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	

}
