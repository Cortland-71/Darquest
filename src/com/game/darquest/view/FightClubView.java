package com.game.darquest.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class FightClubView extends DownTownView {
	
	private Scene fightClubScene;

	public FightClubView() {
		fightClubScene = new Scene(fightClubPane(), View.WIDTH, View.HEIGHT);
		fightClubScene.getStylesheets().addAll("DownTownStyle.css");
	}
	
	private BorderPane fightClubPane() {
		BorderPane fightClubPane = new BorderPane();
		fightClubPane.setTop(topBox());
		fightClubPane.setLeft(leftBox());
		fightClubPane.setRight(rightPane());
		return fightClubPane;
	}
	
	public Scene getFightClubScene() {
		return this.fightClubScene;
	}

}
