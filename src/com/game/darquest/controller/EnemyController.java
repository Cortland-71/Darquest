package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.rules.AttackRuleController;
import com.game.darquest.controller.rules.HealRuleController;
import com.game.darquest.controller.rules.Ruleable;
import com.game.darquest.controller.rules.StealRuleController;
import com.game.darquest.data.Enemy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private List<Ruleable> ruleList;
	private int index = 0;
	private int holder = 0;
	
	
	public EnemyController(Controller c) {
		this.c = c;
		this.ruleList = Arrays.asList(
				new AttackRuleController(this.c), 
				new StealRuleController(this.c),
				new HealRuleController(this.c));
	}
	
	int count = 0;
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
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
	
	
	private List<Integer> allScores = new ArrayList<>();
	private void moveOne() {
		System.out.println(enemy.getType().getName());
		allScores = enemy.getType().getAllScores();
		allScores.forEach(System.out::println);
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) > holder) {
				holder = allScores.get(i);
				index = i;
			}
		}
		
		System.out.println("index " + index);

		ruleList.get(index).getRule();
		updateAllStats();
	}
	
	private void moveTwo() {
		allScores = enemy.getType().getAllScores();
		allScores.forEach(System.out::println);
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) > holder) {
				holder = allScores.get(i);
				index = i;
			}
		}
		
		System.out.println("index " + index);
		ruleList.get(index).getRule();
		updateAllStats();
	}
	
	private void moveThree() {
		allScores = enemy.getType().getAllScores();
		allScores.forEach(System.out::println);
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) > holder) {
				holder = allScores.get(i);
				index = i;
			}
		}
		
		System.out.println("index " + index);
		ruleList.get(index).getRule();
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

	public Enemy getEnemy() {
		return enemy;
	}
	public List<Enemy> getEnemyList() {
		return enemyList;
	}
	
	public void rulesForEat() {
		double cashRequired = (getEnemy().getEat() + .1) * 150.5;
		if(getEnemy().getCash() >= cashRequired) {
			c.getFightClubController().runFire("eat", getEnemy());
			return;
		}
		c.getFightClubController().runFire("work", getEnemy());
	}
	
	public void rulesForSleep() {
		double workRequired = .1;
		if(getEnemy().getWork() >= workRequired) {
			c.getFightClubController().runFire("sleep", getEnemy());
			return;
		}
		c.getFightClubController().runFire("work", getEnemy());
	}
}
