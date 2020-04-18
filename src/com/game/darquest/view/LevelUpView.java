package com.game.darquest.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LevelUpView {
	
	public LevelUpView() {
		levelUpBottom();
		levelUpCenter();
	}

	private BorderPane levelUpPane;
	public BorderPane levelUpCenter() {
		levelUpPane = new BorderPane();
		levelUpPane.setCenter(stackPane());
		return levelUpPane;
	}
	
	private StackPane stackPane() {
		StackPane stackPane = new StackPane();
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTownSoft.png"))));
		stackPane.getChildren().add(l);
		stackPane.getChildren().add(actualLevelUpBox());
		return stackPane;
	}
	
	private BorderPane actualLevelUpBox() {
		BorderPane actualLevelUpBox = new BorderPane();
		actualLevelUpBox.setId("levelUpCenterBox");
		actualLevelUpBox.setMinSize(550,350);
		actualLevelUpBox.setMaxSize(550,350);
		actualLevelUpBox.setTop(topBox());
		actualLevelUpBox.setCenter(centerBox());
		return actualLevelUpBox;
	}
	
	private HBox topBox() {
		HBox topBox = new HBox();
		topBox.setAlignment(Pos.CENTER);
		topBox.getChildren().add(levelUpTitle());
		return topBox;
	}
	
	private Label levelUpTitle() {
		Label levelUpTitle = new Label();
		levelUpTitle.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/levelLogo.png"))));
		return levelUpTitle;
	}
	
	private VBox centerBox() {
		VBox centerBox = new VBox();
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().add(levelUpInfo());
		centerBox.getChildren().add(spinnerGrid());
		return centerBox;
	}
	
	private Label levelUpInfo;
	private Label levelUpInfo() {
		levelUpInfo = new Label();
		levelUpInfo.setPadding(new Insets(0,0,20,0));
		return levelUpInfo;
	}
	
	public void setLevelUpInfoLabel(int points) {
		levelUpInfo.setText("You have " + points + " points to spend.");
	}
	
	private GridPane spinnerGrid() {
		GridPane spinnerGrid = new GridPane();
		spinnerGrid.setAlignment(Pos.CENTER);
		spinnerGrid.add(levelUpDef(), 0, 0);
		spinnerGrid.add(defSpinner(), 1, 0);
		spinnerGrid.add(levelUpStealth(), 0, 1);
		spinnerGrid.add(stealthSpinner(), 1, 1);
		spinnerGrid.add(levelUpAwareness(), 0, 2);
		spinnerGrid.add(awarenessSpinner(), 1, 2);
		return spinnerGrid;
	}
	
	private final int spinnerWidth = 70;
	private final int spinnerHeight = 35;
	
	private Label levelUpDef() {
		Label levelUpDef = new Label("Defense");
		levelUpDef.setPadding(new Insets(20,5,10,0));
		return levelUpDef;
	}
	
	private Spinner<Integer> defSpinner;
	private Spinner<Integer> defSpinner() {
		defSpinner = new Spinner<>();
		defSpinner.setId("defSpinner");
		defSpinner.setMinSize(spinnerWidth, spinnerHeight);
		defSpinner.setMaxSize(spinnerWidth, spinnerHeight);
		
		return defSpinner;
	}
	

	
	private Label levelUpStealth() {
		Label levelUpTitle = new Label("Stealth");
		levelUpTitle.setPadding(new Insets(20,5,10,0));
		return levelUpTitle;
	}
	
	private Spinner<Integer> stealthSpinner;
	private Spinner<Integer> stealthSpinner() {
		stealthSpinner = new Spinner<>();
		stealthSpinner.setId("stealthSpinner");
		stealthSpinner.setMinSize(spinnerWidth, spinnerHeight);
		stealthSpinner.setMaxSize(spinnerWidth, spinnerHeight);
		return stealthSpinner;
	}
	private Label levelUpAwareness() {
		Label levelUpTitle = new Label("Awareness");
		levelUpTitle.setPadding(new Insets(20,5,10,0));
		return levelUpTitle;
	}
	private Spinner<Integer> awarenessSpinner() {
		Spinner<Integer> awarenessSpinner = new Spinner<>();
		awarenessSpinner.setMinSize(spinnerWidth, spinnerHeight);
		awarenessSpinner.setMaxSize(spinnerWidth, spinnerHeight);
		return awarenessSpinner;
	}
	
	public void setDefSpinner(SpinnerValueFactory<Integer> factory, ChangeListener<Integer> l) {
		defSpinner.setValueFactory(factory);
		defSpinner.valueProperty().addListener(l);
	}

	public void setStealthSpinner(SpinnerValueFactory<Integer> factory, ChangeListener<Integer> l) {
		stealthSpinner.setValueFactory(factory);
		stealthSpinner.valueProperty().addListener(l);
		
	}
	
	//Bottom
	private HBox levelUpBottom;
	private HBox levelUpBottom() {
		levelUpBottom = new HBox();
		levelUpBottom.setAlignment(Pos.CENTER);
		levelUpBottom.setPadding(new Insets(20, 0, 185, 0));
		levelUpBottom.getChildren().add(finishButton());
		return levelUpBottom;
	}
	
	public HBox getLevelUpBottom() {
		return levelUpBottom;
	}
	
	private Button finishButton;
	private Button finishButton() {
		finishButton = new Button("Finish");
		finishButton.setMinSize(225, 50);
		finishButton.setMaxSize(225, 50);
		return finishButton;
	}

}
