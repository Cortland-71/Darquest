package com.game.darquest.view.fightClub;

import java.util.ArrayList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class FightClubHubView {
	
	private List<String> fightClubHubButtonTextList = Arrays.asList("Random Fight", "Challenges", 
			"Place a Bet", "Talk", "Fight Tips", "Leave");
	private List<Button> buttonList = new ArrayList<>();

	public FightClubHubView() {
		fightClubHubCenter();
		fightClubHubBottom();
	}
	
	private BorderPane centerPane;
	private BorderPane fightClubHubCenter() {
		centerPane = new BorderPane();
		centerPane.setBackground(View.getBackground(Color.PINK));
		centerPane.setCenter(center());
		centerPane.setMinSize(1300, 727);
		centerPane.setMaxSize(1300, 727);
		return centerPane;
	}
	
	public BorderPane getFightClubHubCenter() {
		return centerPane;
	}
	
	private HBox center() {
		HBox centerInCenter = new HBox();
		centerInCenter.setBackground(View.getBackground(Color.BLACK));
		centerInCenter.setAlignment(Pos.CENTER);
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/zom2.png"))));
		centerInCenter.getChildren().add(l);
		return centerInCenter;
	}
	
	//Bottom
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

}
