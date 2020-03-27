package com.game.darquest.controller;

import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Weapon;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class InventoryController implements EventHandler<MouseEvent> {

	private Controller c;
	public InventoryController(Controller c) {
		this.c = c;
		
		c.getView().getDownTownView().setInventoryListener(this);
		c.getView().getShopView().setInventoryListener(this);
	}

	@Override
	public void handle(MouseEvent e) {
		
		Item item;
		List<Integer> indexList;
		if (c.getView().getWindow().getScene()==c.getView().getDownTownView().getDownTownScene()) {
			 indexList = c.getView().getDownTownView().getSelectedIndexListOfWeaponAndArmorTabs();
			for (int i = 0; i < indexList.size(); i++) {
				 c.getView().getShopView().getInventoryListViewObjects()
					.get(i).getSelectionModel().select(indexList.get(i));
				 item = c.getView().getDownTownView().getInventoryListViewObjects().get(i).getSelectionModel().getSelectedItem();
				 c.getPlayer().setEquippedItems(item);
			}

		} else if(c.getView().getWindow().getScene()==c.getView().getShopView().getShopScene()) {
			indexList = c.getView().getShopView().getSelectedIndexListOfWeaponAndArmorTabs();
			 for (int i = 0; i < indexList.size(); i++) {
				 c.getView().getDownTownView().getInventoryListViewObjects()
					.get(i).getSelectionModel().select(indexList.get(i));
				 item = c.getView().getShopView().getInventoryListViewObjects().get(i).getSelectionModel().getSelectedItem();
				 c.getPlayer().setEquippedItems(item);
			}
		}
		
		c.getView().getDownTownView().setPlayerStats(c.getPlayer());
		c.getView().getShopView().setPlayerStats(c.getPlayer());
	}
	
	public void setPlayerInventoryItemsForAllLocations() {
		c.getView().getDownTownView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		c.getView().getShopView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		
		c.getView().getDownTownView().getInventoryListViewObjects().forEach(o->o.getSelectionModel().select(0));
		c.getView().getShopView().getInventoryListViewObjects().forEach(o->o.getSelectionModel().select(0));
		
		c.getPlayer().setEquippedWeapon(((Weapon)c.getView().getDownTownView().getInventoryListViewObjects()
				.get(0).getSelectionModel().getSelectedItem()));
		c.getPlayer().setEquippedArmor(((Armor)c.getView().getDownTownView().getInventoryListViewObjects()
				.get(1).getSelectionModel().getSelectedItem()));

	}

}
