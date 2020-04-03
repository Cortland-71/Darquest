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
	private Random rand = new Random();
	private Person currentEnemy;
	
	public EnemyController(Controller c) {
		this.c = c;
	}
	
	int count = 0;
	
	public void enemyTurn(Person enemy) {
		this.currentEnemy = enemy;
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
	
	private List<String> commandList = Arrays.asList("eat", "sleep", "work", "attack 0");
	
	private void moveOne() {
		rulesForAttack();
		updateAllStats();
	}
	
	private void moveTwo() {
		rulesForAttack();
		updateAllStats();
	}
	
	private void moveThree() {
		rulesForAttack();
		updateAllStats();
	}
	
	private void rulesForAttack() {
		double eatRequired = .1;
		double sleepRequired = .1;
		
		if(currentEnemy.getEat() < eatRequired) {
			rulesForEat();
			return;
		} else if(currentEnemy.getSleep() < sleepRequired) {
			rulesForSleep();
			return;
		}
		if(currentEnemy.getEng() >= .5 && currentEnemy.getEng() < 1) {
			boolean coinFlip = rand.nextBoolean();
			if(coinFlip) {
				rulesForSleep();
				return;
			} 
			c.getFightClubController().runFire("attack 0", currentEnemy);
			return;
		}
		int coinFlip = rand.nextInt(10);
		if(coinFlip < 7) {
			rulesForSleep();
			return;
		} 
		c.getFightClubController().runFire("attack 0", currentEnemy);
		
	}
	
	private void rulesForEat() {
		double cashRequired = (currentEnemy.getEat() + .1) * 150.5;
		if(currentEnemy.getCash() >= cashRequired) {
			c.getFightClubController().runFire("eat", currentEnemy);
			return;
		}
		c.getFightClubController().runFire("work", currentEnemy);
	}
	
	private void rulesForSleep() {
		double workRequired = .1;
		if(currentEnemy.getWork() >= workRequired) {
			c.getFightClubController().runFire("sleep", currentEnemy);
			return;
		}
		c.getFightClubController().runFire("work", currentEnemy);
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
