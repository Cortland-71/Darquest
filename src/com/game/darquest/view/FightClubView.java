package com.game.darquest.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FightClubView extends DownTownView {
	
	private Scene fightClubScene;

	public FightClubView() {
		fightClubScene = new Scene(fightClubPane(), View.WIDTH, View.HEIGHT);
		fightClubScene.getStylesheets().addAll("DownTownStyle.css", "FightClubStyle.css");
	}
	
	private BorderPane fightClubPane() {
		BorderPane fightClubPane = new BorderPane();
		fightClubPane.setTop(topBox());
		fightClubPane.setLeft(leftBox());
		fightClubPane.setRight(rightPane());
		fightClubPane.setCenter(centerFightClubPane());
		fightClubPane.setBottom(bottomFightClubPane());
		return fightClubPane;
	}
	
	private BorderPane centerFightClubPane() {
		BorderPane centerPane = new BorderPane();
		centerPane.setCenter(centerEnemyPane());
		centerPane.setBottom(outputsBox());
		return centerPane;
	}
	
	private HBox outputsBox() {
		HBox outputsBox = new HBox(30);
		outputsBox.setId("outputsBox");
		outputsBox.setPadding(new Insets(10,0,10,0));
		outputsBox.getChildren().add(playerOutputBox());
		outputsBox.getChildren().add(enemyOutputBox());
		return outputsBox;
	}
	
	//Player components \/\/\/\/\/\/\\/\/\/\/\/\/\/\/\/\/\/\/\/
	private VBox playerOutputBox() {
		VBox playerOutputBox = new VBox(10);
		playerOutputBox.getChildren().add(playerOutputLabel());
		playerOutputBox.getChildren().add(playerOutputTextArea());
		return playerOutputBox;
	}
	
	private TextArea playerOutputTextArea;
	private TextArea playerOutputTextArea() {
		playerOutputTextArea = new TextArea();
		playerOutputTextArea.setEditable(false);
		playerOutputTextArea.setId("playerOutputTextArea");
		playerOutputTextArea.setMaxSize(645, 200);
		playerOutputTextArea.setMinSize(645, 200);
		return playerOutputTextArea;
	}
	
	public void setPlayerOutputTextArea(String text) {
		playerOutputTextArea.setText(text);
	}
	
	private Label playerOutputLabel() {
		Label playerOutputLabel = new Label("Your output");
		playerOutputLabel.setId("playerOutputLabel");
		return playerOutputLabel;
	}
	
	
	//Enemy components \/\/\/\/\/\/\\/\/\/\/\/\/\/\/\/\/\/\/\/
	private VBox enemyOutputBox() {
		VBox enemyOutputBox = new VBox(10);
		enemyOutputBox.getChildren().add(enemyOutputLabel());
		enemyOutputBox.getChildren().add(enemyOutputTextArea());
		return enemyOutputBox;
	}
	
	private TextArea enemyOutputTextArea;
	private TextArea enemyOutputTextArea() {
		enemyOutputTextArea = new TextArea();
		enemyOutputTextArea.setId("enemyOutputTextArea");
		enemyOutputTextArea.setEditable(false);
		enemyOutputTextArea.setMaxSize(645, 200);
		enemyOutputTextArea.setMinSize(645, 200);
		return enemyOutputTextArea;
	}
	
	
	private Label enemyOutputLabel() {
		Label enemyOutputLabel = new Label("Enemy output");
		enemyOutputLabel.setId("enemyOutputLabel");
		return enemyOutputLabel;
	}
	
	//Center for enemy stat's and pictures
	private BorderPane centerEnemyPane() {
		BorderPane centerEnemyPane = new BorderPane();
		centerEnemyPane.setId("centerEnemyPane");
		return centerEnemyPane;
	}
	
	//Bottom for player input \/\/\/\/\/\/\/\/\/\/\\/\/\/\/\/\/\
	private BorderPane bottomFightClubPane() {
		BorderPane bottomPane = new BorderPane();
		bottomPane.setTop(commandFieldBox());
		return bottomPane;
	}
	
	private VBox commandFieldBox() {
		VBox commandFieldBox = new VBox(15);
		commandFieldBox.setAlignment(Pos.CENTER);
		commandFieldBox.setPadding(new Insets(20,0,200,0));
		commandFieldBox.getChildren().add(commandLabel());
		commandFieldBox.getChildren().add(commandField());
		return commandFieldBox;
	}
	
	private Label commandLabel() {
		Label commandLabel = new Label("Enter commands here or type \"help\" for additional info.");
		commandLabel.setId("commandLabel");
		return commandLabel;
	}
	
	private TextField commandField;
	private TextField commandField() {
		commandField = new TextField();
		commandField.setMaxSize(600, 45);
		commandField.setMinSize(600, 45);
		commandField.setAlignment(Pos.CENTER);
		return commandField;
	}
	 
	public void clearCommandField() {
		commandField.clear();
	}
	
	public void addCommandFieldListener(EventHandler<KeyEvent> l) {
		commandField.setOnKeyPressed(l);
	}
	
	public String getCommand() {
		return commandField.getText();
	}
	
	public void setCommandFeildFocused() {
		commandField.requestFocus();
	}
	
	public Scene getFightClubScene() {
		return this.fightClubScene;
	}
}
