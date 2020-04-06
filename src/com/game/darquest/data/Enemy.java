package com.game.darquest.data;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Enemy extends Person {

	private String type;
	private int id;
	private String imagePath;

	public Enemy(String name, int lvl, String type, int def, int stealth, int awareness, Weapon equippedWeapon,
			Armor equippedArmor, Tool equippedTool, int id, double cash, String imagePath) {
		super(name, def, stealth, awareness, equippedWeapon, equippedArmor, equippedTool, lvl, cash);
		this.type = type;
		this.id = id;
		this.imagePath = imagePath;
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

	public String getImagePath() {
		return imagePath;
	}
}
