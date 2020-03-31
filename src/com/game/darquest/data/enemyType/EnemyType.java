package com.game.darquest.data.enemyType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Weapon;

public abstract class EnemyType implements Classable {
	
	protected Random rand = new Random();
	protected int level;
	
	private List<Item> highWeaponsList = Arrays.asList(
			new Weapon("Winterfield .40", 2000, .3, 10, 12, 25),
			new Weapon("AX-15", 2500, .5, 10, 15, 20),
			new Weapon("Cherry", 2100, .3, 10, 12, 18),
			new Weapon("Sniper Rifle", 3700, .6, 10, 20, 30),
			new Weapon("Shotgun", 4000, .4, 10, 22, 40),
			new Weapon("The Machine", 5500, .7, 10, 25, 40),
			new Weapon("Chainsaw", 5800, .5, 10, 20, 35));
	private List<Item> midWeaponsList = Arrays.asList(
			new Weapon("Switch Blade", 350, .02, 10, 6, 13),
			new Weapon("Tanto", 800, .08, 10, 7, 12),
			new Weapon("Machete", 1200, .07, 10, 8, 15),
			new Weapon("Samari Sword", 2200, .1, 10, 10, 20),
			new Weapon("Throwing Star", 1500, .01, 10, 10, 13),
			new Weapon("G26 9mm", 1800, .35, 10, 12, 22),
			new Weapon("Reefer .38", 1800, .25, 10, 10, 20));
	private List<Item> lowWeaponsList = Arrays.asList(
			new Weapon("None", 0, 0, 10, 1, 5),
			new Weapon("Studded Gloves", 150, .01, 10, 2, 8),
			new Weapon("Brass Knuckles", 350, .05, 10, 3, 9),
			new Weapon("Bat (wood)", 250, .07, 10, 5, 10),
			new Weapon("Bat (metal)", 530, .2, 10, 6, 13),
			new Weapon("Hammer", 600, .08, 10, 5, 15),
			new Weapon("Pocket Knife", 250, .01, 10, 5, 12));
	
	private List<Item> highArmorList = Arrays.asList(
			new Armor("Leather Jacket", 800, .02, 10, 3),
			new Armor("Ballistic Vest", 1000, .03, 10 , 4)
			);
	private List<Item> midArmorList = Arrays.asList(
			new Armor("Simple Vest", 250, .02, 10, 2),
			new Armor("Gas Mask", 300, .01, 10, 2)
			);
	private List<Item> lowArmorList = Arrays.asList(
			new Armor("None", 0, 0, 10, 0),
			new Armor("Raincoat", 100, .01, 10, 1),
			new Armor("Helmet", 150, .02, 10, 1)
			);
	
	public List<Item> getHighArmorList() {
		return highArmorList;
	}

	public List<Item> getMidArmorList() {
		return midArmorList;
	}

	public List<Item> getLowArmorList() {
		return lowArmorList;
	}

	public List<Item> getHighWeaponsList() {
		return highWeaponsList;
	}

	public List<Item> getMidWeaponsList() {
		return midWeaponsList;
	}

	public List<Item> getLowWeaponsList() {
		return lowWeaponsList;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
}
