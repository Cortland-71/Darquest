package com.game.darquest.view;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerWinView {

	private Scene fightWinScene;

	public PlayerWinView() {
		fightWinScene = new Scene(fightWinBackground(), View.WIDTH, View.HEIGHT);
		fightWinScene.getStylesheets().addAll("DownTownStyle.css", "FightWinStyle.css");
	}
	
	public Scene getFightWinScene() {
		return this.fightWinScene;
	}
	
	private VBox fightWinBackground() {
		VBox fightWinBackground = new VBox();
		fightWinBackground.setBackground(View.getBackground(Color.BLACK));
		fightWinBackground.setAlignment(Pos.CENTER);
		fightWinBackground.getChildren().add(fightWinPane());
		return fightWinBackground;
	}
	
	private BorderPane fightWinPane;
	private BorderPane fightWinPane() {
		fightWinPane = new BorderPane();
		fightWinPane.setTop(topBox());
		fightWinPane.setCenter(centerPane());
		fightWinPane.setBottom(bottomBox());
		return fightWinPane;
	}
	

	public BorderPane getFightWinPane() {
		return this.fightWinPane;
	}
	
	//Top \/\/\/\/\/\/\/\/\/\\/\/\/\/\/\/\/\
	private HBox topBox() {
		HBox topBox = new HBox();
		topBox.setAlignment(Pos.TOP_CENTER);
		topBox.getChildren().add(winLabel());
		return topBox;
	}

	private Label winLabel() {
		Label winLabel = new Label("Victory!");
		winLabel.setId("winLabel");
		
		return winLabel;
	}
	
	//Center \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private BorderPane centerPane;
	private BorderPane centerPane() {
		centerPane = new BorderPane();
		centerPane.setMinSize(1500, 800);
		centerPane.setMaxSize(1500, 800);
		centerPane.setBottom(bottomInCenter());
		centerPane.setCenter(centerInCenter());
		centerPane.setRight(rightInCenter());
		return centerPane;
	}
	
	private HBox bottomInCenter() {
		HBox bottomInCenter = new HBox();
		bottomInCenter.setPadding(new Insets(0,0,20,0));
		bottomInCenter.setAlignment(Pos.CENTER);
		bottomInCenter.getChildren().add(outputTextArea());
		return bottomInCenter;
	}
	
	private TextArea outputTextArea;
	private TextArea outputTextArea() {
		outputTextArea = new TextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setId("outputTextArea");
		outputTextArea.setMaxSize(1200, 150);
		outputTextArea.setMinSize(1200, 150);
		return outputTextArea;
	}
	
	public void setOutputTextArea(String text) {
		outputTextArea.setText(text);
	}
	
	private HBox centerInCenter() {
		HBox centerInCenter = new HBox();
		centerInCenter.setBackground(View.getBackground(Color.BLACK));
		centerInCenter.setAlignment(Pos.CENTER);
		Image image = new Image("file:zom.png");
		ImageView enemyImage = new ImageView(image);
		centerInCenter.getChildren().add(enemyImage);
		return centerInCenter;
	}
	
	private VBox rightInCenter() {
		VBox rightInCenter = new VBox(10);
		rightInCenter.setMinSize(450, 0);
		rightInCenter.setId("rightInCenter");
		rightInCenter.setAlignment(Pos.TOP_LEFT);
		rightInCenter.getChildren().add(rewardsLabel);
		rightInCenter.getChildren().add(totalMovesLabel());
		rightInCenter.getChildren().add(totalXpEarnedLabel());
		rightInCenter.getChildren().add(efficiencyLabel());
		rightInCenter.getChildren().add(lootLabel());
		rightInCenter.getChildren().add(cashWonLabel());
		rightInCenter.getChildren().add(ratingLabel());
		return rightInCenter;
	}
	private Label rewardsLabel = new Label("Rewards");
	
	private Label totalMovesLabel;
	private Label totalMovesLabel() {
		totalMovesLabel = new Label("Total Number of Moves: ");
		return totalMovesLabel;
	}
	
	private Label totalXpEarnedLabel;
	private Label totalXpEarnedLabel() {
		totalXpEarnedLabel = new Label("Total XP Earned: ");
		return totalXpEarnedLabel;
	}
	
	private Label efficiencyLabel;
	private Label efficiencyLabel() {
		efficiencyLabel = new Label("Efficiency Rating: ");
		return efficiencyLabel;
	}
	
	private Label lootLabel;
	private Label lootLabel() {
		lootLabel = new Label("Loot Obtained: ");
		return lootLabel;
	}
	
	private Label cashWonLabel;
	private Label cashWonLabel() {
		cashWonLabel = new Label("Cash Won: ");
		return cashWonLabel;
	}
	
	private Label ratingLabel;
	private Label ratingLabel() {
		ratingLabel = new Label("RATING: ");
		return ratingLabel;
	}
	
	public void setWinStats(List<String> list) {
		List<String> labelSayingList = Arrays.asList("Number of Moves: ",
				"XP Earned: ", "Efficiency Rating: ", "Loot Obtained: ", "Cash Won: ", "RATING: ");
		List<Label> labelList = Arrays.asList(totalMovesLabel, totalXpEarnedLabel, 
				efficiencyLabel, lootLabel, cashWonLabel, ratingLabel);
		for (int i = 0; i < list.size(); i++) {
			labelList.get(i).setText(labelSayingList.get(i) + list.get(i));
		}
	}
	
	
	//Bottom \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private HBox bottomBox() {
		HBox bottomBox = new HBox();
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(25, 0, 50, 0));
		bottomBox.getChildren().add(continueButton());
		return bottomBox;
	}
	
	private Button continueButton;
	private Button continueButton() {
		continueButton = new Button("Continue");
		continueButton.setMinSize(225, 50);
		return continueButton;
	}

}
