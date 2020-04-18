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
		c.getView().getHubView().setInventoryListener(this);
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
		indexList = c.getView().getHubView().getSelectedIndexListOfAllTabs();
		updateAllPlayerInv();
		for (int i = 0; i < indexList.size(); i++) {
			c.getView().getHubView().getInventoryListViewObjects()
			.get(i).getSelectionModel().select(indexList.get(i));
			item = getSelectedItemFromTab(i);
			c.getPlayer().setEquippedItem(item);
		}
		updateAllPlayerStats();
	}
	
	public void deselectItemToNone() {
		int tabIndex = getSelectedTabIndex();
		c.getView().getHubView().getInventoryListViewObjects()
		.get(tabIndex).getSelectionModel().select(0);
	}
	
	
	
	public void updateAllPlayerStats() {
		c.getView().getHubView().setPlayerStats((Player)c.getPlayer());
	}
	
	public void setNewPlayerInventoryAndStats() {
		updateAllPlayerInv();
		c.getView().getHubView().getInventoryListViewObjects()
		.forEach(o->o.getSelectionModel().select(0));
		equipItem();
		updateAllPlayerStats();
	}

	public Item getSelectedItemFromInvOnCurrentTab() {
		int tabIndex = getSelectedTabIndex();
		Item item = getSelectedItemFromTab(tabIndex);
		return item;
	}

	public int getSelectedIndexOfItem() {
		int tabIndex = getSelectedTabIndex();
		return getSelectedItemIndex(tabIndex);
	}
	
	private void equipItem() {
		indexList = c.getView().getHubView().getSelectedIndexListOfAllTabs();
		for (int i = 0; i < indexList.size(); i++) {
			item = getSelectedItemFromTab(i);
			c.getPlayer().setEquippedItem(item);
		}
	}
	
	private int getSelectedTabIndex() {
		int tabIndex = c.getView().getHubView().getPlayerInventoryTabPane()
				.getSelectionModel().getSelectedIndex();
		return tabIndex;
	}
	
	private void updateAllPlayerInv() {
		c.getView().getHubView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
	}
	
	private Item getSelectedItemFromTab(int tabIndex) {
		return c.getView().getHubView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
	}
	
	private int getSelectedItemIndex(int tabIndex) {
		return c.getView().getHubView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedIndex();
	}
}
