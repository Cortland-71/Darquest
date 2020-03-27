package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ShopController implements EventHandler<ActionEvent>{
	
	private Controller c;
	private List<List<Item>> allItemsList = new ArrayList<>();
	private Player player;
	
	public ShopController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addActionListener(this);

		this.c.getView().getShopView().addBuyButtonListener(this);
		allItemsList.add(getWeaponsList());
		allItemsList.add(getArmorList());
		allItemsList.add(getToolList());
		this.c.getView().getShopView().setAllShopItems(allItemsList);
	}
	
	private List<Item> getWeaponsList() {
		return Arrays.asList(
				new Weapon("Switch Blade",375,.03,9,1,5),
				new Weapon("Tanto",13000,.1,4,16,22),
				new Weapon("Bat (wood)",250,.07,10,50,99),
				new Weapon("Bat (metal)",530,.12,8,20,124),
				new Weapon("Brass Knuckles",400,.05,8,20,124));
	}
	
	private List<Item> getArmorList() {
		return Arrays.asList(
				new Armor("Raincoat", 150, .05, 10),
				new Armor("Vest lvl.1",13000,.5,4),
				new Armor("Leather Jacket",2300,.5,10),
				new Armor("Cod Peice",500000,.5,8));
	}
	
	private List<Item> getToolList() {
		return Arrays.asList(
				new Tool("Reactor",3300, .04),
				new Tool("HP Vial",13000,.5),
				new Tool("ENG Vial",2300,.5),
				new Tool("Bottle of Luck",500000,.5));
	}
	

	@Override
	public void handle(ActionEvent e) {
		if(((Button)e.getSource()).getId().equals("buy")) {
			buyItem();
			return;
		}
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		c.getDownTownController().getDownTownActions().get(eventIndex).clickAction();
		
	}
	
	private void buyItem() {
		player = (Player)c.getPlayer();
		Item selectedItem = getBoughtItem();
		if(player.getCash() >= selectedItem.getPrice()) {
			player.setCash(player.getCash()-selectedItem.getPrice());
			player.setWeight(player.getWeight()+selectedItem.getWeight());
			player.addItemToPlayerInventory(selectedItem);
			
			List<Integer> list = c.getView().getDownTownView().getSelectedIndexListOfWeaponAndArmorTabs();
			
			c.getView().getDownTownView().setPlayerStats(player);
			c.getView().getShopView().setPlayerStats(player);
			
			c.getView().getDownTownView().setAllInventoryItems(player.getInventoryLists());
			c.getView().getShopView().setAllInventoryItems(player.getInventoryLists());
			
			for (int i = 0; i < list.size(); i++) {
				c.getView().getDownTownView().getInventoryListViewObjects().get(i).getSelectionModel().select(list.get(i));
				c.getView().getShopView().getInventoryListViewObjects().get(i).getSelectionModel().select(list.get(i));
			}

		}
		
	}
	
	private Item getBoughtItem() {
		int tabIndex = c.getView().getShopView().getShopTabPane()
				.getSelectionModel().getSelectedIndex();
		Item boughtItem = c.getView().getShopView().getShopListViewObjects()
		.get(tabIndex).getSelectionModel().getSelectedItem();
		return boughtItem;
	}
}
