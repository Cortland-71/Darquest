package com.game.darquest.view.fightClub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.view.View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChallengesView {
	
	private List<Label> challengeLabels = new ArrayList<>();
	private List<String> labelNames = Arrays.asList("One for the money");
	

	public ChallengesView() {
		center();
	}
	
	private BorderPane center;
	private BorderPane center() {
		center = new BorderPane();
		center.setRight(rightBox());
		return center;
	}
	
	private VBox rightBox() {
		VBox testBox = new VBox(10);
		testBox.setMinSize(420,700);
		testBox.setAlignment(Pos.CENTER);
		testBox.getChildren().add(counterLabel());
		testBox.getChildren().add(sp());
		return testBox;
	}
	
	private Label counterLabel;
	private Label counterLabel() {
		counterLabel = new Label("something");
		counterLabel.setId("counterLabel");
		return counterLabel;
	}
	public void setCounterLabel(int compleated) {
		counterLabel.setText("Challenges compleated:  " + compleated + " / " + challengeLabels.size());
	}
	
	private ScrollPane sp() {
		ScrollPane sp = new ScrollPane();
		sp.setId("scrollPane");
		sp.setMaxSize(380, 675);
		sp.setMinSize(380, 675);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setContent(challengeSelectionBox());
		return sp;
	}
	
	private VBox challengeSelectionBox;
	private VBox challengeSelectionBox() {
		challengeSelectionBox = new VBox();
		
		challengeSelectionBox.setAlignment(Pos.TOP_CENTER);
		for (Integer i = 0; i < labelNames.size(); i++) {
			Label l = getLabel(labelNames.get(i), i);
			l.setId("challengeLabels");
			challengeLabels.add(l);
			challengeSelectionBox.getChildren().add(challengeLabels.get(i));
		}
		return challengeSelectionBox;
	}
	
	public VBox getChallengeSelectionBox() {
		return challengeSelectionBox;
	}
	private Label getLabel(String text, int id) {
		Label l = new Label(text);
		l.setPadding(new Insets(0,0,0,20));
		l.setMaxSize(355, 40);
		l.setMinSize(355, 40);
		l.setId(Integer.toString(id));
		return l;
	}

	
	public BorderPane getCenter() { return center; }
	 
	
	public void addChallengeListener(EventHandler<MouseEvent> l) {
		challengeLabels.forEach(e->e.setOnMouseEntered(l));
	}
	
	public List<Label> getChallengeLabels() {
		return challengeLabels;
	}
}
