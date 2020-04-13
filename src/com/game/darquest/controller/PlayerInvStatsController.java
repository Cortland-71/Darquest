package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class PlayerInvStatsController implements EventHandler<MouseEvent> {
	
	private Item item;
	private List<Integer> indexList;

	private Controller c;
	public PlayerInvStatsController(Controller c) {
		this.c = c;
		c.getView().getDownTownView().setInventoryListener(this);
		c.getView().getShopView().setInventoryListener(this);
		c.getView().getFightClubView().setInventoryListener(this);
	}

	@Override
	public void handle(MouseEvent e) {
		Scene scene = c.getView().getWindow().getScene();
		if (scene==c.getView().getDownTownView().getDownTownScene()) {
			equipItemFromDownTownToAllLoc();
		} else if(scene==c.getView().getShopView().getShopScene()) {
			equipItemFromShopToAllLoc();
		} else if(scene==c.getView().getFightClubView().getFightClubScene()) {
			equipItemFromFightClubToAllLoc();
		}
		updateAllPlayerStats();
	}
	
	////////////////////////////////////////////////////////////////// -Equip logic
	private void equipItemFromDownTownToAllLoc() {
		indexList = c.getView().getDownTownView().getSelectedIndexListOfAllTabs();
		for (int i = 0; i < indexList.size(); i++) {
			selectCorrectInventoryItemInShopView(i);
			selectCorrectInventoryItemInFightClubView(i);
			item = getSelectedItemDownTown(i);
			c.getPlayer().setEquippedItem(item);
		}
	}
	
	private void equipItemFromShopToAllLoc() {
		 indexList = c.getView().getShopView().getSelectedIndexListOfAllTabs();
		 for (int i = 0; i < indexList.size(); i++) {
			 selectCorrectInventoryItemInDownTownView(i);
			 selectCorrectInventoryItemInFightClubView(i);
			 item = getSelectedItemShop(i);
			 c.getPlayer().setEquippedItem(item);
		}
	}
	
	private void equipItemFromFightClubToAllLoc() {
		 indexList = c.getView().getFightClubView().getSelectedIndexListOfAllTabs();
		 for (int i = 0; i < indexList.size(); i++) {
			 selectCorrectInventoryItemInDownTownView(i);
			 selectCorrectInventoryItemInShopView(i);
			 item = getSelectedItemFight(i);
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
	
	///////////////////////////////////////////////
	public void removeItemWhenUsedUp(Item item) {
		if(item.getCondition() < 1) {
			int tab = ((Player)c.getPlayer()).removeItemFromPlayerInventory(item, 
					getSelectedItemIndexFight());
			setNoneSelectedFight(tab);
		}
		refreshAllInvFight();
	}
	
	public void setNewPlayerInventoryAndStats() {
		updateAllPlayerInv();
		c.getView().getDownTownView().getInventoryListViewObjects()
		.forEach(o->o.getSelectionModel().select(0));
		equipItemFromDownTownToAllLoc();
		updateAllPlayerStats();
	}
	
	public void setPlayerInventoryAndStatsForBuyItem() {
		refreshAllInvShop();
	}
	
	public void setPlayerInventoryAndStatsForSellItem() {
		setNoneSelectedShop();
		refreshAllInvShop();
		equipItemFromShopToAllLoc();
	}
	
	private void refreshAllInvShop() {
		List<Integer> list = getAllSelectedIndexListShop();
		updateAllPlayerInv();
		updateAllSelectionsPlayerInv(list);
		updateAllPlayerStats();
	}
	
	///////////////////////////////////////////////////////////////////////- API
	public void updateAllPlayerStats() {
		c.getView().getShopView().setPlayerStats((Player)c.getPlayer());
		c.getView().getDownTownView().setPlayerStats((Player)c.getPlayer());
		c.getView().getFightClubView().setPlayerStats((Player)c.getPlayer());
	}
	
	private void updateAllPlayerInv() {
		c.getView().getShopView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		c.getView().getDownTownView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
		c.getView().getFightClubView().setAllInventoryItems(((Player)c.getPlayer()).getInventoryLists());
	}
	
	private void updateAllSelectionsPlayerInv(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			c.getView().getShopView().getInventoryListViewObjects()
			.get(i).getSelectionModel().select(list.get(i));
			c.getView().getDownTownView().getInventoryListViewObjects()
			.get(i).getSelectionModel().select(list.get(i));
			c.getView().getFightClubView().getInventoryListViewObjects()
			.get(i).getSelectionModel().select(list.get(i));
		}
	}
	
	// From player inventory to down town
	private Item getSelectedItemDownTown(int tabIndex) {
		return c.getView().getDownTownView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
	}
	
	// From player inventory to fight
	private int getSelectedTabIndexFight() {
		return c.getView().getFightClubView().getPlayerInventoryTabPane()
				.getSelectionModel().getSelectedIndex();
	}
	
	private int getSelectedItemIndexFight() {
		int tabIndex = getSelectedTabIndexFight();
		return c.getView().getFightClubView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedIndex();
	}
	
	private Item getSelectedItemFight(int tabIndex) {
		return c.getView().getFightClubView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
	}
	
	private void setNoneSelectedFight(int tabIndex) {
		c.getView().getFightClubView().getInventoryListViewObjects()
		.get(tabIndex).getSelectionModel().select(0);
	}
	
	private List<Integer> getAllSelectedIndexListFight() {
		List<Integer> list = new ArrayList<>();
		c.getView().getFightClubView().getInventoryListViewObjects().forEach(e->
		list.add(e.getSelectionModel().getSelectedIndex()));
		return list;
	}
	
	// From player inventory to shop
	private int getSelectedTabIndexShop() {
		return c.getView().getShopView().getPlayerInventoryTabPane()
				.getSelectionModel().getSelectedIndex();
	}
	
	public int getSelectedItemIndexShop() {
		int tabIndex = getSelectedTabIndexShop();
		return c.getView().getShopView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedIndex();
	}
	
	private Item getSelectedItemShop(int tabIndex) {
		return c.getView().getShopView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
	}
	
	public Item getSelectedItemShop() {
		int tabIndex = getSelectedTabIndexShop();
		return c.getView().getShopView().getInventoryListViewObjects()
				.get(tabIndex).getSelectionModel().getSelectedItem();
	}
	
	private void setNoneSelectedShop() {
		int tabIndex = getSelectedTabIndexShop();
		c.getView().getShopView().getInventoryListViewObjects()
		.get(tabIndex).getSelectionModel().select(0);
	}
	
	private List<Integer> getAllSelectedIndexListShop() {
		List<Integer> list = new ArrayList<>();
		c.getView().getShopView().getInventoryListViewObjects().forEach(e->
		list.add(e.getSelectionModel().getSelectedIndex()));
		return list;
	}
	
	public void refreshAllInvFight() {
		List<Integer> list = getAllSelectedIndexListFight();
		updateAllPlayerInv();
		updateAllSelectionsPlayerInv(list);
		equipItemFromFightClubToAllLoc();
	}
}
