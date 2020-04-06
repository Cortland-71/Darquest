package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Enemy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;
	private Enemy enemy;

	
	public EnemyController(Controller c) {
		this.c = c;
	}
	
	int count = 0;
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		c.getRuleController().setCurrentEnemy(enemy);
		c.getRuleController().setEnemyList(enemyList);
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
	private List<Integer> allScores = new ArrayList<>();
	private void moveOne() {
		System.out.println(enemy.getType().getName());
		allScores = enemy.getType().getAllScores();
		allScores.forEach(System.out::println);
		updateAllStats();
	}
	
	private void moveTwo() {
		allScores = enemy.getType().getAllScores();
		allScores.forEach(System.out::println);
		updateAllStats();
	}
	
	private void moveThree() {
		allScores = enemy.getType().getAllScores();
		allScores.forEach(System.out::println);
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
