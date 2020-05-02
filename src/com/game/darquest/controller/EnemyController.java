package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;

public class EnemyController {
	private Controller c;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private int points = 0;
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
		if (points < 5) {
			points++;
		}
			

		masterList = new ArrayList<>();
		List<Fireable> list = getAvailibleMoves(points);

		List<Fireable> allowedList = getAllowedMoves(list);

		createCombinations(allowedList, allowedList.size());
		Collections.reverse(allowedList);
		createCombinations(allowedList, allowedList.size());

		HashSet<List<Fireable>> set = new HashSet<List<Fireable>>();
		for (int i = 0; i < masterList.size(); i++) {
			set.add(masterList.get(i));
		}

		List<List<Fireable>> noDupList = new ArrayList<>(set);

		List<List<Fireable>> withPointsList = getPossibleMovesWithPoints(noDupList);

		withPointsList.sort((a,b)-> {
				if(a.size() == b.size()) return 0;
				return a.size() < b.size() ? -1 : 1;
			});
		
		getHighestScoringList(withPointsList);
		// return allowedList;
	}

	private List<List<Fireable>> getPossibleMovesWithPoints(List<List<Fireable>> noDupList) {
		List<List<Fireable>> listWithPoints = new ArrayList<>();
		int sum = 0;
		for (int i = 0; i < noDupList.size(); i++) {
			for (int j = 0; j < noDupList.get(i).size(); j++) {
				sum += noDupList.get(i).get(j).getPointCost();
			}
			if (sum == points) {
				listWithPoints.add(noDupList.get(i));
			}
			sum = 0;
		}
		return listWithPoints;
	}

	private void getHighestScoringList(List<List<Fireable>> withPointsList) {
		List<Integer> playerIntegerStats = c.getPlayer().getAllIntegerStatsForSimulation();
		double playerCash = c.getPlayer().getCash();
		double playerHp = c.getPlayer().getHp();

		List<Integer> enemyIntegerStats = enemy.getAllIntegerStatsForSimulation();
		double enemyCash = enemy.getCash();
		double enemyHp = enemy.getHp();
		int id = enemy.getId();

		Player simPlayer = new Player();
		simPlayer.setSimStats(playerIntegerStats, playerCash, playerHp);

		Enemy simEnemy = new Enemy();
		simEnemy.setSimStats(enemyIntegerStats, enemyCash, enemyHp);
		simEnemy.setId(id);
		
		//loop this for each withPointsList. Story the ids in a 3d list.
		List<List<String>> ids = getIDCombinations(simEnemy.getId(), withPointsList.get(1).size());
		
		getCommandsWithIds(ids, withPointsList);
		
		
	}

	private List<List<String>> getIDCombinations(Integer id, int size) {
		int n = size-1; // This would be the number of elements-1
		int N = (int)Math.pow(2, n); // this is the total number of loops
		List<List<String>> first = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String[] arr = new String[n];
			String a = Integer.toBinaryString(N|i);
			a = a.replace("1", id.toString());
			arr = a.split("");
			first.add(Arrays.asList(arr));
			a = "0" + a.substring(1);			
			arr = a.split("");
			first.add(Arrays.asList(arr));
		}
		return first;
	}
	
	private void getCommandsWithIds(List<List<String>> ids, 
			List<List<Fireable>> withPointsList) {
		
		List<List<List<String>>> allCommands = new ArrayList<List<List<String>>>();
		
		printLists(withPointsList);
		System.out.println();
		ids.forEach(System.out::println);
		

		//broken
//		for (int k = 0; k < withPointsList.size(); k++) {
//			List<List<String>> commandsWithIds = new ArrayList<>();
//			for (int i = 0; i < ids.size(); i++) {
//				List<String> miniList = new ArrayList<>();
//				
//				if(withPointsList.get(k).size() < ids.get(i).size()) {
//					for (int j = 0; j < withPointsList.get(k).size(); j++) {
//						String currentID = ids.get(i).get(j);
//						String currentCommand = withPointsList.get(k).get(j).getCommandId();
//						miniList.add(currentCommand + " " + currentID);
//					}
//					commandsWithIds.add(miniList);
//					continue;
//				}
//				
//				for (int j = 0; j < ids.get(i).size(); j++) {
//					String currentID = ids.get(i).get(j);
//					String currentCommand = withPointsList.get(k).get(j).getCommandId();
//					miniList.add(currentCommand + " " + currentID);
//				}
//				commandsWithIds.add(miniList);
//			}
//			allCommands.add(commandsWithIds);
//		}
//		allCommands.forEach(System.out::println);
	}
	
	private void createCombinations(List<Fireable> sequence, int N) {
		Fireable[] data = new Fireable[N];
		for (int r = 0; r < sequence.size(); r++)
			combinations(sequence, data, 0, N - 1, 0, r);
	}

	private void combinations(List<Fireable> sequence, Fireable[] data, int start, int end, int index, int r) {
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
		List<Fireable> finalList = list.stream().filter(e -> allowedList.contains(e.getCommandId()))
				.collect(Collectors.toList());
		return finalList;
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
	
	private void printLists(List<List<Fireable>> lists) {
		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < lists.get(i).size(); j++) {
				System.out.print(lists.get(i).get(j).getCommandId() + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
