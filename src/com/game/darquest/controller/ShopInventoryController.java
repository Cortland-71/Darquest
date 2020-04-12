package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.items.Item;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ShopInventoryController implements EventHandler<MouseEvent> {
	
	private List<List<Item>> allItemsList = new ArrayList<>();

	private Controller c;
	public ShopInventoryController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addShopListViewListener(this);
		this.c.getView().getShopView().addShopTabListener(this);
		
		allItemsList.add(c.getItemHub().getAllWeapons());
		allItemsList.add(c.getItemHub().getAllArmors());
		allItemsList.add(c.getItemHub().getAllTools());
		this.c.getView().getShopView().setAllShopItems(allItemsList);
		
	}

	@Override
	public void handle(MouseEvent e) {
		setSelectedShopItemToTextArea();
	}
	
	public void setSelectedShopItemToTextArea() {
		int tabIndex = c.getView().getShopView().getShopTabPane().getSelectionModel().getSelectedIndex();
		Item itemSelected = c.getView().getShopView().getShopListViewObjects().get(tabIndex).getSelectionModel().getSelectedItem();
		c.getView().getShopView().setBuyShopDialogeTextArea(itemSelected.toString());
	}

}
