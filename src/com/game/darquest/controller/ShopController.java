package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	private List<String> odetteDialog = Arrays.asList("Nice choice.", "That should help.", 
			"That's one of my most popular items.", "I like your style.", "Carful with that.", "One of my favorites.",
			"I should get one of these myself.", "I rebuilt this one from scratch.", "Well worth the price.");
	private Random rand = new Random();
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
		} else if(((Button)e.getSource()).getId().equals("sell")) {
			sellItem();
			return;
		}
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		c.getDownTownController().getDownTownActions().get(eventIndex).clickAction();
		
	}
	
	private void sellItem() {
		player = (Player)c.getPlayer();
		Item selectedItem = getSoldItem();
		if(selectedItem.getName() != "none") {
			player.setCash(player.getCash()+selectedItem.getValue());
			player.setWeight(player.getWeight()-selectedItem.getWeight());
			
			player.removeItemFromPlayerInventory(selectedItem, getSoldItemIndex());
			List<Integer> list = c.getInventoryController().getSelectedInventoryIndexForAllTabs();
			
			c.getInventoryController().setPlayerInventoryItemsForAllLocations();
			c.getInventoryController().setSelectedInventoryItemsForAllTabs(list);
			
			c.getInventoryController().equipNoneItemWhenSelling();
			c.getInventoryController().setPlayerStatsForAllLocations();
		}
	}

	private void buyItem() {
		player = (Player)c.getPlayer();
		Item selectedItem = getBoughtItem();
		if(player.getCash() >= selectedItem.getPrice()) {
			player.setCash(player.getCash()-selectedItem.getPrice());
			player.setWeight(player.getWeight()+selectedItem.getWeight());
			player.addItemToPlayerInventory(selectedItem);
			
			List<Integer> list = c.getInventoryController().getSelectedInventoryIndexForAllTabs();
			c.getInventoryController().setPlayerInventoryItemsForAllLocations();
			c.getInventoryController().setSelectedInventoryItemsForAllTabs(list);
			c.getInventoryController().setPlayerStatsForAllLocations();
			
			shopDialogOutput(selectedItem);
			return;
		}
		c.getView().getShopView().setBuyShopDialogeRed();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: Sorry "+c.getPlayer().getName()+
				", you don't have enough cash for this...");
	}
	private int getSoldItemIndex() {
		int tabIndex = c.getView().getShopView().getInventoryTabPane()
				.getSelectionModel().getSelectedIndex();
		int itemIndex = c.getView().getShopView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedIndex();
		return itemIndex;
	}
	
	private Item getSoldItem() {
		int tabIndex = c.getView().getShopView().getInventoryTabPane()
				.getSelectionModel().getSelectedIndex();
		Item selectedItem = c.getView().getShopView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
		return selectedItem;
	}
	
	private Item getBoughtItem() {
		int tabIndex = c.getView().getShopView().getShopTabPane()
				.getSelectionModel().getSelectedIndex();
		Item boughtItem = c.getView().getShopView().getShopListViewObjects()
		.get(tabIndex).getSelectionModel().getSelectedItem();
		return boughtItem;
	}
	
	private void shopDialogOutput(Item item) {
		c.getView().getShopView().setBuyShopDialogeNormal();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: "+odetteDialog.get(rand.nextInt(odetteDialog.size()))+
				"\n\tItem bought: "+item.getName()+"\n\tCost: -"+item.getPriceFormatted()+"\n\tWeight: +"+item.getWeight());
	}
}
