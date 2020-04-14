package com.game.darquest.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
		return fightWinPane;
	}

}
