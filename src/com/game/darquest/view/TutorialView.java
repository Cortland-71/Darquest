package com.game.darquest.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TutorialView {
	
	private List<Button> tutorialButtonList = new ArrayList<>();
	private List<String> tutorialNameList = Arrays.asList( 
			"Show All", "Help", "Eat", "Sleep", "Work", "Attack", "Steal", "Heal", "Kill");
	private List<String> tutorialButtonIDList = Arrays.asList(
			"all","Command: help", "Command: eat", "Command: sleep", "Command: work", 
			"Command: attack", "Command: steal", "Command: heal", "Command: kill");
	
	private String fullOutput;
	private Scene tutorialScene;
	private Label titleLabel = new Label("How to play");
	
	public TutorialView() {
		tutorialScene = new Scene(getTutorialPane(), View.WIDTH, View.HEIGHT);
		tutorialScene.getStylesheets().add("TutorialStyle.css");
	}
	
	public BorderPane getTutorialPane() {
		BorderPane borderPane = new BorderPane();
		borderPane.setBackground(View.getBackground(Color.BLACK));
		borderPane.setBottom(bottomBox());
		borderPane.setLeft(leftBox());
		borderPane.setCenter(centerBox());
		borderPane.setRight(rightBox());
		return borderPane;
	}
	
	private HBox bottomBox() {
		HBox bottomBox = new HBox();
		bottomBox.setPadding(new Insets(0,0,50,0));
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().add(back());
		return bottomBox;
	}
	
	private Button back;
	private Button back() {
		back = new Button("Back");
		back.setId("back");
		back.setMinSize(150, 30);
		back.setTextFill(Color.WHITE);
		return back;
	}
	
	private VBox leftBox() {
		VBox leftBox = new VBox(10);
		leftBox.setPadding(new Insets(0,20,0,20));
		leftBox.setAlignment(Pos.CENTER);
		for (int i = 0; i < tutorialNameList.size(); i++) {
			tutorialButtonList.add(View.getButton(tutorialNameList.get(i), tutorialButtonIDList.get(i)));
			leftBox.getChildren().add(tutorialButtonList.get(i));
		}
		return leftBox;
	}
	
	private VBox rightBox() {
		VBox leftBox = new VBox();
		leftBox.setPadding(new Insets(0,0,0,0));
		leftBox.setMinSize(150, 0);
		return leftBox;
	}
	
	private VBox centerBox() {
		VBox centerBox = new VBox(20);
		centerBox.setPadding(new Insets(0,0,0,0));
		centerBox.setAlignment(Pos.CENTER);
		titleLabel.setId("tutorialTitle");
		centerBox.getChildren().add(titleLabel);
		centerBox.getChildren().add(tipsOutput());
		return centerBox;
	}
	
	
	private TextArea tutorialOutput; 
	private TextArea tipsOutput() {
		tutorialOutput = new TextArea();
		tutorialOutput.setPrefHeight(600);
		tutorialOutput.setWrapText(true);
		tutorialOutput.setEditable(false);
		return tutorialOutput;
	}
	
	public void searchTutorialOutput(String search) {
		if(search.equals("all")) {
			tutorialOutput.setText(fullOutput);
			return;
		}
		int a = fullOutput.indexOf(search);
		String sub = fullOutput.substring(a);
		int b = sub.indexOf("****");
		tutorialOutput.setText(sub.substring(0, b));
	}
	
	public void setFullTutorialOutput(String fullOutput) {
		this.fullOutput = fullOutput;
		tutorialOutput.setText(this.fullOutput);
	}
	
	public void addActionEvent(EventHandler<ActionEvent> l) {
		tutorialButtonList.forEach(e->e.setOnAction(l));
		back.setOnAction(l);
	}
	
	public Scene getTutorialScene() {
		return this.tutorialScene;
	}
}
