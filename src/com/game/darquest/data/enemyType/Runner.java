package com.game.darquest.data.enemyType;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public class Runner extends EnemyType {

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
		return (Weapon)getMidWeaponsList().get(rand.nextInt(getMidWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)getLowArmorList().get(rand.nextInt(getLowArmorList().size()));
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
}
