package com.game.darquest.view;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FightClubView {

	public FightClubView() {
		fightClubCenter();
		fightClubBottom();
	}
	
	private BorderPane fightClubCenter;
	private BorderPane fightClubCenter() {
		fightClubCenter = new BorderPane();
		fightClubCenter.setCenter(centerFightClubPane());
		return fightClubCenter;
	}
	
	public BorderPane getFightClubCenter() {
		return fightClubCenter;
	}
	
	private BorderPane centerFightClubPane() {
		BorderPane centerPane = new BorderPane();
		centerPane.setMinSize(1200, 600);
		centerPane.setCenter(centerEnemyBox());
		centerPane.setBottom(outputsBox());
		return centerPane;
	}
	
	private HBox outputsBox() {
		HBox outputsBox = new HBox(15);
		outputsBox.setId("outputsBox");
		outputsBox.setAlignment(Pos.BOTTOM_CENTER);
		outputsBox.setPadding(new Insets(25,0,0,0));
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
		playerOutputTextArea.setMaxSize(637, 180);
		playerOutputTextArea.setMinSize(637, 180);
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
		enemyOutputTextArea.setMaxSize(637, 180);
		enemyOutputTextArea.setMinSize(637, 180);
		return enemyOutputTextArea;
	}
	
	public void clearEnemyOutputTextArea() {
		enemyOutputTextArea.clear();
	}
	
	public void setEnemyOutputTextArea(String text) {
		enemyOutputTextArea.appendText(text+"\n");
	}
	
	public void clearAllOutputTextAreas() {
		clearEnemyOutputTextArea();
		setPlayerOutputTextArea("");
	}
	
	private Label enemyOutputLabel() {
		Label enemyOutputLabel = new Label("Enemy output");
		enemyOutputLabel.setId("enemyOutputLabel");
		return enemyOutputLabel;
	}
	
	//Center for enemy stat's and pictures
	private HBox centerEnemyBox;
	private HBox centerEnemyBox() {
		centerEnemyBox = new HBox(12);
		centerEnemyBox.setAlignment(Pos.CENTER);
		centerEnemyBox.setId("centerEnemyBox");
		return centerEnemyBox;
	}
	
	public HBox getCenterEnemyBox() {
		return centerEnemyBox;
	}

	private final int innerHeight = 482;
	private final int innerWidth = 422;
	
	public BorderPane innerEnemyPane(String picture) {
		BorderPane innerEnemyPane = new BorderPane();
		innerEnemyPane.setId("innerEnemyPane");
		innerEnemyPane.setMinSize(innerWidth, innerHeight);
		innerEnemyPane.setMaxSize(innerWidth, innerHeight);
		innerEnemyPane.setCenter(enemyImage(picture));
		innerEnemyPane.setRight(enemyRightStatsBox());
		innerEnemyPane.setBottom(enemyBottomStatsBox());
		return innerEnemyPane;
	}
	
	public BorderPane getInnerEnemyPane(String picture) {
		return innerEnemyPane(picture);
	}
	
	private Label enemyImage(String picture) {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/"+picture))));
		return l;
	}
	
	//Enemy right stats box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private VBox enemyRightStatsBox() {
		VBox enemyRightStatsBox = new VBox(10);
		enemyRightStatsBox.setId("enemyRightStatsBox");
		enemyRightStatsBox.setAlignment(Pos.TOP_LEFT);
		enemyRightStatsBox.setPadding(new Insets(10,0,0,5));
		enemyRightStatsBox.setMinSize(200, 360);
		enemyRightStatsBox.setMaxSize(200, 360);
		enemyRightStatsBox.setBackground(View.getBackground(Color.PINK));
		enemyRightStatsBox.getChildren().addAll(enemyNameLabel(), enemyLvlLabel(),
				enemyCashLabel(), enemyHpLabel() ,enemyHpBar(), enemyEngLabel(), enemyEngBar(), enemyWeaponLabel(),
				enemyArmorLabel(), enemyDefLabel(), enemyStealthLabel(), enemyAwarenessLabel(), enemyTypeLabel(),
				enemyIDLabel());
		return enemyRightStatsBox;
	}
	
	private final int barLength = 180;
	private final int barWidth = 10;
	
	private Label enemyNameLabel;
	private Label enemyNameLabel() {
		enemyNameLabel = new Label("Name");
		return enemyNameLabel;
	}
	public void setEnemyNameLabel(String name) {
		enemyNameLabel.setText(name);
	}
	
	private Label enemyLvlLabel;
	private Label enemyLvlLabel() {
		enemyLvlLabel = new Label("Lvl");
		return enemyLvlLabel;
	}
	public void setEnemyLvlLabel(String Lvl) {
		enemyLvlLabel.setText(Lvl);
	}
	
	private Label enemyCashLabel;
	private Label enemyCashLabel() {
		enemyCashLabel = new Label("Cash");
		return enemyCashLabel;
	}
	
	
	
	private Label enemyHpLabel;
	private Label enemyHpLabel() {
		enemyHpLabel = new Label("Hp");
		return enemyHpLabel;
	}
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
	
	private Label enemyEngLabel;
	private Label enemyEngLabel() {
		enemyEngLabel = new Label("Eng");
		return enemyEngLabel;
	}
	
	private ProgressBar enemyEngBar;
	private ProgressBar enemyEngBar() {
		enemyEngBar = new ProgressBar();
		enemyEngBar.setId("enemyEngBar");
		enemyEngBar.setMinSize(barLength, barWidth);
		enemyEngBar.setMaxSize(barLength, barWidth);
		return enemyEngBar;
	}
	
	private Label enemyWeaponLabel;
	private Label enemyWeaponLabel() {
		enemyWeaponLabel = new Label("Weapon");
		return enemyWeaponLabel;
	}
	
	private Label enemyArmorLabel;
	private Label enemyArmorLabel() {
		enemyArmorLabel = new Label("Armor");
		return enemyArmorLabel;
	}
	
	private Label enemyDefLabel;
	private Label enemyDefLabel() {
		enemyDefLabel = new Label("Def");
		return enemyDefLabel;
	}
	
	private Label enemyStealthLabel;
	private Label enemyStealthLabel() {
		enemyStealthLabel = new Label("Stealth");
		return enemyStealthLabel;
	}
	
	private Label enemyAwarenessLabel;
	private Label enemyAwarenessLabel() {
		enemyAwarenessLabel = new Label("Awareness");
		return enemyAwarenessLabel;
	}
	
	private Label enemyTypeLabel;
	private Label enemyTypeLabel() {
		enemyTypeLabel = new Label("Type");
		return enemyTypeLabel;
	}
	
	private Label enemyIDLabel;
	private Label enemyIDLabel() {
		enemyIDLabel = new Label("ID");
		return enemyIDLabel;
	}
	
	//Enemy Bottom stats box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private VBox enemyBottomStatsBox() {
		VBox enemyBottomStatsBox = new VBox(2);
		enemyBottomStatsBox.setId("enemyBottomStatsBox");
		enemyBottomStatsBox.setPadding(new Insets(5,0,0,20));
		enemyBottomStatsBox.setAlignment(Pos.TOP_LEFT);
		enemyBottomStatsBox.setMinSize(420, 120);
		enemyBottomStatsBox.setMaxSize(420, 120);
		enemyBottomStatsBox.setBackground(View.getBackground(Color.BLUE));
		enemyBottomStatsBox.getChildren().addAll(enemyLimitLabel(), enemyLimitBar(),
				enemyEatLabel(), enemyEatBar(), enemySleepLabel(), enemySleepBar(), 
				enemyWorkLabel(), enemyWorkBar());
		return enemyBottomStatsBox;
	}
	
	private final int esBarLength = 380;
	private final int esBarWidth = 9;
	
	private Label enemyEatLabel;
	private Label enemyEatLabel() {
		enemyEatLabel = new Label("Eat");
		return enemyEatLabel;
	}
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
	
	private Label enemySleepLabel;
	private Label enemySleepLabel() {
		enemySleepLabel = new Label("Sleep");
		return enemySleepLabel;
	}
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
	
	private Label enemyWorkLabel;
	private Label enemyWorkLabel() {
		enemyWorkLabel = new Label("Work");
		return enemyWorkLabel;
	}
	
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
	
	private Label enemyLimitLabel;
	private Label enemyLimitLabel() {
		enemyLimitLabel = new Label("Limit");
		return enemyLimitLabel;
	}
	
	public void setEnemyLimitLabel(String Limit) {
		enemyLimitLabel.setText(Limit);
	}
	private ProgressBar enemyLimitBar;
	private ProgressBar enemyLimitBar() {
		enemyLimitBar = new ProgressBar();
		enemyLimitBar.setId("enemyLimitBar");
		enemyLimitBar.setMinSize(esBarLength, esBarWidth);
		enemyLimitBar.setMaxSize(esBarLength, esBarWidth);
		return enemyLimitBar;
	}
	
	
	//Bottom for player input \/\/\/\/\/\/\/\/\/\/\\/\/\/\/\/\/\ Bottom player input
	private BorderPane bottomPane;
	private BorderPane fightClubBottom() {
		bottomPane = new BorderPane();
		bottomPane.setTop(inputLabelMovesBox());
		bottomPane.setCenter(commandFieldBox());
		return bottomPane;
	}
	
	public BorderPane getFightClubBottom() {
		return bottomPane;
	}
	
	private VBox commandFieldBox() {
		VBox commandFieldBox = new VBox(15);
		commandFieldBox.setAlignment(Pos.CENTER);
		commandFieldBox.setPadding(new Insets(5,0,165,0));
		commandFieldBox.getChildren().add(commandField());
		return commandFieldBox;
	}
	
	private HBox inputLabelMovesBox() {
		HBox inputLabelMovesBox = new HBox(15);
		inputLabelMovesBox.setAlignment(Pos.CENTER);
		inputLabelMovesBox.setPadding(new Insets(20,0,0,0));
		inputLabelMovesBox.getChildren().add(commandLabel());
		inputLabelMovesBox.getChildren().add(playerMovesLabel());
		return inputLabelMovesBox;
	}
	
	private Label commandLabel() {
		Label commandLabel = new Label("Enter commands here or type \"help\" for additional info.");
		commandLabel.setId("commandLabel");
		return commandLabel;
	}
	
	private Label playerMovesLabel;
	private Label playerMovesLabel() {
		playerMovesLabel = new Label("Moves");
		playerMovesLabel.setId(""
				+ "");
		return playerMovesLabel;
	}
	
	private TextField commandField;
	private TextField commandField() {
		commandField = new TextField();
		commandField.setMaxSize(600, 45);
		commandField.setMinSize(600, 45);
		commandField.setAlignment(Pos.CENTER);
		return commandField;
	}
	
	public void setDisableCommandField(boolean b) {
		commandField.setDisable(b);
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
	
	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Helper
	public void setPlayerMovesLeft(Person p) {
		playerMovesLabel.setText("Moves left: "+p.getMoves()+"/"+p.getMaxMoves());
	}
	
	public void setEnemyStats(Enemy p, Person player) {
		enemyNameLabel.setText("Name:\t" + p.getName());
		enemyLvlLabel.setText("Lvl:\t" + p.getLvl());
		enemyCashLabel.setText("Cash:\t" + p.getCashFormatted());
		enemyHpLabel.setText("HP:\t" + p.getHp() + "/1.0");
		enemyEngLabel.setText("Eng:\t" + p.getEng() + "/1.0");
		enemyEatLabel.setText("Eat:\t" + p.getEat() + "/1.0");
		enemySleepLabel.setText("Sleep:\t" + p.getSleep() + "/1.0");
		enemyWorkLabel.setText("Work:\t" + p.getWork() + "/1.0");

		enemyHpBar.setProgress(p.getHp());
		enemyEngBar.setProgress(p.getEng());
		enemyEatBar.setProgress(p.getEat());
		enemySleepBar.setProgress(p.getSleep());
		enemyWorkBar.setProgress(p.getWork());
		enemyLimitBar.setProgress(p.getLimit());

		enemyWeaponLabel.setText("Wep: " + p.getEquippedWeaponString());
		enemyArmorLabel.setText("Arm: " + p.getEquippedArmorString());
		
		if(p.getDef() <= player.getEquippedWeapon().getMinDamage()) {
			enemyDefLabel.setStyle("-fx-text-fill: red");
		} else {
			enemyDefLabel.setStyle("-fx-text-fill: #cc6600");
		}
		
		if(p.getStealth() <= player.getAwareness()) {
			enemyStealthLabel.setStyle("-fx-text-fill: red");
		} else {
			enemyStealthLabel.setStyle("-fx-text-fill: #cc6600");
		}
		
		if(p.getAwareness() <= player.getStealth()) {
			enemyAwarenessLabel.setStyle("-fx-text-fill: red");
		} else {
			enemyAwarenessLabel.setStyle("-fx-text-fill: #cc6600");
		}
		
		enemyDefLabel.setText("Defense:   " + p.getDef());
		enemyStealthLabel.setText("Stealth:   " + p.getStealth());
		enemyAwarenessLabel.setText("Awareness: " + p.getAwareness());
		enemyTypeLabel.setText("Type: "+p.getType().getName());
		Tooltip typeToolTip = new Tooltip(p.getType().getDescription());
		typeToolTip.setStyle("-fx-text-fill: orange");
		enemyTypeLabel.setTooltip(typeToolTip);
		enemyIDLabel.setText("ID: " + p.getId());
		enemyLimitLabel.setText("Limit: " + p.getLimit());
		
	}
}
