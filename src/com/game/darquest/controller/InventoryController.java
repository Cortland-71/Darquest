package com.game.darquest.controller;

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
		if (c.getView().getWindow().getScene()==c.getView().getDownTownView().getDownTownScene()) {
			int downTownIndex = c.getView().getDownTownView().getSelectedIndex();
			c.getView().getShopView().getInventoryListViewObjects().get(0).getSelectionModel().select(downTownIndex);
			return;
		}
		int shopIndex = c.getView().getShopView().getSelectedIndex();
		c.getView().getDownTownView().getInventoryListViewObjects().get(0).getSelectionModel().select(shopIndex);
	}

}
