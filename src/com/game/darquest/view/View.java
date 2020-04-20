package com.game.darquest.view;

import com.game.darquest.view.fightClub.FightClubHubView;
import com.game.darquest.view.fightClub.FightView;
import com.game.darquest.view.fightClub.FightClubWinView;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View {
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final int DEF_PAD = 15;
	private Stage window;

	
	private MainMenuView mainMenuView;
	private NewPlayerView newPlayerView;
	private HubView hubView;
	private TutorialView tutorialView;
	private ShopView shopView;
	private FightClubHubView fightClubHub;
	private FightView fightClubView;
	private FightClubWinView playerWinView;
	private LevelUpView levelUpView;
	
	public View(Stage primaryStage) {
		this.window = primaryStage;
		this.mainMenuView = new MainMenuView();
		this.newPlayerView = new NewPlayerView();
		this.hubView = new HubView();
		this.hubView.setView(this);
		this.tutorialView = new TutorialView();
		this.shopView = new ShopView();
		this.fightClubHub = new FightClubHubView();
		this.fightClubView = new FightView();
		this.playerWinView = new FightClubWinView();
		this.levelUpView = new LevelUpView();
		
		
		FadeTransition ft2 = new FadeTransition(Duration.millis(3000), getMainMenu().getTitle());
		ft2.setFromValue(0.0);
		ft2.setToValue(1.0);
		ft2.play();
		
		window.setScene(mainMenuView.getMainMenuScene());
		window.setTitle("Darquest FX v1.0");
		window.show();
		
	}
	
	public FightClubWinView getPlayerWinView() {
		return playerWinView;
	}

	public void setFightClubView(FightView fightClubView) {
		this.fightClubView = fightClubView;
	}
	
	public ShopView getShopView() {
		return this.shopView;
	}
	
	public NewPlayerView getNewPlayerView() {
		return this.newPlayerView;
	}
	
	public TutorialView getTutorialView() {
		return this.tutorialView;
	}
	
	public HubView getHubView() {
		return this.hubView;
	}
	
	public MainMenuView getMainMenu() {
		return this.mainMenuView;
	}

	public FightView getFightClubView() {
		return fightClubView;
	}
	
	public Stage getWindow() {
		return window;
	}
	
	public static Background getBackground(Color color) {
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}
	
	private static Button button;
	public static Button getButton(String name, String id) {
		button = new Button(name);
		button.setId(id);
		button.setMinSize(225, 50);
		return button;
	}

	public FightClubWinView getFightWinView() {
		return playerWinView;
	}

	public LevelUpView getLevelUpView() {
		return levelUpView;
	}

	public FightClubHubView getFightClubHub() {
		return fightClubHub;
	}
}