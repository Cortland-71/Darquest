package com.game.darquest.data;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public class Enemy extends Person {
	
	private String type;
	private int id;

	public Enemy(String name, int def, int stealth, int awareness, String type, Weapon equippedWeapon, Armor equippedArmor, int id) {
		super(name, def, stealth, awareness, equippedWeapon, equippedArmor);
		this.type = type;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return super.toString() + "Enemy [type=" + type + "]";
	}

	public int getId() {
		return id;
	}
}
