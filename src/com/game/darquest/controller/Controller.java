package com.game.darquest.controller;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.view.View;

public class Controller {
	
	private final MainMenuController mainMenuController;
	private final TutorialController tutorialController;
	private final NewPlayerController newPlayerController;
	private final DownTownController downTownController;
	private final ShopController shopController;
	private final PlayerInvStatsController playerInvStatsController;
	private final ShopInventoryController shopInventoryController;
	private final FightClubController fightClubController;
	private final PlayerWinController playerWinController;
	private final ItemHub itemHub;
	private final EnemyController enemyController;
	private final View view;
	private Person player;
	
	

	public Controller(View view) {
		this.player = new Player();
		this.view = view;
		this.mainMenuController = new MainMenuController(this);
		this.tutorialController = new TutorialController(this);
		this.newPlayerController = new NewPlayerController(this);
		this.downTownController = new DownTownController(this);
		this.shopController = new ShopController(this);
		this.playerInvStatsController = new PlayerInvStatsController(this);
		this.playerWinController = new PlayerWinController(this);
		this.itemHub = new ItemHub();
		this.shopInventoryController = new ShopInventoryController(this);
		this.fightClubController = new FightClubController(this);
		this.enemyController = new EnemyController(this);
		
	}

	
	public PlayerWinController getPlayerWinController() {
		return playerWinController;
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
	
	public Person getPlayer( ) {
		return this.player;
	}


	public PlayerInvStatsController getPlayerInvStatsController() {
		return playerInvStatsController;
	}


	public ShopInventoryController getShopInventoryController() {
		return shopInventoryController;
	}


	public FightClubController getFightClubController() {
		return fightClubController;
	}


	public ItemHub getItemHub() {
		return itemHub;
	}


	public EnemyController getEnemyController() {
		return enemyController;
	}
	
}
