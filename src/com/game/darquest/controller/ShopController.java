package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ShopController implements EventHandler<ActionEvent>{
	
	private Controller c;
	
	private Player player;
	
	private List<String> odetteBuyDialogue = Arrays.asList("Nice choice.", "That should help.", 
			"That's one of my most popular items.", "I like your style.", "Carful with that.", "One of my favorites.",
			"I should get one of these myself.", "I rebuilt this one from scratch.", "Well worth the price.");
	
	private List<String> odetteSellDialogue = Arrays.asList("This is great, thanks.", "I've been looking for one of these...",
			"Pleasure doing business.", "I already have a hundred of these but I'll take it.", "Sure you don't want this? Okay.");
	
	private Random rand = new Random();
	public ShopController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addShopActionListener(this);
	}
	

	@Override
	public void handle(ActionEvent e) {
		if(((Button)e.getSource()).getId().equals("buy")) {
			buyItem();
			return;
		} else if(((Button)e.getSource()).getId().equals("sell")) {
			sellItem();
			return;
		} else if(((Button)e.getSource()).getId().equals("back")) {
			goBackToDownTown();
			return;
		}
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		c.getHubController().getDownTownActions().get(eventIndex).clickAction();
		
	}
	 
	private void sellItem() {
		player = (Player)c.getPlayer();
		Item item = c.getPlayerInvStatsController().getSelectedItemFromInvOnCurrentTab();
		if(item.getName() != "none") {
			player.setCash(player.getCash()+item.getValue());
			int itemIndex = c.getPlayerInvStatsController().getSelectedIndexOfItem();
			player.removeItemFromPlayerInventory(item, itemIndex);
			c.getPlayerInvStatsController().deselectItemToNone();
			c.getPlayerInvStatsController().captureSelectedItemsUpdateInvReEquipItems();
			shopSellDialogueOutput(item);
		}
	}

	private void buyItem() {
		player = (Player)c.getPlayer();
		Item selectedItem = getSelectedItemShopInventory();
		if(player.getCash() >= selectedItem.getPrice()) {
			player.setCash(player.getCash()-selectedItem.getPrice());
			player.addItemToPlayerInventory(selectedItem);
			c.getPlayerInvStatsController().captureSelectedItemsUpdateInvReEquipItems();
			shopBuyDialogueOutput(selectedItem);
			return;
		}
		c.getView().getShopView().setBuyShopDialogeRed();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: Sorry "+c.getPlayer().getName()+
				", you don't have enough cash for this...");
	}
	
	private Item getSelectedItemShopInventory() {
		int tabIndex = c.getView().getShopView().getShopTabPane()
				.getSelectionModel().getSelectedIndex();
		Item selectedItem = c.getView().getShopView().getShopListViewObjects()
		.get(tabIndex).getSelectionModel().getSelectedItem();
		return selectedItem;
	}
	
	private void shopBuyDialogueOutput(Item item) {
		c.getView().getShopView().setBuyShopDialogeNormal();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: "+odetteBuyDialogue.get(rand.nextInt(odetteBuyDialogue.size()))+
				"\n\nItem bought: "+item.getName()+"\nCost: -"+item.getPriceFormatted()+"\nWeight: +"+item.getWeight());
	}
	
	private void shopSellDialogueOutput(Item item) {
		c.getView().getShopView().setBuyShopDialogeNormal();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: "+odetteSellDialogue.get(rand.nextInt(odetteSellDialogue.size()))+
				"\n\nItem Sold: "+item.getName()+"\nValue: -"+item.getValueFormatted()+"\nWeight: +"+item.getWeight());
	}
	
	private void goBackToDownTown() {
		 
		c.getView().getHubView().showHub();
	}
}
