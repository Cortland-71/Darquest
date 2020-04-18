package com.game.darquest.controller;

import javafx.scene.control.SpinnerValueFactory;

public class LevelUpController {
	
	private Controller c;
	private SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 5, 3);

	public LevelUpController(Controller c) {
		this.c = c;
	}
	
	public SpinnerValueFactory<Integer> getFactory() {
		return this.valueFactory;
	}
	

}
