package com.game.darquest.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChallengesSelectView {
	
	private List<Button> challengeButtons = new ArrayList<>();
	private List<String> buttonNames = Arrays.asList("Master of none", "Stop hitting yourself",
			"Every move you make", "The Thief", "Perfectionist", "Pyromania", "One shot one kill",
			"Lucky bastard", "Pincushion", "Grumble grumble");
	

	public ChallengesSelectView() {
		center();
	}
	
	private BorderPane center;
	private BorderPane center() {
		center = new BorderPane();
		center.setRight(rightBox());
		return center;
	}
	
	private HBox rightBox() {
		HBox testBox = new HBox();
		testBox.setMinSize(400,725);
		testBox.setAlignment(Pos.CENTER_LEFT);
		testBox.setBackground(View.getBackground(Color.BLACK));
		testBox.getChildren().add(sp());
		return testBox;
	}
	
	private ScrollPane sp() {
		ScrollPane sp = new ScrollPane();
		sp.setMaxSize(380, 700);
		sp.setMinSize(380, 700);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setContent(challengeSelectionBox());
		return sp;
	}
	
	private VBox challengeSelectionBox() {
		VBox challengeSelectionBox = new VBox();
		challengeSelectionBox.setAlignment(Pos.TOP_CENTER);
		challengeSelectionBox.setBackground(View.getBackground(Color.DARKGREEN));
		for (Integer i = 0; i < buttonNames.size(); i++) {
			Button b = View.getButton(buttonNames.get(i), i.toString());
			b.setMinWidth(350);
			challengeButtons.add(b);
			challengeSelectionBox.getChildren().add(challengeButtons.get(i));
		}
		
		return challengeSelectionBox;
	}

	public BorderPane getCenter() {
		return center;
	}
	
	public void addChallengeListener(EventHandler<ActionEvent> l) {
		challengeButtons.forEach(e->e.setOnAction(l));
	}
}
