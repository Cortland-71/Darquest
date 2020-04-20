package com.game.darquest.controller.fightClubControllers;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.Clickable;
import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.EnemyGenerator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class FightClubHubController implements EventHandler<ActionEvent> {
	
	private Controller c;
	private List<Clickable> fightClubHubActions;
	private RandomFight randomFight;
	private Challenges challenges;
	private PlaceBet placeBet;
	private Talk talk;
	private FightTips fightTips;
	private Leave leave;

	public FightClubHubController(Controller c) {
		this.c = c;
		this.randomFight = new RandomFight(this.c);
		this.challenges = new Challenges(this.c);
		this.placeBet = new PlaceBet(this.c);
		this.talk = new Talk(this.c);
		this.fightTips = new FightTips(this.c);
		this.leave = new Leave(this.c);
		
		fightClubHubActions = Arrays.asList(randomFight, challenges, placeBet, talk, fightTips, leave);
		this.c.getView().getFightClubHub().addActionListener(this);
	}

	@Override
	public void handle(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		fightClubHubActions.get(eventIndex).clickAction();
		
	}

}

class RandomFight implements Clickable {
	private EnemyGenerator enemyGenerator;
	private Controller c;
	public RandomFight(Controller c) {
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
		c.getView().getHubView().showRandomFight();
		c.getView().getFightClubView().setCommandFeildFocused();
		
	}
}

class Challenges implements Clickable {
	private Controller c;
	public Challenges(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Challenges");
	}
}

class PlaceBet implements Clickable {
	private Controller c;
	public PlaceBet(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("PlaceBet");
	}
}

class Talk implements Clickable {
	private Controller c;
	public Talk(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Talk");
	}
}

class FightTips implements Clickable {
	private Controller c;
	public FightTips(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Fight Tips");
	}
}

class Leave implements Clickable {
	private Controller c;
	public Leave(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Leave");
		
	}
}
