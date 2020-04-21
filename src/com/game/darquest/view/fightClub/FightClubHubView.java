package com.game.darquest.view.fightClub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.view.ChallengesSelectView;
import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class FightClubHubView {
	
	private List<String> fightClubHubButtonTextList = Arrays.asList("Random Fight", "Challenges", 
			"Place a Bet", "Talk", "Fight Tips", "Leave");
	private List<Button> buttonList = new ArrayList<>();

	private ChallengesSelectView challengesSelectView;
	
	public FightClubHubView() {
		fightClubHubCenter();
		fightClubHubBottom();
		challengesSelectView = new ChallengesSelectView();
	}
	
	private StackPane centerPane;
	private StackPane fightClubHubCenter() {
		centerPane = new StackPane();
		centerPane.setAlignment(Pos.BOTTOM_CENTER);
		centerPane.setBackground(View.getBackground(Color.PINK));
		centerPane.getChildren().add(center());
		centerPane.getChildren().add(zomTextArea());
		centerPane.setMinSize(1300, 700);
		centerPane.setMaxSize(1300, 700);
		return centerPane;
	}
	
	public StackPane getFightClubHubCenter() {
		return centerPane;
	}
	
	private HBox center() {
		HBox centerInCenter = new HBox();
		centerInCenter.setBackground(View.getBackground(Color.BLACK));
		centerInCenter.setAlignment(Pos.CENTER);
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/zom.png"))));
		centerInCenter.getChildren().add(l);
		return centerInCenter;
	}
	
	private TextArea zomTextArea;
	private TextArea zomTextArea() {
		zomTextArea = new TextArea();
		zomTextArea.setPadding(new Insets(5,5,5,5));
		zomTextArea.setId("zomTextArea");
		zomTextArea.setWrapText(true);
		zomTextArea.setEditable(false);
		zomTextArea.setMinSize(1100,150);
		zomTextArea.setMaxSize(1100,150);
		return zomTextArea;
	}
	
	public void setZomTextArea(String text) {
		zomTextArea.setText(text);
	}
	
	//Bottom \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private GridPane bottomPane;
	private GridPane fightClubHubBottom() {
		int counter = 0;
		bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setPadding(new Insets(20, 0, 120, 0));
		bottomPane.setVgap(15);
		bottomPane.setHgap(15);
		for (int i = 0; i < fightClubHubButtonTextList.size(); i++) {
			buttonList.add(View.getButton(fightClubHubButtonTextList.get(i), ((Integer) i).toString()));

			if (i < 3) {
				bottomPane.add(buttonList.get(i), i, 0);
				continue;
			}
			bottomPane.add(buttonList.get(i), counter++, 1);
		}
		return bottomPane;
	}
	
	public GridPane getFightClubHubBottom() {
		return bottomPane;
	}
	
	public void addActionListener(EventHandler<ActionEvent> l) {
		buttonList.forEach(e -> e.setOnAction(l));
	}
	
	public List<Button> getButtonList() {
		return buttonList;
	}

	public ChallengesSelectView getChallengesSelectView() {
		return challengesSelectView;
	}

}
