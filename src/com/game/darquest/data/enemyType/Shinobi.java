package com.game.darquest.data.enemyType;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public class Shinobi extends EnemyType {

	@Override
	public int getGenerateDef() {
		return rand.nextInt(level);
	}

	@Override
	public int getGenerateStealth() {
		return rand.nextInt(level*5);
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt(level*5);
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)getHighWeaponsList().get(rand.nextInt(getHighWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)getLowArmorList().get(rand.nextInt(getLowArmorList().size()));
	}

	@Override
	public String getType() {
		return "Shinobi";
	}

	@Override
	public double getGeneratedCash() {
		int min = 1000;
		int max = 100000;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	

}
