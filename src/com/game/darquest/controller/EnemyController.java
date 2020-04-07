package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.rules.AttackRuleController;
import com.game.darquest.controller.rules.DeceptionRuleController;
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
	
	private List<Integer> allScores = new ArrayList<>();
	private int holder = 0;
	
	private DeceptionRuleController deceptionRuleController;
	
	
	public EnemyController(Controller c) {
		this.c = c;
		deceptionRuleController = new DeceptionRuleController(this.c);
		this.ruleList = Arrays.asList(
				new AttackRuleController(this.c), 
				new StealRuleController(this.c),
				new HealRuleController(this.c),
				deceptionRuleController);
		
	}
	
	public DeceptionRuleController getDeceptionRuleController() {
		return deceptionRuleController;
	}

	int count = 0;
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(500), 
						ae-> move()),
				new KeyFrame(Duration.millis(500*2), 
						ae-> move()),
				new KeyFrame(Duration.millis(500*3), 
						ae-> move()),
				new KeyFrame(Duration.millis(500*3), 
						ae-> enemyTurnEnd())
				);
			timeline.playFromStart();
	}
	

	private void move() {
		System.out.println(enemy.getType().getName());
		allScores = enemy.getType().getAllScores();
		int index = getChoosenActionIndex();
		ruleList.get(index).getRule();
		updateAllStats();
	}
	
	
	private int getChoosenActionIndex() {
		List<Integer> highScoreIndexes = new ArrayList<>();
		int choosenIndex = 0;
		holder = 0;
		Random rand = new Random();
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) > holder) {
				holder = allScores.get(i);
				choosenIndex = i;
			}
		}
		
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) == holder) {
				highScoreIndexes.add(i);
			}
		}
		
		highScoreIndexes.forEach(e->System.out.println("Enemy Controller: High Score index: " + e));
		if(highScoreIndexes.size() > 1) 
			choosenIndex = highScoreIndexes.get(rand.nextInt(highScoreIndexes.size()));
		
		System.out.println("Choosen index: " + choosenIndex + "\n");
		return choosenIndex;
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
		rulesForWork();
	}
	
	public void rulesForSleep() {
		double workRequired = .1;
		if(getEnemy().getWork() >= workRequired) {
			c.getFightClubController().runFire("zz", getEnemy());
			return;
		}
		rulesForWork();
	}
	
	public void rulesForWork() {
		c.getFightClubController().runFire("wq", getEnemy());
	}
}
