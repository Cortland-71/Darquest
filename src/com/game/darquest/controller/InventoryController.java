package com.game.darquest.controller;

import java.util.ArrayList;
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
				 c.getPlayer().setEquippedItem(item);
			}

		} else if(c.getView().getWindow().getScene()==c.getView().getShopView().getShopScene()) {
			indexList = c.getView().getShopView().getSelectedIndexListOfWeaponAndArmorTabs();
			 for (int i = 0; i < indexList.size(); i++) {
				 c.getView().getDownTownView().getInventoryListViewObjects()
					.get(i).getSelectionModel().select(indexList.get(i));
				 item = c.getView().getShopView().getInventoryListViewObjects().get(i).getSelectionModel().getSelectedItem();
				 c.getPlayer().setEquippedItem(item);
			}
		}
		setPlayerStatsForAllLocations();
	}
	
	public void setPlayerInventoryItemsForAllLocations() {
		c.getView().getDownTownView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		c.getView().getShopView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
	}
	
	public void setPlayerInventoryToFirstItem() {
		c.getView().getDownTownView().getInventoryListViewObjects().forEach(o->o.getSelectionModel().select(0));
		c.getView().getShopView().getInventoryListViewObjects().forEach(o->o.getSelectionModel().select(0));
	}
	
	public void equipNoneItemWhenSelling() {
		setPlayerInventoryToFirstItemOnTab();
		c.getPlayer().setEquippedItem(((Weapon)c.getView().getShopView().getInventoryListViewObjects()
				.get(0).getSelectionModel().getSelectedItem()));
		c.getPlayer().setEquippedItem(((Armor)c.getView().getShopView().getInventoryListViewObjects()
				.get(1).getSelectionModel().getSelectedItem()));
	}
	private void setPlayerInventoryToFirstItemOnTab() {
		int tabIndex = c.getView().getShopView().getInventoryTabPane().getSelectionModel().getSelectedIndex();
		c.getView().getShopView().getInventoryListViewObjects().get(tabIndex).getSelectionModel().select(0);
	}
	
	public void equipEnventoryItemsAtDownTown() {
		c.getPlayer().setEquippedItem(((Weapon)c.getView().getDownTownView().getInventoryListViewObjects()
				.get(0).getSelectionModel().getSelectedItem()));
		c.getPlayer().setEquippedItem(((Armor)c.getView().getDownTownView().getInventoryListViewObjects()
				.get(1).getSelectionModel().getSelectedItem()));
	}
	
	public void setPlayerStatsForAllLocations() {
		c.getView().getDownTownView().setPlayerStats(c.getPlayer());
		c.getView().getShopView().setPlayerStats(c.getPlayer());
	}
	
	public void setSelectedInventoryItemsForAllTabs(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			c.getView().getShopView().getInventoryListViewObjects().get(i).getSelectionModel().select(list.get(i));
		}
	}
	
	public List<Integer> getSelectedInventoryIndexForAllTabs() {
		List<Integer> list = new ArrayList<>();
		c.getView().getShopView().getInventoryListViewObjects().forEach(e->list.add(e.getSelectionModel().getSelectedIndex()));
		return list;
	}

}
