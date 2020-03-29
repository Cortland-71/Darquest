package com.game.darquest.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
		centerPane.setCenter(centerEnemyBox());
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
	private HBox centerEnemyBox() {
		HBox centerEnemyBox = new HBox(15);
		centerEnemyBox.setAlignment(Pos.CENTER);
		centerEnemyBox.getChildren().add(innerEnemyPane());
//		for (int i = 0; i < 3; i++) {
//			centerEnemyBox.getChildren().add(innerEnemyPane());
//		}
	
		centerEnemyBox.setId("centerEnemyBox");
		return centerEnemyBox;
	}
	
	private final int innerHeight = 440;
	private final int innerWidth = 420;
	
	private BorderPane innerEnemyPane() {
		BorderPane innerEnemyPane = new BorderPane();
		innerEnemyPane.setMinSize(innerWidth, innerHeight);
		innerEnemyPane.setMaxSize(innerWidth, innerHeight);
		innerEnemyPane.setBackground(View.getBackground(Color.RED));
		innerEnemyPane.setCenter(enemyImage());
		innerEnemyPane.setRight(enemyRightStatsBox());
		innerEnemyPane.setBottom(enemyBottomStatsBox());
		return innerEnemyPane;
	}
	
	private ImageView enemyImage() {
		Image image = new Image("file:test.png");
		ImageView enemyImage = new ImageView(image);
		return enemyImage;
	}
	
	//Enemy right stats box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private VBox enemyRightStatsBox() {
		VBox enemyRightStatsBox = new VBox(5);
		enemyRightStatsBox.setId("enemyRightStatsBox");
		enemyRightStatsBox.setAlignment(Pos.TOP_LEFT);
		enemyRightStatsBox.setPadding(new Insets(0,0,0,10));
		enemyRightStatsBox.setMinSize(200, 320);
		enemyRightStatsBox.setMaxSize(200, 320);
		enemyRightStatsBox.setBackground(View.getBackground(Color.PINK));
		enemyRightStatsBox.getChildren().addAll(enemyNameLabel, enemyLvlLabel,
				enemyCashLabel, enemyHpLabel,enemyHpBar(), enemyEngLabel, enemyEngBar(), enemyWeaponLabel,
				enemyArmorLabel, enemyDefLabel, enemyStealthLabel, enemyAwarenessLabel, enemyTypeLabel,
				enemyIDLabel);
		return enemyRightStatsBox;
	}
	
	private final int barLength = 180;
	private final int barWidth = 10;
	
	private Label enemyNameLabel = new Label("Name");
	public void setEnemyNameLabel(String name) {
		enemyNameLabel.setText(name);
	}
	private Label enemyLvlLabel = new Label("Lvl");
	public void setEnemyLvlLabel(String Lvl) {
		enemyLvlLabel.setText(Lvl);
	}
	private Label enemyCashLabel = new Label("Cash");
	public void setEnemyCashLabel(String Cash) {
		enemyCashLabel.setText(Cash);
	}
	
	private Label enemyHpLabel = new Label("Hp");
	public void setEnemyHpLabel(String Hp) {
		enemyHpLabel.setText(Hp);
	}
	private ProgressBar enemyHpBar;
	private ProgressBar enemyHpBar() {
		enemyHpBar = new ProgressBar();
		enemyHpBar.setId("enemyHpBar");
		enemyHpBar.setMinSize(barLength, barWidth);
		enemyHpBar.setMaxSize(barLength, barWidth);
		return enemyHpBar;
	}
	
	private Label enemyEngLabel = new Label("Eng");
	public void setEnemyEngLabel(String Eng) {
		enemyEngLabel.setText(Eng);
	}
	private ProgressBar enemyEngBar;
	private ProgressBar enemyEngBar() {
		enemyEngBar = new ProgressBar();
		enemyEngBar.setId("enemyEngBar");
		enemyEngBar.setMinSize(barLength, barWidth);
		enemyEngBar.setMaxSize(barLength, barWidth);
		return enemyEngBar;
	}
	
	private Label enemyWeaponLabel = new Label("Weapon");
	public void setEnemyWeaponLabel(String Weapon) {
		enemyWeaponLabel.setText(Weapon);
	}
	
	private Label enemyArmorLabel = new Label("Armor");
	public void setEnemyArmorLabel(String Armor) {
		enemyArmorLabel.setText(Armor);
	}
	
	private Label enemyDefLabel = new Label("Def");
	public void setEnemyDefLabel(String Def) {
		enemyDefLabel.setText(Def);
	}
	
	private Label enemyStealthLabel = new Label("Stealth");
	public void setEnemyStealthLabel(String Stealth) {
		enemyStealthLabel.setText(Stealth);
	}
	
	private Label enemyAwarenessLabel = new Label("Awareness");
	public void setEnemyAwarenessLabel(String Awareness) {
		enemyAwarenessLabel.setText(Awareness);
	}
	
	private Label enemyTypeLabel = new Label("Type");
	public void setEnemyTypeLabel(String Type) {
		enemyTypeLabel.setText(Type);
	}
	
	private Label enemyIDLabel = new Label("ID");
	public void setEnemyIDLabel(String ID) {
		enemyIDLabel.setText(ID);
	}
	
	//Enemy Bottom stats box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private VBox enemyBottomStatsBox() {
		VBox enemyBottomStatsBox = new VBox();
		enemyBottomStatsBox.setId("enemyBottomStatsBox");
		enemyBottomStatsBox.setAlignment(Pos.CENTER_LEFT);
		enemyBottomStatsBox.setMinSize(innerWidth, 120);
		enemyBottomStatsBox.setMaxSize(innerWidth, 120);
		enemyBottomStatsBox.setBackground(View.getBackground(Color.BLUE));
		enemyBottomStatsBox.getChildren().addAll(enemyEatLabel, enemyEatBar(),
				enemySleepLabel, enemySleepBar(), enemyWorkLabel, enemyWorkBar());
		return enemyBottomStatsBox;
	}
	
	private final int esBarLength = 200;
	private final int esBarWidth = 15;
	
	private Label enemyEatLabel = new Label("Eat");
	public void setEnemyEatLabel(String Eat) {
		enemyEatLabel.setText(Eat);
	}
	private ProgressBar enemyEatBar;
	private ProgressBar enemyEatBar() {
		enemyEatBar = new ProgressBar();
		enemyEatBar.setId("enemyEatBar");
		enemyEatBar.setMinSize(esBarLength, esBarWidth);
		enemyEatBar.setMaxSize(esBarLength, esBarWidth);
		return enemyEatBar;
	}
	
	private Label enemySleepLabel = new Label("Sleep");
	public void setEnemySleepLabel(String Sleep) {
		enemySleepLabel.setText(Sleep);
	}
	private ProgressBar enemySleepBar;
	private ProgressBar enemySleepBar() {
		enemySleepBar = new ProgressBar();
		enemySleepBar.setId("enemySleepBar");
		enemySleepBar.setMinSize(esBarLength, esBarWidth);
		enemySleepBar.setMaxSize(esBarLength, esBarWidth);
		return enemySleepBar;
	}
	
	private Label enemyWorkLabel = new Label("Work");
	public void setEnemyWorkLabel(String Work) {
		enemyWorkLabel.setText(Work);
	}
	private ProgressBar enemyWorkBar;
	private ProgressBar enemyWorkBar() {
		enemyWorkBar = new ProgressBar();
		enemyWorkBar.setId("enemyWorkBar");
		enemyWorkBar.setMinSize(esBarLength, esBarWidth);
		enemyWorkBar.setMaxSize(esBarLength, esBarWidth);
		return enemyWorkBar;
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
