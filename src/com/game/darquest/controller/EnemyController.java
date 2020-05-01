package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Collections;
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
	private List<List<Fireable>> masterList;

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
	
		masterList = new ArrayList<>();
		List<Fireable> list = getAvailibleMoves(points);
		
		List<Fireable> allowedList = getAllowedMoves(list);

		printCombinations(allowedList, allowedList.size());
		Collections.reverse(allowedList);
		printCombinations(allowedList, allowedList.size());

		HashSet<List<Fireable>> set = new HashSet<List<Fireable>>();
		for (int i = 0; i < masterList.size(); i++) {
			set.add(masterList.get(i));
		}

		List<List<Fireable>> noDupList = new ArrayList<>(set); 
		
		//printList(noDupList);

		List<List<Fireable>> withPointsList = getPossibleMovesWithPoints(noDupList);
		printList(withPointsList);
		//return allowedList;
	}
	
	private void printList(List<List<Fireable>> lists) {
		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < lists.get(i).size(); j++) {
				System.out.print(lists.get(i).get(j).getCommandId() + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private List<List<Fireable>> getPossibleMovesWithPoints(List<List<Fireable>> noDupList) {
		List<List<Fireable>> listWithPoints = new ArrayList<>();
		int sum = 0;
		for (int i = 0; i < noDupList.size(); i++) {
			for (int j = 0; j < noDupList.get(i).size(); j++) {
				sum += noDupList.get(i).get(j).getPointCost();
			}
			if(sum <= points) {
				listWithPoints.add(noDupList.get(i));
			}
			sum = 0;
		}
		return listWithPoints;
	}
 	
	private void getSimulation(List<List<Fireable>> noDupList) {
		
	}
	
	private void printCombinations(List<Fireable> sequence, int N) {
        Fireable[] data = new Fireable[N];
        for (int r = 0; r < sequence.size(); r++)
            combinations(sequence, data, 0, N - 1, 0, r);
    }
 
    private void combinations(List<Fireable> sequence, Fireable[] data, int start, int end,
            int index, int r) {
 
    	
        if (index == r) {
        	List<Fireable> miniList = new ArrayList<>();
            for (int j = 0; j < r; j++) {
            	miniList.add(data[j]);
            	masterList.add(miniList);
            }
        }
        for (int i = start; i <= end && ((end - i + 1) >= (r - index)); i++) {
            data[index] = sequence.get(i);
            combinations(sequence, data, i + 1, end, index + 1, r);
        }
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
