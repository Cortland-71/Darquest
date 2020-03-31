package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ShopInventoryController implements EventHandler<MouseEvent> {
	
	private List<List<Item>> allItemsList = new ArrayList<>();

	private Controller c;
	public ShopInventoryController(Controller c) {
		this.c = c;
		this.c.getView().getShopView().addShopListViewListener(this);
		this.c.getView().getShopView().addShopTabListener(this);
		
		allItemsList.add(c.getItemController().getAllWeapons());
		allItemsList.add(c.getItemController().getAllArmors());
		allItemsList.add(c.getItemController().getAllTools());
		this.c.getView().getShopView().setAllShopItems(allItemsList);
		
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
