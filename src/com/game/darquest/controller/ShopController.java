package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Armor;
import com.game.darquest.data.Item;
import com.game.darquest.data.Player;
import com.game.darquest.data.Weapon;

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
		this.c.getView().getShopView().setAllShopItems(allItemsList);
	}
	
	private List<Item> getWeaponsList() {
		return Arrays.asList(new Weapon("Test weapon",200,.5,9,1,5),
				new Weapon("Katana 1",13000,.5,4,16,22),
				new Weapon("Katana 2",2300,.5,10,50,99),
				new Weapon("Katana 3",500000,.5,8,20,124));
	}
	
	private List<Item> getArmorList() {
		return Arrays.asList(new Armor("Raincoat", 150, .05, 10),
				new Weapon("Katana 1",13000,.5,4,10,16),
				new Weapon("Katana 2",2300,.5,10,10,50),
				new Weapon("Katana 3",500000,.5,8,10,20));
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
		player = (Player)c.getNewPlayerController().getPlayer();
		Item selectedItem = getBoughtItem();
		if(player.getCash() >= selectedItem.getPrice()) {
			player.setCash(player.getCash()-selectedItem.getPrice());
			player.addItemToPlayerInventory(selectedItem);
			
			c.getView().getDownTownView().setPlayerStats(player);
			c.getView().getDownTownView().clearAllInventoryItems();
			c.getView().getDownTownView().setAllInventoryItems(player.getInventoryLists());
			
			c.getView().getShopView().setPlayerStats(player);
			c.getView().getShopView().clearAllInventoryItems();
			c.getView().getShopView().setAllInventoryItems(player.getInventoryLists());
		}
		
	}
	
	private Item getBoughtItem() {
		int tabIndex = c.getView().getShopView().getShopTabPane()
				.getSelectionModel().getSelectedIndex();
		Item boughtItem = c.getView().getShopView().getListViewObjects()
		.get(tabIndex).getSelectionModel().getSelectedItem();
		return boughtItem;
	}
}
