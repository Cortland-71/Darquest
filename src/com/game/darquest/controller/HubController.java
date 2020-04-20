package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.EnemyGenerator;
import com.game.darquest.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
	
public class HubController implements EventHandler<ActionEvent> {

	
	private View view;
	private Controller c;
	
	private FightClub fightClub;
	private Shop shop;
	private Email email;
	private LevelUp levelUp;
	private Save save;
	private Tutorial tutorial;
	private List<Clickable> downTownActions;
	
	public HubController(Controller c) {
		this.c = c;
		this.view = this.c.getView();
		this.view.getHubView().addActionListener(this);
		
		fightClub = new FightClub(this.c);
		shop = new Shop(this.c);
		email = new Email(this.c);
		levelUp = new LevelUp(this.c);
		save = new Save(this.c);
		tutorial = new Tutorial(this.c);
		
		downTownActions = Arrays.asList(fightClub, shop, email, levelUp, save, tutorial);
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
	
	public void drawAllEnemyBoxes(List<Enemy> list) {
		c.getView().getFightClubView().getCenterEnemyBox().getChildren().clear();
		for (int i = 0; i < list.size(); i++) {
			c.getView().getFightClubView().getCenterEnemyBox().getChildren()
			.add(c.getView().getFightClubView().getInnerEnemyPane(list.get(i).getImagePath()));
			c.getView().getFightClubView().setEnemyStats(list.get(i), c.getPlayer());
		}
	}
}

//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/ -Action Classes for Main Menu
class FightClub implements Clickable {
	private EnemyGenerator enemyGenerator;
	private Controller c;
	public FightClub(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		enemyGenerator = new EnemyGenerator(this.c);
		enemyGenerator.generateEnemys();
		List<Enemy> enemyList = enemyGenerator.getEnemyList();
		c.getPlayerWinController().setAllCountersToDefault();
		c.getFightClubController().setEnemyList(enemyList);
		
		c.getHubController().drawAllEnemyBoxes(enemyList);
		
		c.getPlayer().setMoves(3);
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
		c.getView().getFightClubView().clearAllOutputTextAreas();
		c.getView().getFightClubView().clearCommandField();
		c.getView().getHubView().showFightClub();
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
		 
		c.getView().getHubView().showShop();
		c.getView().getShopView().getShopTabPane().getSelectionModel().selectFirst();
		c.getView().getShopView().getShopListViewObjects().forEach(o->o.getSelectionModel().selectFirst());
		c.getShopInventoryController().setSelectedShopItemToTextArea();
		c.getView().getShopView().setBuyShopDialogeTextArea("Odette: Welcome, please have a look around.");
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

class LevelUp implements Clickable {
	private Controller c;
	public LevelUp(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		c.getView().getHubView().showLevelUp();
		c.getLevelUpController().setPointsAvailable(3);
		c.getView().getLevelUpView().setLevelUpInfoLabel(c.getLevelUpController().getPointsAvailable());
		
		c.getView().getLevelUpView().getDefFactory().setMax(c.getPlayer().getDef() + c.getLevelUpController().getPointsAvailable());
		c.getView().getLevelUpView().getDefFactory().setMin(c.getPlayer().getDef());
		c.getView().getLevelUpView().getDefFactory().setValue(c.getPlayer().getDef());
		
		c.getView().getLevelUpView().getStealthFactory().setMax(c.getPlayer().getStealth() + c.getLevelUpController().getPointsAvailable());
		c.getView().getLevelUpView().getStealthFactory().setMin(c.getPlayer().getStealth());
		c.getView().getLevelUpView().getStealthFactory().setValue(c.getPlayer().getStealth());
		
		c.getView().getLevelUpView().getAwarFactory().setMax(c.getPlayer().getAwareness() + c.getLevelUpController().getPointsAvailable());
		c.getView().getLevelUpView().getAwarFactory().setMin(c.getPlayer().getAwareness());
		c.getView().getLevelUpView().getAwarFactory().setValue(c.getPlayer().getAwareness());
		
		c.getView().getLevelUpView().setDefSpinnerListener(c.getLevelUpController().getDluc());
		c.getView().getLevelUpView().setStealthSpinnerListener(c.getLevelUpController().getSluc());
		c.getView().getLevelUpView().setAwarSpinnerListener(c.getLevelUpController().getAwaruc());
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

