package com.game.darquest.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class LevelUpView {

	private BorderPane levelUpPane;
	public BorderPane levelUpPane() {
		Label l = new Label("Really doing this");
		l.setStyle("-fx-text-fill: red");
		levelUpPane = new BorderPane();
		levelUpPane.setBackground(View.getBackground(Color.PINK));
		levelUpPane.setCenter(l);
		return levelUpPane;
	}

}
