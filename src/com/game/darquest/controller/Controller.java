package com.game.darquest.controller;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.view.View;

public class Controller {
	
	private MainMenuController mainMenuController;
	private TutorialController tutorialController;
	private NewPlayerController newPlayerController;
	private DownTownController downTownController;
	private ShopTransactionController shopController;
	private PlayerInventoryAndStatsController playerInventoryAndStatsController;
	private ShopInventoryController shopInventoryController;
	private FightClubController fightClubController;
	private ItemController itemController;
	private View view;
	private Person player;
	

	public Controller(View view) {
		this.player = new Player();
		this.view = view;
		this.mainMenuController = new MainMenuController(this);
		this.tutorialController = new TutorialController(this);
		this.newPlayerController = new NewPlayerController(this);
		this.downTownController = new DownTownController(this);
		this.shopController = new ShopTransactionController(this);
		this.playerInventoryAndStatsController = new PlayerInventoryAndStatsController(this);
		this.itemController = new ItemController();
		this.shopInventoryController = new ShopInventoryController(this);
		this.fightClubController = new FightClubController(this);
		
	}

	
	public ShopTransactionController getShopController() {
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
	
	public Person getPlayer( ) {
		return this.player;
	}


	public PlayerInventoryAndStatsController getPlayerInventoryAndStatsController() {
		return playerInventoryAndStatsController;
	}


	public ShopInventoryController getShopInventoryController() {
		return shopInventoryController;
	}


	public FightClubController getFightClubController() {
		return fightClubController;
	}


	public ItemController getItemController() {
		return itemController;
	}
	
	
	
}
