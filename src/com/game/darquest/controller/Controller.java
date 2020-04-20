package com.game.darquest.controller;

import com.game.darquest.controller.fightClubControllers.FightController;
import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.controller.fightClubControllers.FightClubHubController;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.view.View;

public class Controller {
	
	private final MainMenuController mainMenuController;
	private final TutorialController tutorialController;
	private final NewPlayerController newPlayerController;
	private final HubController hubController;
	private final ShopController shopController;
	private final PlayerInvStatsController playerInvStatsController;
	private final ShopInventoryController shopInventoryController;
	private final FightClubHubController fightClubHubController;
	private final FightController fightClubController;
	private final FightClubWinController playerWinController;
	private final LevelUpController levelUpController;
	
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
		this.hubController = new HubController(this);
		this.shopController = new ShopController(this);
		this.playerInvStatsController = new PlayerInvStatsController(this);
		this.playerWinController = new FightClubWinController(this);
		this.itemHub = new ItemHub();
		this.shopInventoryController = new ShopInventoryController(this);
		this.fightClubHubController = new FightClubHubController(this);
		this.fightClubController = new FightController(this);
		this.enemyController = new EnemyController(this);
		this.levelUpController = new LevelUpController(this);
		
	}

	public FightClubWinController getPlayerWinController() {
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

	public HubController getHubController() {
		return hubController;
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


	public FightController getFightClubController() {
		return fightClubController;
	}


	public ItemHub getItemHub() {
		return itemHub;
	}


	public EnemyController getEnemyController() {
		return enemyController;
	}


	public LevelUpController getLevelUpController() {
		return levelUpController;
	}

	public FightClubHubController getFightClubHubController() {
		return fightClubHubController;
	}
	
}
