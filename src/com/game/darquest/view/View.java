package com.game.darquest.view;

import com.game.darquest.Driver;

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
	private DownTownView downTownView;
	private TutorialView tutorialView;
	private ShopView shopView;
	private FightClubView fightClubView;
	private PlayerWinView playerWinView;
	
	public View(Stage primaryStage) {
		this.window = primaryStage;
		this.mainMenuView = new MainMenuView();
		this.newPlayerView = new NewPlayerView();
		this.downTownView = new DownTownView();
		this.tutorialView = new TutorialView();
		this.shopView = new ShopView();
		this.fightClubView = new FightClubView();
		this.playerWinView = new PlayerWinView();
		
		FadeTransition ft2 = new FadeTransition(Duration.millis(3000), getMainMenu().getTitle());
		ft2.setFromValue(0.0);
		ft2.setToValue(1.0);
		ft2.play();
		
		
		
		window.setScene(mainMenuView.getMainMenuScene());
		window.setTitle("Darquest FX v1.0");
		window.show();
		
	}
	
	public PlayerWinView getPlayerWinView() {
		return playerWinView;
	}

	public void setFightClubView(FightClubView fightClubView) {
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
	
	public DownTownView getDownTownView() {
		return this.downTownView;
	}
	
	public MainMenuView getMainMenu() {
		return this.mainMenuView;
	}

	public FightClubView getFightClubView() {
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

	public PlayerWinView getFightWinView() {
		return playerWinView;
	}
}