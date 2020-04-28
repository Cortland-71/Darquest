package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;
import com.game.darquest.view.View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewPlayerController implements EventHandler<KeyEvent> {

	private View view;
	private Controller c;
	private Player p;
	public NewPlayerController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getNewPlayerView().addKeyListener(this);
		this.p = (Player)this.c.getPlayer();
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
		setNameAndStats();
		initChallengeList();

		//p.addItemToPlayerInventory(new Weapon("none","none",0,0,10,10,5,6));
		p.addItemToPlayerInventory(new Weapon("none","none",0,0,10,10,5,6));
		p.addItemToPlayerInventory(new Armor("none","none",0,0,10,10,0));
		p.addItemToPlayerInventory(new Tool("none","none", "none",0,0,10,10,0,0));
		for (int i = 0; i < 3; i++) {
			p.addItemToPlayerInventory(new Tool("Strong Hp Vial", 
					c.getItemHub().hpVileDes(), "hp", 150, .02, 3, 3, 20, 50));
		}

	}
	
	private void setNameAndStats() {
		p.setName(c.getView().getNewPlayerView().getNameText());
		
		p.setMaxAttack(5);
		p.setMaxDef(5);
		p.setMaxStealth(5);
		p.setMaxAwareness(5);
		p.setMaxMutation(5);
		p.setMaxSecurity(5);
		
		p.setAttack(5);
		p.setDef(5);
		p.setStealth(5);
		p.setAwareness(5);
		p.setMutation(5);
		p.setSecurity(5);
	}
	
	private void initChallengeList() {
		List<Boolean> initChallengeList = new ArrayList<>();
		for (int i = 0; i < c.getChallengeController().getChallengeCheckListSize(); i++) {
			initChallengeList.add(false);
		}
		p.setChallengeBools(initChallengeList);
	}
}
