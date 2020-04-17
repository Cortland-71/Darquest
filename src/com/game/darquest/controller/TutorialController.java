package com.game.darquest.controller;

import com.game.darquest.data.TutorialData;
import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TutorialController implements EventHandler<ActionEvent> {
	
	private View view;
	private Controller c;
	public TutorialController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		view.getTutorialView().setFullTutorialOutput(TutorialData.getTutorialData());
		view.getTutorialView().addActionEvent(this);
	
	}
	
	@Override
	public void handle(ActionEvent e) {
		if(((Button)e.getSource()).getId().equals("back")) {
			view.getWindow().setScene(view.getHubView().getDownTownScene());
			return;
		}
		String id = ((Button)e.getSource()).getId();
		view.getTutorialView().searchTutorialOutput(id);
	}
}
