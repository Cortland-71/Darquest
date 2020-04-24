package com.game.darquest.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AppartmentView {
	
	private List<String> appartmentButtonTextList = Arrays.asList("Search", "Email", 
			"Music", "Stats", "Rest", "Leave");
	private List<Button> buttonList = new ArrayList<>();

	public AppartmentView() {
		bottom();
	}
	
	//Bottom \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private GridPane bottom;
	private GridPane bottom() {
		int counter = 0;
		bottom = new GridPane();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(20, 0, 120, 0));
		bottom.setVgap(15);
		bottom.setHgap(15);
		for (int i = 0; i < appartmentButtonTextList.size(); i++) {
			buttonList.add(View.getButton(appartmentButtonTextList.get(i), ((Integer) i).toString()));

			if (i < 3) {
				bottom.add(buttonList.get(i), i, 0);
				continue;
			}
			bottom.add(buttonList.get(i), counter++, 1);
		}
		return bottom;
	}
	
	public GridPane getBottom() {
		return bottom;
	}
	
	public void addActionListener(EventHandler<ActionEvent> l) {
		buttonList.forEach(e -> e.setOnAction(l));
	}

}
