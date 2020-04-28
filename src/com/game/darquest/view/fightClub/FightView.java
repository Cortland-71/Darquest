package com.game.darquest.view.fightClub;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FightView {
	
	
	private Label enemyNameLabel = new Label();
	private Label enemyLvlLabel = new Label();
	private Label enemyCashLabel = new Label();
	private Label enemyHpLabel = new Label();
	private Label enemyAttackLabel = new Label();
	private Label enemyDefLabel = new Label();
	private Label enemyStealthLabel = new Label();
	private Label enemyAwarenessLabel = new Label();
	private Label enemyMutationLabel = new Label();
	private Label enemySecurityLabel = new Label();
	private Label enemyTypeLabel = new Label();
	private Label enemyIDLabel = new Label();
	
	private List<Label> queueCommandLabels = new ArrayList<>();

	public FightView() {
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
		outputsBox.getChildren().add(queueAllBox());
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
	
	private final int outputAreaHeight = 180;
	private final int outputAreaWidth = 465;
	private TextArea playerOutputTextArea;
	private TextArea playerOutputTextArea() {
		playerOutputTextArea = new TextArea();
		playerOutputTextArea.setEditable(false);
		playerOutputTextArea.setWrapText(true);
		playerOutputTextArea.setId("playerOutputTextArea");
		playerOutputTextArea.setMaxSize(outputAreaWidth, outputAreaHeight);
		playerOutputTextArea.setMinSize(outputAreaWidth, outputAreaHeight);
		return playerOutputTextArea;
	}

	public void setPlayerOutputTextArea(String text) {
		playerOutputTextArea.appendText(text);
	}
	
	public void clearPlayerOutputTextArea() {
		playerOutputTextArea.clear();
	}
	
	private Label playerOutputLabel() {
		Label playerOutputLabel = new Label("Your output");
		playerOutputLabel.setId("playerOutputLabel");
		return playerOutputLabel;
	}
	
	//Queue \/\/\/\/\/\/\/\/\\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private VBox queueAllBox() {
		VBox queueAllBox = new VBox(10);
		queueAllBox.setAlignment(Pos.CENTER);
		queueAllBox.getChildren().add(queueLabel());
		queueAllBox.getChildren().add(queueScrollPane());
		return queueAllBox;
	}
	
	private final int queueWidth = 325;
	private final int queueHeight = 180;
	private ScrollPane queueScrollPane() {
		ScrollPane queueScrollPane = new ScrollPane();
		queueScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		queueScrollPane.setMinSize(queueWidth, queueHeight);
		queueScrollPane.setMaxSize(queueWidth, queueHeight);
		queueScrollPane.setContent(queueBox());
		return queueScrollPane;
	}
	
	private VBox queueBox;
	private VBox queueBox() {
		queueBox = new VBox(5);
		queueBox.setMinSize(300, queueHeight);
		queueBox.setMaxWidth(300);
		return queueBox;
	}
	
	private Label queueLabel;
	private Label queueLabel() {
		queueLabel = new Label("Queue");
		queueLabel.setId("queueLabel");
		return queueLabel;
	}
	public void setQueueLabel(int points) {
		queueLabel.setText("Queue: " + points + "/10");
	}
	
	public void addCommandToQueue(String command) {
		queueBox.getChildren().clear();
		Label l = new Label(command);
		l.setPadding(new Insets(10,0,0,10));
		queueCommandLabels.add(l);
		queueCommandLabels.forEach(e->queueBox.getChildren().add(e));
	}
	
	public void clearQueue() {
		queueBox.getChildren().clear();
		queueCommandLabels.clear();
	}
	
	//Enemy components \/\/\/\/\/\/\\/\/\/\/\/\/\/\/\/\/\/\/\/
	private VBox enemyOutputBox() {
		VBox enemyOutputBox = new VBox(10);
		enemyOutputBox.setAlignment(Pos.CENTER);
		enemyOutputBox.getChildren().add(enemyOutputLabel());
		enemyOutputBox.getChildren().add(enemyOutputTextArea());
		return enemyOutputBox;
	}
	
	private TextArea enemyOutputTextArea;
	private TextArea enemyOutputTextArea() {
		enemyOutputTextArea = new TextArea();
		enemyOutputTextArea.setId("enemyOutputTextArea");
		enemyOutputTextArea.setEditable(false);
		enemyOutputTextArea.setMaxSize(outputAreaWidth, outputAreaHeight);
		enemyOutputTextArea.setMinSize(outputAreaWidth, outputAreaHeight);
		return enemyOutputTextArea;
	}
	
	public void clearEnemyOutputTextArea() {
		enemyOutputTextArea.clear();
	}
	
	public void setEnemyOutputTextArea(String text) {
		enemyOutputTextArea.appendText(text);
	}
	
	public void clearAllOutputTextAreas() {
		clearEnemyOutputTextArea();
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
		enemyRightStatsBox.getChildren().addAll(enemyNameLabel, enemyLvlLabel,
				enemyCashLabel, enemyHpLabel ,enemyHpBar(), enemyAttackLabel,
				enemyDefLabel, enemyStealthLabel, enemyAwarenessLabel, enemyMutationLabel,
				enemySecurityLabel, enemyTypeLabel,
				enemyIDLabel);
		return enemyRightStatsBox;
	}
	
	private final int barLength = 180;
	private final int barWidth = 10;
	
	
	
	private ProgressBar enemyHpBar;
	private ProgressBar enemyHpBar() {
		enemyHpBar = new ProgressBar();
		enemyHpBar.setId("enemyHpBar");
		enemyHpBar.setMinSize(barLength, barWidth);
		enemyHpBar.setMaxSize(barLength, barWidth);
		return enemyHpBar;
	}
	
	
	//Enemy Bottom stat's box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private VBox enemyBottomStatsBox() {
		VBox enemyBottomStatsBox = new VBox(2);
		enemyBottomStatsBox.setId("enemyBottomStatsBox");
		enemyBottomStatsBox.setPadding(new Insets(5,0,0,20));
		enemyBottomStatsBox.setAlignment(Pos.TOP_LEFT);
		enemyBottomStatsBox.setMinSize(420, 120);
		enemyBottomStatsBox.setMaxSize(420, 120);
		enemyBottomStatsBox.getChildren().addAll(enemyLimitLabel(), enemyLimitBar());
		return enemyBottomStatsBox;
	}
	
	private final int esBarLength = 380;
	private final int esBarWidth = 9;
	
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
		bottomPane.setCenter(commandFieldBox());
		return bottomPane;
	}
	
	public BorderPane getFightClubBottom() {
		return bottomPane;
	}
	
	private VBox commandFieldBox() {
		VBox commandFieldBox = new VBox(15);
		commandFieldBox.setAlignment(Pos.CENTER);
		commandFieldBox.setPadding(new Insets(15,0,165,0));
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
	
	public void setEnemyStats(Enemy p, Person player) {
		enemyNameLabel.setText("Name:\t" + p.getName());
		enemyLvlLabel.setText("Lvl:\t" + p.getLvl());
		enemyCashLabel.setText("Cash:\t" + p.getCashFormatted());
		enemyHpLabel.setText("HP:\t" + p.getHp() + "/1.0");
		

		enemyHpBar.setProgress(p.getHp());
		enemyLimitBar.setProgress(p.getLimit());
		
		if(p.getAttack() <= player.getDef()) {
			enemyAttackLabel.setStyle("-fx-text-fill: red");
		} else {
			enemyAttackLabel.setStyle("-fx-text-fill: #cc6600");
		}
		
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
		
		if(p.getMutation() < p.getMaxMutation()) {
			enemyMutationLabel.setStyle("-fx-text-fill: red");
		} else {
			enemyMutationLabel.setStyle("-fx-text-fill: #cc6600");
		}
		
		if(p.getSecurity() < p.getMaxSecurity()) {
			enemySecurityLabel.setStyle("-fx-text-fill: red");
		} else {
			enemySecurityLabel.setStyle("-fx-text-fill: #cc6600");
		}
		
		enemyAttackLabel.setText("Attack:    " + p.getAttack());
		enemyDefLabel.setText("Defense:   " + p.getDef());
		enemyStealthLabel.setText("Stealth:   " + p.getStealth());
		enemyAwarenessLabel.setText("Awareness: " + p.getAwareness());
		enemyMutationLabel.setText("Mutation:  " + p.getMutation());
		enemySecurityLabel.setText("Security:  " + p.getSecurity());
		
		enemyTypeLabel.setText("Type: "+p.getType().getName());
		Tooltip typeToolTip = new Tooltip(p.getType().getDescription());
		typeToolTip.setStyle("-fx-text-fill: orange");
		enemyTypeLabel.setTooltip(typeToolTip);
		enemyIDLabel.setText("ID: " + p.getId());
		enemyLimitLabel.setText("Limit: " + p.getLimit());
		
		
		
	}

	public List<String> getQueueCommands() {
		List<String> commandsStrings = new ArrayList<>();
		for (int i = 0; i < queueCommandLabels.size(); i++) {
			commandsStrings.add(queueCommandLabels.get(i).getText());
		}
		return commandsStrings;
	}

	
}
