package com.game.darquest.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenuView {
	
	private Scene mainMenuScene;

	private List<String> buttonTextList = Arrays.asList("New Game", "Load", "Quit");
	private List<Button> buttons = new ArrayList<>();
	
	public MainMenuView() {
		mainMenuScene = new Scene(backgroundBox(), View.WIDTH, View.HEIGHT);
		mainMenuScene.getStylesheets().add("styles/MainMenuStyle.css");
	}
	
	private VBox backgroundBox() {
		VBox backgroundBox = new VBox();
		backgroundBox.setBackground(View.getBackground(Color.BLACK));
		backgroundBox.setAlignment(Pos.CENTER);
		backgroundBox.getChildren().add(getMainMenuBox());
		return backgroundBox;
	}
	
	VBox startMenuPane;
	public VBox getMainMenuBox() {
		startMenuPane = new VBox(10);
		startMenuPane.setAlignment(Pos.CENTER);
		startMenuPane.getChildren().add(title());
		for (int i = 0; i < buttonTextList.size(); i++) {
			buttons.add(View.getButton(buttonTextList.get(i), ((Integer)i).toString()));
			startMenuPane.getChildren().add(buttons.get(i));
		}
		return startMenuPane;
	}
	
	public VBox getStartMenuPane() {
		return startMenuPane;
	}
	
	Label title;
	private Label title() {
		title = new Label("");
		title.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/logo.png"))));
		title.setId("title");
		title.setPadding(new Insets(0,0,20,0));
		return title;
	}
	
	public Label getTitle() {
		return title;
	}
	
	
	public void addButtonAction(EventHandler<ActionEvent> l) {
		buttons.forEach(e->e.setOnAction(l));
	}
	
	public Scene getMainMenuScene() {
		return this.mainMenuScene;
	}
}
