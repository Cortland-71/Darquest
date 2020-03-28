package com.game.darquest.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	
	public View(Stage primaryStage) {
		this.window = primaryStage;
		
		this.mainMenuView = new MainMenuView();
		this.newPlayerView = new NewPlayerView();
		this.downTownView = new DownTownView();
		this.tutorialView = new TutorialView();
		this.shopView = new ShopView();
		this.fightClubView = new FightClubView();
		
		window.setScene(mainMenuView.getMainMenuScene());
		window.setTitle("Darquest FX v1.0");
		window.show();
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
}
