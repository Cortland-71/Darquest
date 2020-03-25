package com.game.darquest.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class NewPlayerView {
	
	private Scene enterNameScene;
	
	public NewPlayerView() {
		enterNameScene = new Scene(getEnterNameBox(), View.WIDTH, View.HEIGHT);
		enterNameScene.getStylesheets().add("NewPlayerStyle.css");
	}
	
	public VBox getEnterNameBox() {
		VBox enterNameBox = new VBox(20);
		enterNameBox.setAlignment(Pos.CENTER);
		enterNameBox.getChildren().add(greeting());
		enterNameBox.getChildren().add(nameField());
		return enterNameBox;
	}
	
	private Label greeting() {
		Label greeting = new Label("What should I call you?");
		return greeting;
	}
	
	private TextField nameField;
	private TextField nameField() {
		nameField = new TextField();
		nameField.setMaxSize(450, 45);
		nameField.setMinSize(450, 45);
		return nameField;
		
	}
	
	public void addKeyListener(EventHandler<KeyEvent> l) {
		nameField.setOnKeyPressed(l);
	}
	
	public String getNameText() {
		return nameField.getText();
	}
	
	public Scene getEnterNameScene() {
		return this.enterNameScene;
	}
}
