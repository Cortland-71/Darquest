package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.EnemyGenerator;
import com.game.darquest.view.FightClubView;
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
	private EnemyGenerator enemyGenerator;
	private Controller c;
	public FightClub(Controller c) {
		this.c = c;
		this.enemyGenerator = new EnemyGenerator(this.c);
	}
	
	@Override
	public void clickAction() {
		enemyGenerator.generateEnemys();
		List<Enemy> enemyList = enemyGenerator.getEnemyList();
		c.getFightClubController().setEnemyList(enemyList);
		
		for (int i = 0; i < enemyList.size(); i++) {
			c.getView().getFightClubView().getCenterEnemyBox().getChildren()
			.add(c.getView().getFightClubView().getInnerEnemyPane());
			c.getView().getFightClubView().setEnemyStats(enemyList.get(i));
		}
		c.getPlayer().setMoves(3);
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
		c.getView().getWindow().setScene(c.getView().getFightClubView().getFightClubScene());
		c.getView().getFightClubView().setCommandFeildFocused();
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
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: Welcome, please have a look around.");
		c.getView().getShopView().setSellShopDialogeTextArea("Select an item from your inventory and "
				+ "click the \"Sell\" Button to sell it.");
		
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

