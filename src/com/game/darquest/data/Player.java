package com.game.darquest.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Tool;

public class Player extends Person {
	
	private List<Item> weaponList = new ArrayList<>();
	private List<Item> armorList = new ArrayList<>();
	private List<Item> toolList = new ArrayList<>();
	private List<List<Item>> inventoryLists = Arrays.asList(weaponList, armorList, toolList);
	
	private double weight = 0;
	
	public Player(String name) {
		super(name); 
	}
	
	public void addItemToPlayerInventory(Item item) {
		if (item instanceof Weapon) weaponList.add(item);
		else if (item instanceof Armor) armorList.add(item);
		else toolList.add(item);
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
