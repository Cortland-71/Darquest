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
import javafx.scene.layout.VBox;

public class MainMenuView {
	
	private Scene mainMenuScene;
	
	private List<String> buttonTextList = Arrays.asList("New Game", "Load", "Quit");
	private List<Button> buttons = new ArrayList<>();
	
	public MainMenuView() {
		mainMenuScene = new Scene(getMainMenuBox(), View.WIDTH, View.HEIGHT);
		mainMenuScene.getStylesheets().add("MainMenuStyle.css");
	}
	
	public VBox getMainMenuBox() {
		VBox startMenuPane = new VBox(10);
		startMenuPane.setAlignment(Pos.CENTER);
		startMenuPane.getChildren().add(title());
		for (int i = 0; i < buttonTextList.size(); i++) {
			buttons.add(View.getButton(buttonTextList.get(i), ((Integer)i).toString()));
			startMenuPane.getChildren().add(buttons.get(i));
		}
		return startMenuPane;
	}
	
	private Label title() {
		Label title = new Label("D A R Q U E S T");
		title.setId("title");
		title.setPadding(new Insets(0,0,50,0));
		return title;
	}
	
	
	
	public void addButtonAction(EventHandler<ActionEvent> l) {
		buttons.forEach(e->e.setOnAction(l));
	}
	
	public Scene getMainMenuScene() {
		return this.mainMenuScene;
	}
}
