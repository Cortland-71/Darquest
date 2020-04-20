package com.game.darquest.view.fightClub;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.Driver;
import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class FightClubWin {

	public FightClubWin() {
		fightWinCenter();
		fightWinBottom();
	}
	
	
	
	
	//Center \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private BorderPane centerPane;
	private BorderPane fightWinCenter() {
		centerPane = new BorderPane();
		centerPane.setMinSize(1300, 727);
		centerPane.setMaxSize(1300, 727);
		//centerPane.setBottom(bottomInCenter());
		centerPane.setTop(topBox());
		centerPane.setCenter(centerInCenter());
		centerPane.setRight(rightInCenter());
		return centerPane;
	}
	
	public BorderPane getFightWinCenter() {
		return centerPane;
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
		outputTextArea.setMaxSize(1200, 100);
		outputTextArea.setMinSize(1200, 100);
		return outputTextArea;
	}
	
	public void setOutputTextArea(String text) {
		outputTextArea.setText(text);
	}
	
	private HBox centerInCenter() {
		HBox centerInCenter = new HBox();
		centerInCenter.setBackground(View.getBackground(Color.BLACK));
		centerInCenter.setAlignment(Pos.CENTER);
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/zom2.png"))));
		centerInCenter.getChildren().add(l);
		return centerInCenter;
	}
	
	private VBox rightInCenter() {
		VBox rightInCenter = new VBox(20);
		rightInCenter.setPadding(new Insets(0,20,0,20));
		rightInCenter.setAlignment(Pos.CENTER_LEFT);
		rightInCenter.setMinSize(320, 560);
		rightInCenter.setMaxSize(320, 560);
		rightInCenter.setId("rightInCenter");
		rightInCenter.getChildren().add(rewardsLabel);
		rightInCenter.getChildren().add(totalMovesLabel());
		rightInCenter.getChildren().add(totalXpEarnedLabel());
		rightInCenter.getChildren().add(efficiencyLabel());
		rightInCenter.getChildren().add(lootLabel());
		rightInCenter.getChildren().add(cashWonLabel());
		rightInCenter.getChildren().add(bonusCashWonLabel());
		rightInCenter.getChildren().add(totalCashWonLabel());
		rightInCenter.getChildren().add(ratingLabel());
		return rightInCenter;
	}
	private Label rewardsLabel = new Label("******* Rewards *******");
	
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
	
	private Label bonusCashWonLabel;
	private Label bonusCashWonLabel() {
		bonusCashWonLabel = new Label("Cash Won: ");
		return bonusCashWonLabel;
	}
	
	private Label totalCashWonLabel;
	private Label totalCashWonLabel() {
		totalCashWonLabel = new Label("Cash Won: ");
		return totalCashWonLabel;
	}
	
	private Label ratingLabel;
	private Label ratingLabel() {
		ratingLabel = new Label("Overall RATING: ");
		ratingLabel.setId("ratingLabel");
		return ratingLabel;
	}
	
	public void setWinStats(List<String> list) {
		List<String> labelSayingList = Arrays.asList("Total Moves: ",
				"XP Earned: ", "Efficiency: ", "Loot Obtained: ", "Cash Won: +", "Bonus Cash: +",
				"Total Cash: +", "GRADE: ");
		List<Label> labelList = Arrays.asList(totalMovesLabel, totalXpEarnedLabel, 
				efficiencyLabel, lootLabel, cashWonLabel, bonusCashWonLabel, totalCashWonLabel, ratingLabel);
		for (int i = 0; i < list.size(); i++) {
			labelList.get(i).setText(labelSayingList.get(i) + list.get(i));
		}
	}
	
	
	//Bottom \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private HBox bottomBox;
	private HBox fightWinBottom() {
		bottomBox = new HBox();
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(20, 0, 185, 0));
		bottomBox.getChildren().add(continueButton());
		return bottomBox;
	}
	
	public HBox getFightWinBottom() {
		return bottomBox;
	}
	
	private Button continueButton;
	private Button continueButton() {
		continueButton = new Button("Continue");
		continueButton.setMinSize(225, 50);
		return continueButton;
	}
	
	public void addContinueButtonListener(EventHandler<ActionEvent> l) {
		continueButton.setOnAction(l);
	}

}
