package com.game.darquest.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.enemyType.Classable;
import com.game.darquest.data.items.Weapon;

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
	private List<List<Fireable>> masterList;
	private int moveIndex = 0;

	public EnemyController(Controller c) {
		this.c = c;
	}

	private int getMoveIndex() {
		return moveIndex;
	}

	private void setMoveIndex(int index) {
		this.moveIndex = index;
	}

	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;

		List<String> finalList = getFinalListOfMoves();

		Timeline timeline = new Timeline();
		timeline.setCycleCount(finalList.size());
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), e -> {
			move(finalList.get(getMoveIndex()));
			setMoveIndex(getMoveIndex() + 1);
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

	private void move(String command) {
		c.getFightClubController().runFire(command, enemy);
		updateAllStats();
		if (c.getPlayer().getHp() <= 0) {
			c.getView().getHubView().showHub();
		}
	}

	private List<String> getFinalListOfMoves() {
		if (points < c.getFightClubController().getTotalMovePoints()) {
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

		withPointsList.sort((a, b) -> {
			if (a.size() == b.size())
				return 0;
			return a.size() < b.size() ? -1 : 1;
		});

		return getHighestScoringCommands(withPointsList);
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

	private List<String> getHighestScoringCommands(List<List<Fireable>> withPointsList) {

		List<Person> people = new ArrayList<>(getSimPeople());

		List<List<List<String>>> allIds = new ArrayList<>();

		int withPointsListSize = 0;
		for (int i = 0; i < withPointsList.size(); i++) {
			if (withPointsList.get(i).size() > withPointsListSize) {
				List<List<String>> ids = getIDCombinations(((Enemy) people.get(1)).getId(),
						withPointsList.get(i).size());
				allIds.add(ids);
				withPointsListSize = withPointsList.get(i).size();
			}
		}
		List<List<List<String>>> allCommands = getCommandsWithIds(allIds, withPointsList);
		List<List<String>> masterCommands = getMasterListOfCommands(allCommands, (Player) people.get(0),
				(Enemy) people.get(1));
		return getChoosenCommands(masterCommands);
	}

	private List<String> getChoosenCommands(List<List<String>> masterCommands) {
		Random rand = new Random();
		return masterCommands.get(rand.nextInt(masterCommands.size()));
	}

	private List<List<String>> getMasterListOfCommands(List<List<List<String>>> allCommands, Player simPlayer,
			Enemy simEnemy) {
		List<List<String>> masterCommands = new ArrayList<>();
		List<Double> miniScores = new ArrayList<>();
		List<List<String>> miniCommands = new ArrayList<>();
		for (int i = 0; i < allCommands.size(); i++) {
			for (int j = 0; j < allCommands.get(i).size(); j++) {
				for (int j2 = 0; j2 < allCommands.get(i).get(j).size(); j2++) {
					String command = allCommands.get(i).get(j).get(j2);
					runSimFire(command, simPlayer, simEnemy);
				}
				miniScores.add(getScore(simPlayer, simEnemy));
				miniCommands.add(allCommands.get(i).get(j));
				List<Person> people = getSimPeople();
				simPlayer = (Player) people.get(0);
				simEnemy = (Enemy) people.get(1);
			}
		}

		System.out.println(miniScores);
		System.out.println(miniCommands);

		double maxScore = Collections.max(miniScores);
		System.out.println("max score: " + maxScore);
		for (int i = 0; i < miniScores.size(); i++) {
			if (miniScores.get(i) == maxScore) {
				masterCommands.add(miniCommands.get(i));
			}
		}

		masterCommands.forEach(System.out::println);
		return masterCommands;
	}

	private double getScore(Player simPlayer, Enemy simEnemy) {
		Player p = (Player) c.getPlayer();
		Enemy e = enemy;

		List<Double> playerBeforeListDouble = p.getListOfMainStatsDouble();
		List<Integer> playerBeforeListInt = p.getListOfMainStatsInts();
		List<Double> playerAfterListDouble = simPlayer.getListOfMainStatsDouble();
		List<Integer> playerAfterListInt = simPlayer.getListOfMainStatsInts();
		
		List<Integer> playerAfterListIntOrg = Arrays.asList(playerAfterListInt.get(1),
				playerAfterListInt.get(0), playerAfterListInt.get(3), playerAfterListInt.get(2),
				playerAfterListInt.get(4));

		List<Double> enemyBeforeListDouble = e.getListOfMainStatsDouble();
		List<Integer> enemyBeforeListInt = e.getListOfMainStatsInts();
		List<Double> enemyAfterListDouble = simEnemy.getListOfMainStatsDouble();
		List<Integer> enemyAfterListInt = simEnemy.getListOfMainStatsInts();

		double playerSum = 0.0;
		double enemySum = 0;

		// Get the sums
		for (int i = 0; i < enemyAfterListInt.size(); i++) {
			double playerDif = playerBeforeListInt.get(i) - playerAfterListInt.get(i);
			playerSum += playerDif;
			
			double enemyDif = enemyBeforeListInt.get(i) - enemyAfterListInt.get(i);
			enemySum += enemyDif;
			if(simPlayer.getAttack() < p.getAttack()) enemySum += p.getAttack() - simPlayer.getAttack();
		}
		for (int i = 0; i < playerBeforeListDouble.size(); i++) {
			double playerDif = playerBeforeListDouble.get(i) - playerAfterListDouble.get(i);
			playerSum += playerDif;
			double enemyDif = enemyBeforeListDouble.get(i) - enemyAfterListDouble.get(i);
			enemySum += enemyDif;
			
		}
		return getPlayerEnemyCombinationScore(enemySum, playerSum);
	}

	private static double getPlayerEnemyCombinationScore(double enemySum, double playerSum) {
		double finalScore = 0.0;
		finalScore -= enemySum / 100d;
		finalScore += playerSum / 100d;
		DecimalFormat df = new DecimalFormat(".##");
		df.setRoundingMode(RoundingMode.UP);
		finalScore = Double.parseDouble(df.format(finalScore));
		return finalScore;
	}

	private void runSimFire(String command, Player simPlayer, Enemy simEnemy) {
		List<Fireable> fireList = c.getFightClubController().getFireList();
		Person choosen = assignReceivingSimPerson(command, simPlayer, simEnemy);
		String finalCommand = c.getFightClubController().getCommandHandler().getCommandWithoutModifiers(command);

		for (int i = 0; i < fireList.size(); i++) {
			if (finalCommand.equals(fireList.get(i).getCommandId())) {
				fireList.get(i).fire(simEnemy, choosen);
				return;
			}
		}
	}

	private Person assignReceivingSimPerson(String command, Player simPlayer, Enemy simEnemy) {
		if (command.contains("0"))
			return simPlayer;
		return simEnemy;
	}

	private List<Person> getSimPeople() {
		List<Integer> playerIntegerStats = c.getPlayer().getAllIntegerStatsForSimulation();
		double playerCash = c.getPlayer().getCash();
		double playerHp = c.getPlayer().getHp();
		String playerName = c.getPlayer().getName();
		Weapon playerWeapon = c.getPlayer().getEquippedWeapon();

		List<Integer> enemyIntegerStats = enemy.getAllIntegerStatsForSimulation();
		double enemyCash = enemy.getCash();
		double enemyHp = enemy.getHp();
		int id = enemy.getId();
		String enemyName = enemy.getName();
		Weapon enemyWeapon = enemy.getEquippedWeapon();
		Classable enemyType = enemy.getType();

		Player simPlayer = new Player();
		simPlayer.setSimStats(playerIntegerStats, playerCash, playerHp, playerName, playerWeapon);

		Enemy simEnemy = new Enemy();
		simEnemy.setSimStats(enemyIntegerStats, enemyCash, enemyHp, enemyName, enemyWeapon);
		simEnemy.setId(id);
		simEnemy.setType(enemyType);
		return Arrays.asList(simPlayer, simEnemy);
	}

	private List<List<String>> getIDCombinations(Integer id, int size) {
		int n = size - 1; // This would be the number of elements-1
		int N = (int) Math.pow(2, n); // this is the total number of loops
		List<List<String>> first = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] arr = new String[n];
			String a = Integer.toBinaryString(N | i);
			a = a.replace("1", id.toString());
			arr = a.split("");
			first.add(Arrays.asList(arr));
			a = "0" + a.substring(1);
			arr = a.split("");
			first.add(Arrays.asList(arr));
		}
		return first;
	}

	private List<List<List<String>>> getCommandsWithIds(List<List<List<String>>> allIds,
			List<List<Fireable>> withPointsList) {

		List<List<List<String>>> allCommands = new ArrayList<List<List<String>>>();

		int withPointsListSize = 0;
		int idListIndex = -1;

		// broken
		for (int k = 0; k < withPointsList.size(); k++) {
			List<List<String>> commandsWithIds = new ArrayList<>();
			if (withPointsList.get(k).size() > withPointsListSize) {
				idListIndex++;
				withPointsListSize = withPointsList.get(k).size();
			}
			for (int i = 0; i < allIds.get(idListIndex).size(); i++) {
				List<String> miniList = new ArrayList<>();
				for (int j = 0; j < allIds.get(idListIndex).get(i).size(); j++) {
					String currentID = allIds.get(idListIndex).get(i).get(j);
					String currentCommand = withPointsList.get(k).get(j).getCommandId();
					miniList.add(currentCommand + " " + currentID);
				}
				commandsWithIds.add(miniList);
			}
			allCommands.add(commandsWithIds);
		}
		return allCommands;
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
}
