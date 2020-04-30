package com.game.darquest.controller;

import java.util.ArrayList;
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
	int totalCostPoints = 0;
	private int moveIndex = 0;
	
	public EnemyController(Controller c) {
		this.c = c;
	}
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		
		List<Fireable> finalList = getFinalListOfMoves();
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(finalList.size());
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), 
				e-> {
					move(finalList.get(getMoveIndex()));
					setMoveIndex(getMoveIndex()+1);
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
	
	private List<Fireable> getFinalListOfMoves() {
		if(points < 5) points++;
		List<Fireable> finalList = new ArrayList<>();
		List<Fireable> list = getAvailibleMoves(points);
		for (int i = 0; i < points; i++) {
			Fireable choosenMove = getBestMove(list);
			totalCostPoints += choosenMove.getPointCost();
			if(totalCostPoints <= points)
				finalList.add(choosenMove);
		}
		return finalList;
	}
	
	private void move(Fireable choosenMove) {
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



	private List<Fireable> getAvailibleMoves(int numberOfPoints) {
		List<Fireable> fireList = new ArrayList<>(
				c.getFightClubController().getFireList());
		fireList.remove(0);
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
		totalCostPoints = 0;
		setMoveIndex(0);
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
	
	private void setMoveIndex(int moveIndex) {
		this.moveIndex = moveIndex;
	}
	private int getMoveIndex() {
		return moveIndex;
	}
}
