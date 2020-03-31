package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ShopInventoryController implements EventHandler<MouseEvent> {
	
	private List<List<Item>> allItemsList = new ArrayList<>();

	private Controller c;
	public ShopInventoryController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addShopListViewListener(this);
		this.c.getView().getShopView().addShopTabListener(this);
		
		allItemsList.add(getWeaponsList());
		allItemsList.add(getArmorList());
		allItemsList.add(getToolList());
		this.c.getView().getShopView().setAllShopItems(allItemsList);
		
	}
	
	private List<Item> getWeaponsList() {
		return Arrays.asList(
				new Weapon("Studded Gloves", 150, .01, 10, 2, 8),
				new Weapon("Brass Knuckles", 350, .05, 10, 3, 9),
				new Weapon("Bat (wood)", 250, .07, 10, 5, 10),
				new Weapon("Bat (metal)", 530, .2, 10, 6, 13),
				new Weapon("Hammer", 600, .08, 10, 5, 15),
				new Weapon("Pocket Knife", 250, .01, 10, 5, 12),
				new Weapon("Switch Blade", 350, .02, 10, 6, 13),
				new Weapon("Tanto", 800, .08, 10, 7, 12),
				new Weapon("Machete", 1200, .07, 10, 8, 15),
				new Weapon("Samari Sword", 2200, .1, 10, 10, 20),
				new Weapon("Throwing Star", 1500, .01, 10, 10, 13),
				new Weapon("G26 9mm", 1800, .35, 10, 12, 22),
				new Weapon("Reefer .38", 1800, .25, 10, 10, 20),
				new Weapon("Winterfield .40", 2000, .3, 10, 12, 25),
				new Weapon("AX-15", 2500, .5, 10, 15, 20),
				new Weapon("Cherry", 2100, .3, 10, 12, 18),
				new Weapon("Sniper Rifle", 3700, .6, 10, 20, 30),
				new Weapon("Shotgun", 4000, .4, 10, 22, 40),
				new Weapon("The Machine", 5500, .7, 10, 25, 40),
				new Weapon("Chainsaw", 5800, .5, 10, 20, 35));
	}
	
	private List<Item> getArmorList() {
		return Arrays.asList(
				new Armor("Raincoat", 100, .01, 10, 1),
				new Armor("Helmet", 150, .02, 10, 1),
				new Armor("Simple Vest", 250, .02, 10, 2),
				new Armor("Gas Mask", 300, .01, 10, 2),
				new Armor("Leather Jacket", 800, .02, 10, 3),
				new Armor("Ballistic Vest", 1000, .03, 10 , 4));
	}
	
	private List<Item> getToolList() {
		return Arrays.asList(
				new Tool("Reactor",3300, .04),
				new Tool("HP Vial",13000,.5),
				new Tool("ENG Vial",2300,.5),
				new Tool("Bottle of Luck",500000,.5));
	}

	@Override
	public void handle(MouseEvent e) {
		setSelectedShopItemToLabel();
	}
	
	public void setSelectedShopItemToLabel() {
		int tabIndex = c.getView().getShopView().getShopTabPane().getSelectionModel().getSelectedIndex();
		Item itemSelected = c.getView().getShopView().getShopListViewObjects().get(tabIndex).getSelectionModel().getSelectedItem();
		c.getView().getShopView().setSelectedItemInfoLabel(itemSelected.toString());
	}

}
