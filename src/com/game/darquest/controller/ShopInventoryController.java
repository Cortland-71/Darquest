package com.game.darquest.controller;

import com.game.darquest.data.items.Item;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ShopInventoryController implements EventHandler<MouseEvent> {

	private Controller c;
	public ShopInventoryController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addShopListViewListener(this);
		this.c.getView().getShopView().addShopTabListener(this);
		
	}

	@Override
	public void handle(MouseEvent e) {
		setSelectedShopItemToLabel();
	}
	
	public void setSelectedShopItemToLabel() {
		int tabIndex = c.getView().getShopView().getShopTabPane().getSelectionModel().getSelectedIndex();
		Item itemSelected = c.getView().getShopView().getShopListViewObjects().get(tabIndex).getSelectionModel().getSelectedItem();
		c.getView().getShopView().setSelectedItemInfoLabel(itemSelected.toString());
	}

}
