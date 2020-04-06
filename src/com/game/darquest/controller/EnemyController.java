package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;

	
	public EnemyController(Controller c) {
		this.c = c;
	}
	
	int count = 0;
	
	public void enemyTurn(Person enemy) {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(500), 
						ae-> moveOne()),
				new KeyFrame(Duration.millis(500*2), 
						ae->moveTwo()),
				new KeyFrame(Duration.millis(500*3), 
						ae->moveThree()),
				new KeyFrame(Duration.millis(500*3), 
						ae-> enemyTurnEnd())
				);
			timeline.playFromStart();
	}
	
	
	private List<String> commandList = Arrays.asList("eat", "sleep", "work", "attack 0", "steal 0");
	
	private void moveOne() {
		//c.getRulesController().rulesForAttack();
		updateAllStats();
	}
	
	private void moveTwo() {
		//c.getRulesController().rulesForAttack();
		updateAllStats();
	}
	
	private void moveThree() {
		//c.getRulesController().rulesForAttack();
		updateAllStats();
	}
	
	private void updateAllStats() {
		List<Enemy> list = c.getFightClubController().getEnemyList();
		c.getView().getFightClubView().getCenterEnemyBox().getChildren().clear();
		c.getDownTownController().drawAllEnemyBoxes(list);
		c.getPlayerInvStatsController().updateAllPlayerStats();
	}
	
	private void enemyTurnEnd() {
		count = 0;
		c.getView().getFightClubView().setDisableCommandField(false);
		c.getView().getFightClubView().setCommandFeildFocused();
		c.getPlayer().setMoves(c.getPlayer().getMaxMoves());
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
	}
}
