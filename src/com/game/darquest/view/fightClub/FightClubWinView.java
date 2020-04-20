package com.game.darquest.view.fightClub;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FightClubWinView {

	public FightClubWinView() {
		fightWinCenter();
		fightWinBottom();
	}

	//Center \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private StackPane centerPane;
	private StackPane fightWinCenter() {
		centerPane = new StackPane();
		centerPane.setAlignment(Pos.BOTTOM_CENTER);
		centerPane.setMinSize(1300, 700);
		centerPane.setMaxSize(1300, 700);
		centerPane.getChildren().add(ringPicture());
		centerPane.getChildren().add(outputPane());
		centerPane.getChildren().add(topBox());
		return centerPane;
	}
	
	public StackPane getFightWinCenter() {
		return centerPane;
	}
	
	//Top \/\/\/\/\/\/\/\/\/\\/\/\/\/\/\/\/\
	
	private StackPane topBox() {
		StackPane topBox = new StackPane();
		topBox.setAlignment(Pos.TOP_CENTER);
		Label l = new Label();
		l.setPadding(new Insets(30,0,0,0));
		l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/winLogo.png"))));
		topBox.getChildren().add(l);
		return topBox;
	}

	private HBox ringPicture() {
		HBox centerInCenter = new HBox();
		centerInCenter.setBackground(View.getBackground(Color.BLACK));
		centerInCenter.setAlignment(Pos.CENTER);
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/winRing.png"))));
		centerInCenter.getChildren().add(l);
		return centerInCenter;
	}
	
	private StackPane outputPane() {
		StackPane outputPane = new StackPane();
		outputPane.setAlignment(Pos.CENTER);
		outputPane.setPadding(new Insets(60,0,0,0));
		outputPane.getChildren().add(winningOutputBox());
		return outputPane;
	}
	
	private VBox winningOutputBox() {
		VBox rightInCenter = new VBox(20);
		//rightInCenter.setPadding(new Insets(0,0,0,100));
		rightInCenter.setAlignment(Pos.CENTER);
		rightInCenter.setMinSize(500, 500);
		rightInCenter.setMaxSize(500, 500);
		rightInCenter.setId("rightInCenter");
		rightInCenter.getChildren().add(rewardsLabel);
		rightInCenter.getChildren().add(totalMovesLabel());
		rightInCenter.getChildren().add(totalXpEarnedLabel());
		rightInCenter.getChildren().add(efficiencyLabel());
		rightInCenter.getChildren().add(cashWonLabel());
		rightInCenter.getChildren().add(bonusCashWonLabel());
		rightInCenter.getChildren().add(totalCashWonLabel());
		rightInCenter.getChildren().add(lootLabel());
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
				"XP Earned: ", "Efficiency: ", "Cash Won: +", "Bonus Cash: +",
				"Total Cash: +", "Loot Obtained:", "GRADE: ");
		List<Label> labelList = Arrays.asList(totalMovesLabel, totalXpEarnedLabel, 
				efficiencyLabel, cashWonLabel, bonusCashWonLabel, totalCashWonLabel, lootLabel, ratingLabel);
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
