package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Weapon;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PlayerInventoryAndStatsController implements EventHandler<MouseEvent> {
	
	private Item item;
	private List<Integer> indexList;

	private Controller c;
	public PlayerInventoryAndStatsController(Controller c) {
		this.c = c;
		c.getView().getDownTownView().setInventoryListener(this);
		c.getView().getShopView().setInventoryListener(this);
		c.getView().getFightClubView().setInventoryListener(this);
	}

	@Override
	public void handle(MouseEvent e) {
		if (c.getView().getWindow().getScene()==c.getView().getDownTownView().getDownTownScene()) {
			equipSelectedInventoryItemsForDownTown();
		} else if(c.getView().getWindow().getScene()==c.getView().getShopView().getShopScene()) {
			equipSelectedInventoryItemsForShop();
		} else if(c.getView().getWindow().getScene()==c.getView().getFightClubView().getFightClubScene()) {
			equipSelectedInventoryItemsForFightClub();
		}
		setPlayerStatsForAllLocations();
	}
	
	////////////////////////////////////////////////////////////////// -Mouse event logic
	private void equipSelectedInventoryItemsForDownTown() {
		indexList = c.getView().getDownTownView().getSelectedIndexListOfWeaponAndArmorTabs();
		for (int i = 0; i < indexList.size(); i++) {
			selectCorrectInventoryItemInShopView(i);
			selectCorrectInventoryItemInFightClubView(i);
			item = c.getView().getDownTownView().getInventoryListViewObjects().get(i).getSelectionModel().getSelectedItem();
			c.getPlayer().setEquippedItem(item);
		}
	}
	
	private void equipSelectedInventoryItemsForShop() {
		 indexList = c.getView().getShopView().getSelectedIndexListOfWeaponAndArmorTabs();
		 for (int i = 0; i < indexList.size(); i++) {
			 selectCorrectInventoryItemInDownTownView(i);
			 selectCorrectInventoryItemInFightClubView(i);
			 item = c.getView().getShopView().getInventoryListViewObjects().get(i).getSelectionModel().getSelectedItem();
			 c.getPlayer().setEquippedItem(item);
		}
	}
	
	private void equipSelectedInventoryItemsForFightClub() {
		 indexList = c.getView().getFightClubView().getSelectedIndexListOfWeaponAndArmorTabs();
		 for (int i = 0; i < indexList.size(); i++) {
			 selectCorrectInventoryItemInDownTownView(i);
			 selectCorrectInventoryItemInShopView(i);
			 item = c.getView().getFightClubView().getInventoryListViewObjects().get(i).getSelectionModel().getSelectedItem();
			 c.getPlayer().setEquippedItem(item);
		}
	}
	
	
	private void selectCorrectInventoryItemInDownTownView(int i) {
		 c.getView().getDownTownView().getInventoryListViewObjects()
			.get(i).getSelectionModel().select(indexList.get(i));
	}
	
	private void selectCorrectInventoryItemInShopView(int i) {
		c.getView().getShopView().getInventoryListViewObjects()
		.get(i).getSelectionModel().select(indexList.get(i));
	}
	
	private void selectCorrectInventoryItemInFightClubView(int i) {
		c.getView().getFightClubView().getInventoryListViewObjects()
		.get(i).getSelectionModel().select(indexList.get(i));
	}
	
	
	
	///////////////////////////////////////////////////////////////////////- API
	
	public void setNewPlayerInventoryAndStats() {
		updatePlayerInventoryForAllLocations();
		c.getView().getDownTownView().getInventoryListViewObjects()
		.forEach(o->o.getSelectionModel().select(0));
		equipSelectedInventoryItemsForDownTown();
		setPlayerStatsForAllLocations();
	}
	
	public void setPlayerInventoryAndStatsForBuyItem() {
		List<Integer> list = getSelectedInventoryItemIndexForAllTabs();
		updatePlayerInventoryForAllLocations();
		setSelectedInventoryItemsForAllTabs(list);
	}
	
	public void setPlayerInventoryAndStatsForSellItem() {
		setFirstItemOnSelectedTab();
		List<Integer> list = getSelectedInventoryItemIndexForAllTabs();
		updatePlayerInventoryForAllLocations();
		setSelectedInventoryItemsForAllTabs(list);
		equipSelectedInventoryItemsForShop();
		setPlayerStatsForAllLocations();
	}
	
	/////////////////////////////////////////////////////////////////// - helper
	private void setPlayerStatsForAllLocations() {
		c.getView().getShopView().setPlayerStats(c.getPlayer());
		c.getView().getDownTownView().setPlayerStats(c.getPlayer());
		c.getView().getFightClubView().setPlayerStats(c.getPlayer());
	}
	
	private void updatePlayerInventoryForAllLocations() {
		c.getView().getShopView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		c.getView().getDownTownView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		c.getView().getFightClubView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
	}
	
	private void setSelectedInventoryItemsForAllTabs(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			c.getView().getShopView().getInventoryListViewObjects().get(i).getSelectionModel().select(list.get(i));
			c.getView().getDownTownView().getInventoryListViewObjects().get(i).getSelectionModel().select(list.get(i));
			c.getView().getFightClubView().getInventoryListViewObjects().get(i).getSelectionModel().select(list.get(i));
		}
	}
	
	private List<Integer> getSelectedInventoryItemIndexForAllTabs() {
		List<Integer> list = new ArrayList<>();
		c.getView().getShopView().getInventoryListViewObjects().forEach(e->list.add(e.getSelectionModel().getSelectedIndex()));
		return list;
	}
	
	
	private void setFirstItemOnSelectedTab() {
		int tabIndex = c.getView().getShopView().getInventoryTabPane().getSelectionModel().getSelectedIndex();
		c.getView().getShopView().getInventoryListViewObjects().get(tabIndex).getSelectionModel().select(0);
	}
}
