package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Weapon;
import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class DownTownController implements EventHandler<ActionEvent>{

	
	private View view;
	private Controller c;
	
	private FightClub fightClub;
	private Shop shop;
	private Email email;
	private MainMenu mainMenu;
	private Save save;
	private Tutorial tutorial;
	private List<Clickable> downTownActions;
	
	public DownTownController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getDownTownView().addActionListener(this);
		
		fightClub = new FightClub(this.c);
		shop = new Shop(this.c);
		email = new Email(this.c);
		mainMenu = new MainMenu(this.c);
		save = new Save(this.c);
		tutorial = new Tutorial(this.c);
		
		downTownActions = Arrays.asList(fightClub, shop, email, mainMenu, save, tutorial);
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
		c.getView().getShopView().getShopTabPane().getSelectionModel().selectFirst();
		c.getView().getShopView().getShopListViewObjects().forEach(o->o.getSelectionModel().selectFirst());
		c.getShopInventoryController().setSelectedShopItemToLabel();
		
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

