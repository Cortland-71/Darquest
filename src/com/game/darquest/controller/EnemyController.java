package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;
	private Random rand = new Random();
	
	public EnemyController(Controller c) {
		this.c = c;
	}
	
	int count = 0;
	
	public void enemyTurn() {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(500), 
						ae-> enemyWork()),
				new KeyFrame(Duration.millis(500*2), 
						ae->enemyWork()),
				new KeyFrame(Duration.millis(500*3), 
						ae->enemyWork()),
				new KeyFrame(Duration.millis(500*3), 
						ae-> enemyTurnEnd())
				);
			timeline.playFromStart();
	}
	
	private List<String> commandList = Arrays.asList("eat", "sleep", "work", "attack");
	
	private void enemyWork() {
		c.getFightClubController().runFire(
				commandList.get(rand.nextInt(commandList.size())), 
				c.getFightClubController().getEnemyList().get(0));
	}
	
	private void enemyTurnEnd() {
		count = 0;
		c.getView().getFightClubView().setDisableCommandField(false);
		c.getView().getFightClubView().setCommandFeildFocused();
		c.getPlayer().setMoves(c.getPlayer().getMaxMoves());
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
	}
	
}
