package com.game.darquest.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Weapon;

public class Player extends Person {
	
	private List<Item> weaponList = new ArrayList<>();
	private List<Item> armorList = new ArrayList<>();
	private List<Item> toolList = new ArrayList<>();
	private List<List<Item>> inventoryLists = Arrays.asList(weaponList, armorList, toolList);
	
	private DecimalFormat f = new DecimalFormat("0.00");
	private double weight = 0;
	private double xp = .1;
	private double xpAddedBeatEnemy = .1;
	private double xpAdded = .01;
	private double remainingXp = 0;
	private final double maxXp = 1;
	private final double minXp = 0;
	

	public void addItemToPlayerInventory(Item item) {
		setWeight(getWeight() + item.getWeight());
		if (item instanceof Weapon) weaponList.add(item);
		else if (item instanceof Armor) armorList.add(item);
		else toolList.add(item);
	}
	
	public int removeItemFromPlayerInventory(Item item, int index) {
		setWeight(getWeight() - item.getWeight());
		if (item instanceof Weapon) {
			weaponList.remove(index);
			return 0;
		}
		else if (item instanceof Armor) {
			armorList.remove(index);
			return 1;
		}
		else {
			toolList.remove(index);
			return 2;
		}
	}
	
	public List<List<Item>> getInventoryLists() {
		return this.inventoryLists;
	}
	
	public void setWeight(double weight) {
		this.weight = Double.parseDouble(f.format(weight));
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = Math.round(xp * 100.0) / 100.0;
	}

	

	public double getXpAddedBeatEnemy() {
		return xpAddedBeatEnemy;
	}

	public void setXpAddedBeatEnemy(double xpAddedBeatEnemy) {
		this.xpAddedBeatEnemy = xpAddedBeatEnemy;
	}

	public double getXpAdded() {
		return xpAdded;
	}

	public void setXpAdded(double xpAdded) {
		this.xpAdded = xpAdded;
	}

	public double getMaxXp() {
		return maxXp;
	}

	public double getMinXp() {
		return minXp;
	}

	public double getRemainingXp() {
		return remainingXp;
	}

	public void setRemainingXp(double remainingXp) {
		this.remainingXp = remainingXp;
	}
	
	
}
