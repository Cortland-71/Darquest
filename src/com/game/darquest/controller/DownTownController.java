package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Person;
import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class DownTownController implements EventHandler<ActionEvent>{

	private List<Clickable> downTownActions;
	private Person player;
	private View view;
	private Controller c;
	
	public DownTownController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getDownTownView().addActionListener(this);
		downTownActions = Arrays.asList(new FightClub(this.c), new Shop(this.c),
				new Email(this.c), new MainMenu(this.c), new Save(this.c), new Tutorial(this.c));
	}
	
	public List<Clickable> getDownTownActions() {
		return downTownActions;
	}

	@Override
	public void handle(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		downTownActions.get(eventIndex).clickAction();
	}
	
	public void setPlayer(Person player) {
		this.player = player;
		setPlayerStats();
	}
	
	public void setPlayerStats() {
		view.getDownTownView().setPlayerStats(this.player);
		view.getShopView().setPlayerStats(this.player);
	}
}

//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/ -Action Classes for Main Menu
class FightClub implements Clickable {
	
	private Controller c;
	public FightClub(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Clicked Fight club");
	}
}

class Shop implements Clickable {
	private Controller c;
	public Shop(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Clicked shop");
		c.getView().getWindow().setScene(c.getView().getShopView().getShopScene());
		c.getView().getShopView().getListViewObjects().forEach(o->o.getSelectionModel().select(0));
	}
}

class Email implements Clickable {
	private Controller c;
	public Email(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Clicked email");
	}
}

class MainMenu implements Clickable {
	private Controller c;
	public MainMenu(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Clicked mainMenu");
		c.getView().getWindow().setScene(c.getView().getMainMenu().getMainMenuScene());
	}
}

class Save implements Clickable {
	private Controller c;
	public Save(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Clicked save");
	}
}

class Tutorial implements Clickable {
	private Controller c;
	public Tutorial(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Clicked tips");
		c.getView().getWindow().setScene(c.getView().getTutorialView().getTutorialScene());
	}
}

