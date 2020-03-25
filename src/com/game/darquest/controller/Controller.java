package com.game.darquest.controller;

import com.game.darquest.view.View;

public class Controller {
	
	private MainMenuController mainMenuController;
	private TutorialController tutorialController;
	private NewPlayerController newPlayerController;
	private DownTownController downTownController;
	private ShopController shopController;
	private View view;

	public Controller(View view) {
		this.view = view;
		this.mainMenuController = new MainMenuController(this);
		this.tutorialController = new TutorialController(this);
		this.newPlayerController = new NewPlayerController(this);
		this.downTownController = new DownTownController(this);
		this.shopController = new ShopController(this);
	}

	
	public ShopController getShopController() {
		return shopController;
	}


	public MainMenuController getMainMenuController() {
		return mainMenuController;
	}

	public TutorialController getTutorialController() {
		return tutorialController;
	}

	public NewPlayerController getNewPlayerController() {
		return newPlayerController;
	}

	public DownTownController getDownTownController() {
		return downTownController;
	}
	
	public View getView() {
		return this.view;
	}
	
}
