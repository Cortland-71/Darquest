package com.game.darquest.controller;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.view.View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewPlayerController implements EventHandler<KeyEvent> {

	private View view;
	private Person player;
	private Controller c;
	public NewPlayerController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getNewPlayerView().addKeyListener(this);
	}

	@Override
	public void handle(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			player = new Player(view.getNewPlayerView().getNameText());
			view.getWindow().setScene(view.getDownTownView().getDownTownScene());
			System.out.println(player);
			c.getDownTownController().setPlayer(player);
		}	
	}
	
	
	public Person getPlayer() {
		return this.player;
	}

}
