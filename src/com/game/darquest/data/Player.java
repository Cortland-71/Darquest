package com.game.darquest.data;

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
	
	private double weight = 0;
	
	public void addItemToPlayerInventory(Item item) {
		if (item instanceof Weapon) weaponList.add(item);
		else if (item instanceof Armor) armorList.add(item);
		else toolList.add(item);
	}
	
	public void removeItemFromPlayerInventory(Item item, int index) {
		if (item instanceof Weapon) weaponList.remove(index);
		else if (item instanceof Armor) armorList.remove(index);
		else toolList.remove(index);
	}
	
	public List<List<Item>> getInventoryLists() {
		return this.inventoryLists;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	

	
	
	
	
	 

	

	

	
}
