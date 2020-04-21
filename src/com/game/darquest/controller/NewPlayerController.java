package com.game.darquest.controller;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;
import com.game.darquest.view.View;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

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
			c.getPlayerInvStatsController().setNewPlayerInventoryAndStats();
			view.getWindow().setScene(view.getHubView().getDownTownScene());
			view.getHubView().showHub();
		}	
	}
	
	private void setPlayerAttributes() {
		c.getPlayer().setName(c.getView().getNewPlayerView().getNameText());
		
		c.getPlayer().setMaxDef(5);
		c.getPlayer().setMaxStealth(5);
		c.getPlayer().setMaxAwareness(5);
		
		c.getPlayer().setDef(5);
		c.getPlayer().setStealth(5);
		c.getPlayer().setAwareness(5);
		
		((Player)c.getPlayer()).addItemToPlayerInventory(new Tool("none","none", "none",0,0,10,10,0,0));
		for (int i = 0; i < 3; i++) {
			((Player)c.getPlayer()).addItemToPlayerInventory(new Tool("Strong Hp Vial", 
					c.getItemHub().hpVileDes(), "hp", 150, .02, 3, 3, 20, 50));
		}
		
		((Player)c.getPlayer()).addItemToPlayerInventory(new Weapon("none","none",0,0,10,10,5,6));
		((Player)c.getPlayer()).addItemToPlayerInventory(new Armor("none","none",0,0,10,10,0));

	}
}
