package com.game.darquest.data;

import com.game.darquest.data.enemyType.Classable;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public class Enemy extends Person {

	private Classable type;
	private int id;
	private String imagePath;
	private double limit = 0;

	public Enemy(String name, int defaultAttack, int defaultDef, int defaultStealth, int defaultAwareness,
			int defaultMutation, Weapon equippedWeapon, Armor equippedArmor, int lvl, double cash, Classable type,
			int id, String imagePath) {
		super(name, defaultAttack, defaultDef, defaultStealth, defaultAwareness, defaultMutation, equippedWeapon,
				equippedArmor, lvl, cash);
		this.type = type;
		this.id = id;
		this.imagePath = imagePath;
	}
	
	public Enemy() {}

	public Classable getType() {
		return type;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public double getLimit() {
		return this.limit;
	}

	public void setLimit(double limit) {
		if (limit > 1) {
			this.limit = super.getMAX_BAR();
			return;
		}
		if (limit < 0) {
			this.limit = super.getMIN();
			return;
		}
		this.limit = Math.round(limit * 100.0) / 100.0;
	}


}
