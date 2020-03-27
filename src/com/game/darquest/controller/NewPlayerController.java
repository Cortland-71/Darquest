package com.game.darquest.controller;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;
import com.game.darquest.view.View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewPlayerController implements EventHandler<KeyEvent> {

	private View view;
	private Controller c;
	public NewPlayerController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getNewPlayerView().addKeyListener(this);
	}

	@Override
	public void handle(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			setPlayerAttributes();
			c.getInventoryController().setPlayerInventoryItemsForAllLocations();
			setPlayerStatsInAllLocations();
			view.getWindow().setScene(view.getDownTownView().getDownTownScene());
			
		}	
	}
	
	
	
	private void setPlayerAttributes() {
		c.getPlayer().setName(c.getView().getNewPlayerView().getNameText());
		((Player)c.getPlayer()).addItemToPlayerInventory(new Weapon("none",0,0,10,1,5));
		((Player)c.getPlayer()).addItemToPlayerInventory(new Armor("none",0,0,10));
	}
	
	private void setPlayerStatsInAllLocations() {
		view.getDownTownView().setPlayerStats(c.getPlayer());
		view.getShopView().setPlayerStats(c.getPlayer());
	}

}
