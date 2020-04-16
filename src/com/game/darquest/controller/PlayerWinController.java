package com.game.darquest.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlayerWinController implements EventHandler<ActionEvent> {

	private Controller c;
	public PlayerWinController(Controller c) {
		this.c = c;
		c.getView().getPlayerWinView().addContinueButtonListener(this);
	}

	@Override
	public void handle(ActionEvent e) {
		
		c.getView().getWindow().setScene(c.getView().getDownTownView().getDownTownScene());
	}
}
