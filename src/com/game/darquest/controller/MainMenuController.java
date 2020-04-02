package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class MainMenuController implements EventHandler<ActionEvent> {
	
	private Controller c;
	private View view;
	private List<Clickable> mainMenuOptionsList;
	
	public MainMenuController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getMainMenu().addButtonAction(this);
		
		mainMenuOptionsList = Arrays.asList(new NewGame(this.view)
				, new Load(), new Quit(this.view));
	}

	@Override
	public void handle(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		mainMenuOptionsList.get(eventIndex).clickAction();
	}
}

//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/ -Action Classes for Main Menu
class NewGame implements Clickable {
	
	private View view;
	public NewGame(View view) {
		this.view = view;
	}
	
	@Override
	public void clickAction() {
		view.getWindow().setScene(view.getNewPlayerView().getEnterNameScene());
	}
}

class Load implements Clickable {
	@Override
	public void clickAction() {
		System.out.println("Clicked load");
	}
}

class Quit implements Clickable {
	private View view;
	public Quit(View view) {
		this.view = view;
	}
	@Override
	public void clickAction() {
		System.out.println("Clicked quit");
		view.getWindow().close();
	}
}

