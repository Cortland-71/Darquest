package com.game.darquest.data.items;

public class Weapon extends Item {
	
	private int minDamage;
	private int maxDamage;

	public Weapon(String name, String description, double price, double weight, int condition, int maxCondition,
			int minDamage, int maxDamage) {
		super(name, description, price, weight, condition, maxCondition);
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	@Override
	public String toString() {
		return super.toString() + "\nDamage:\t" + minDamage + "-" + maxDamage;
	}
	
	@Override
	public String getToStringForPlayerInventory() {
		return super.getToStringForPlayerInventory() + "\nDamage:\t" + minDamage + "-" + maxDamage;
	}
}
