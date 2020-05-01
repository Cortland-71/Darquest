package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.actions.Fireable;

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

		getFinalListOfMoves();
		enemyTurnEnd();
//		Timeline timeline = new Timeline();
//		timeline.setCycleCount(finalList.size());
//		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), e -> {
//			move(finalList.get(getMoveIndex()));
//			setMoveIndex(getMoveIndex() + 1);
//		}));
//		timeline.play();
//
//		timeline.setOnFinished(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				enemyTurnEnd();
//				System.out.println();
//			}
//		});
	}

	private void move(Fireable choosenMove) {
		choosenMove.fire(enemy, c.getPlayer());
		c.getView().getFightClubView().setEnemyOutputTextArea(choosenMove.getOutput());
		updateAllStats();
		if (c.getPlayer().getHp() <= 0) {
			c.getView().getHubView().showHub();
		}
	}

	private void getFinalListOfMoves() {
		if (points < 5)
			points++;
		List<List<Fireable>> masterList = new ArrayList<>();
		
		int sum = 0;
		List<Fireable> list = getAvailibleMoves(points);
		List<Fireable> allowedList = getAllowedMoves(list);
		
		for (int i = 0; i < allowedList.size(); i++) {
			Fireable seed = allowedList.get(i);
			
			for (int j = 0; j < allowedList.size(); j++) {
				List<Fireable> miniList = new ArrayList<>();
				miniList.add(seed);
				sum = seed.getPointCost();
				
				for (int k = j; k < allowedList.size(); k++) {
					sum += allowedList.get(k).getPointCost();
					if(sum <= points) {
						miniList.add(allowedList.get(k));
						continue;
					}

					masterList.add(miniList);
					break;
				}
				
			}
		}
		HashSet<List<Fireable>> set = new HashSet<List<Fireable>>();
		for (int i = 0; i < masterList.size(); i++) {
			set.add(masterList.get(i));
			System.out.println(masterList.get(i));
		}

		System.out.println();
		List<List<Fireable>> noDupList = new ArrayList<>(set); 
		
		for (int i = 0; i < noDupList.size(); i++) {
			for (int j = 0; j < noDupList.get(i).size(); j++) {
				System.out.print(noDupList.get(i).get(j).getCommandId() + ", ");
			}
			System.out.println();
		}
		
		System.out.println();
//		for (int i = 0; i < points; i++) {
//			Fireable choosenMove = getBestMove(list);
//			totalCostPoints += choosenMove.getPointCost();
//			if (totalCostPoints <= points)
//				finalList.add(choosenMove);
//		}
		//return allowedList;
	}

	private List<Fireable> getAllowedMoves(List<Fireable> list) {
		List<String> allowedList = enemy.getType().getAllowedMoves();
		List<Fireable> finalList = list.stream()
				.filter(e->allowedList.contains(e.getCommandId()))
				.collect(Collectors.toList());
		return finalList;
	}

	private Fireable getBestMove(List<Fireable> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	private List<Fireable> getAvailibleMoves(int numberOfPoints) {
		List<Fireable> fireList = new ArrayList<>(c.getFightClubController().getFireList());
		fireList.remove(0);
		List<Fireable> availibleFireList = fireList.stream().filter(e -> e.getPointCost() <= numberOfPoints)
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
