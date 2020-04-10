package com.game.darquest.data;

import com.game.darquest.data.enemyType.Classable;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Enemy extends Person {

	private Classable type;
	private int id;
	private String imagePath;

	

	public Enemy(String name, int maxDef, int maxStealth, int maxAwareness, Weapon equippedWeapon, Armor equippedArmor,
			Tool equippedTool, int lvl, double cash, Classable type, int id, String imagePath) {
		super(name, maxDef, maxStealth, maxAwareness, equippedWeapon, equippedArmor, equippedTool, lvl, cash);
		this.type = type;
		this.id = id;
		this.imagePath = imagePath;
	}

	public Classable getType() {
		return type;
	}

	@Override
	public String toString() {
		return super.toString() + "Enemy [type=" + type + "]";
	}

	public int getId() {
		return id;
	}

	public String getImagePath() {
		return imagePath;
	}
}
