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

public class ShopTransactionController implements EventHandler<ActionEvent>{
	
	private Controller c;
	
	private Player player;
	
	private List<String> odetteBuyDialogue = Arrays.asList("Nice choice.", "That should help.", 
			"That's one of my most popular items.", "I like your style.", "Carful with that.", "One of my favorites.",
			"I should get one of these myself.", "I rebuilt this one from scratch.", "Well worth the price.");
	
	private Random rand = new Random();
	public ShopTransactionController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addActionListener(this);

		this.c.getView().getShopView().addBuyButtonListener(this);
		
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
			c.getPlayerInventoryAndStatsController().setPlayerInventoryAndStatsForSellItem();
			shopSellDialogueOutput(selectedItem);
		}
	}

	private void buyItem() {
		player = (Player)c.getPlayer();
		Item selectedItem = getBoughtItem();
		if(player.getCash() >= selectedItem.getPrice()) {
			player.setCash(player.getCash()-selectedItem.getPrice());
			player.setWeight(player.getWeight()+selectedItem.getWeight());
			player.addItemToPlayerInventory(selectedItem);
			c.getPlayerInventoryAndStatsController().setPlayerInventoryAndStatsForBuyItem();
			shopBuyDialogueOutput(selectedItem);
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
	
	private void shopBuyDialogueOutput(Item item) {
		c.getView().getShopView().setBuyShopDialogeNormal();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: "+odetteBuyDialogue.get(rand.nextInt(odetteBuyDialogue.size()))+
				"\n\tItem bought: "+item.getName()+"\n\tCost: -"+item.getPriceFormatted()+"\n\tWeight: +"+item.getWeight());
	}
	
	private void shopSellDialogueOutput(Item item) {
		c.getView().getShopView().setSellShopDialogeNormal();
		c.getView().getShopView().setSellShopDialogeTextArea("Item sold: "+item.getName()+
				"\n\tValue: +"+item.getValueFormatted()+"\n\tWeight: -"+item.getWeight());
	}
}
