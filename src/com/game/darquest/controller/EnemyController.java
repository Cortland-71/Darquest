package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.actions.Fireable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private int points = 0;
	int count = 0;
	
	public EnemyController(Controller c) {
		this.c = c;
	}

	
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		Timeline timeline = new Timeline();
		
		if(points < 5) points++;
		timeline.setCycleCount(points);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), 
				e-> {
					//figure out how many points they have and what moves they can do
					int numberOfPoints = timeline.getCycleCount();
					move(numberOfPoints);
					}));
		
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	enemyTurnEnd();
		    	System.out.println();
		    }
		});
	}
	

	private void move(int numberOfPoints) {
		
		List<Fireable> list = determineMoves(numberOfPoints);
		Fireable choosenMove = getBestMove(list);
		choosenMove.fire(enemy, c.getPlayer());
		c.getView().getFightClubView().setEnemyOutputTextArea(choosenMove.getOutput());
		updateAllStats();
		if(c.getPlayer().getHp() <= 0) {
			c.getView().getHubView().showHub();
		}
	}
	
	private Fireable getBestMove(List<Fireable> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}



	private List<Fireable> determineMoves(int numberOfPoints) {
		List<Fireable> fireList = c.getFightClubController().getFireList();
		List<Fireable> availibleFireList = fireList.stream()
				.filter(e->e.getPointCost() <= numberOfPoints)
				.collect(Collectors.toList());
		
		return availibleFireList;
	}
	
	
	
	private void updateAllStats() {
		List<Enemy> list = c.getFightClubController().getEnemyList();
		c.getView().getFightClubView().getCenterEnemyBox().getChildren().clear();
		c.getHubController().drawAllEnemyBoxes(list);
		c.getPlayerInvStatsController().updateAllPlayerStats();
	}
	
	private void enemyTurnEnd() {
		count = 0;
		c.getView().getFightClubView().setCommandFieldDisabled(false);
		c.getView().getFightClubView().setCommandFeildFocused();
		c.getView().getHubView().setPlayerInventoryTabPaneDisabled(false);
		updateAllStats();
	}

	public Enemy getEnemy() {
		return enemy;
	}
	public List<Enemy> getEnemyList() {
		return enemyList;
	}
}
