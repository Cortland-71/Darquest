package com.game.darquest.controller;

import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PlayerInvStatsController implements EventHandler<MouseEvent> {
	
	private Item item;
	private List<Integer> indexList;

	private Controller c;
	public PlayerInvStatsController(Controller c) {
		this.c = c;
		c.getView().getDownTownView().setInventoryListener(this);
		c.getView().getFightClubView().setInventoryListener(this);
	}

	@Override
	public void handle(MouseEvent e) {
		equipItem();
		updateAllPlayerStats();
	}
	
	
	
//	public void removeItemWhenUsedUp(Item item) {
//		if(item.getCondition() < 1) {
//			int tab = ((Player)c.getPlayer()).removeItemFromPlayerInventory(item, 
//					getSelectedItemIndexFight());
//			setNoneSelectedFight(tab);
//		}
//	}
	
	public void captureSelectedItemsUpdateInvReEquipItems() {
		indexList = c.getView().getDownTownView().getSelectedIndexListOfAllTabs();
		updateAllPlayerInv();
		for (int i = 0; i < indexList.size(); i++) {
			item = getSelectedItemFromTab(i);
			c.getView().getDownTownView().getInventoryListViewObjects()
			.get(i).getSelectionModel().select(indexList.get(i));
			c.getPlayer().setEquippedItem(item);
		}
		updateAllPlayerStats();
	}
	
	public void updateAllPlayerInv() {
		c.getView().getDownTownView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
	}
	
	private Item getSelectedItemFromTab(int tabIndex) {
		return c.getView().getDownTownView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
	}
	
	public void updateAllPlayerStats() {
		c.getView().getDownTownView().setPlayerStats((Player)c.getPlayer());
	}
	
	public void setNewPlayerInventoryAndStats() {
		updateAllPlayerInv();
		c.getView().getDownTownView().getInventoryListViewObjects()
		.forEach(o->o.getSelectionModel().select(0));
		equipItem();
		updateAllPlayerStats();
	}
	
	public void equipItem() {
		indexList = c.getView().getDownTownView().getSelectedIndexListOfAllTabs();
		for (int i = 0; i < indexList.size(); i++) {
			item = getSelectedItemFromTab(i);
			c.getPlayer().setEquippedItem(item);
		}
	}
}
