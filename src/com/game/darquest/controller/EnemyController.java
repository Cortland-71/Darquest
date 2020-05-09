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
import com.game.darquest.data.actions.mutationCommands.Acid;
import com.game.darquest.data.actions.mutationCommands.Deception;
import com.game.darquest.data.actions.mutationCommands.Echo;
import com.game.darquest.data.actions.mutationCommands.Fear;
import com.game.darquest.data.actions.mutationCommands.Shield;
import com.game.darquest.data.actions.mutationCommands.VitaminC;
import com.game.darquest.data.actions.primaryCommands.Attack;
import com.game.darquest.data.actions.primaryCommands.Steal;
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
	private int maxPoints = 8;
	private int currentPoints = 0;
	private int moveIndex = 0;
	private List<Fireable> fireList;

	public EnemyController(Controller c) {
		this.c = c;
	}

	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		fireList = c.getFightClubController().getFireList();
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
		List<Person> simPeople = getSimPeople();
		Player simP = (Player)simPeople.get(0);
		Enemy simE = (Enemy)simPeople.get(1);
		
		List<String> moveList = new ArrayList<>();

		String type = enemy.getType().getName();
		
		if(type.equals("Enforcer")) 
			return attackFocused(moveList, simP, simE);
		else if(type.equals("Observer")) {
			return attackFocused(moveList, simP, simE);
		} else {
			return stealFocused(moveList, simP, simE);
		}
	}
	
	private List<String> attackFocused(List<String> moveList, Player simP, Enemy simE) {
		attackLogic(moveList, simP, simE);
		for (int i = 0; i < moveList.size(); i++) runSimFire(moveList.get(i), simP, simE);
		if(currentPoints < maxPoints) attackFocused(moveList, simP, simE);
		currentPoints = 0;
		return moveList;
	}
	
	private List<String> stealFocused(List<String> moveList, Player simP, Enemy simE) {
		if(c.getPlayer().getCash() > 100) {
			if(simE.getAttack() >= simP.getDef()*2) {
				canAttack(moveList, simE);
			}
			else if(simE.getMutation() < simE.getDefaultMutation()) {
				moveList.add("vc " + simE.getId()); currentPoints += currentPoints += fireList.get(8).getPointCost();
			} else if(simE.getDef() < simE.getDefaultDef()) {
				moveList.add("shd " + simE.getId()); currentPoints += fireList.get(7).getPointCost();
			}
			else if(simE.getStealth() < simP.getAwareness()) {
				moveList.add("dec " + simE.getId()); currentPoints += fireList.get(4).getPointCost();
			} else {
				if(maxPoints - currentPoints >= fireList.get(3).getPointCost()) {
					moveList.add("st 0"); currentPoints += fireList.get(3).getPointCost();
				} else {
					moveList.add("ech " + simE.getId()); currentPoints += fireList.get(6).getPointCost();
				}
			}
		} else {
			attackLogic(moveList, simP, simE);
		}
	
		for (int i = 0; i < moveList.size(); i++) runSimFire(moveList.get(i), simP, simE);
		
		if(currentPoints < maxPoints) stealFocused(moveList, simP, simE);
		
		currentPoints = 0;
		return moveList;
	}
	
	private void attackLogic(List<String> moveList, Player simP, Enemy simE) {
		if(simE.getMutation() < simE.getDefaultMutation()) {
			moveList.add("vc " + simE.getId()); currentPoints += fireList.get(8).getPointCost();
		} else if(simE.getAttack() < simP.getDef()) {
			moveList.add("fear " + simE.getId()); currentPoints += fireList.get(5).getPointCost();
		} else {
			canAttack(moveList, simE);
		}
	}
	
	private void canAttack(List<String> moveList, Enemy simE) {
		if(maxPoints - currentPoints >= fireList.get(2).getPointCost()) {
			moveList.add("att 0"); currentPoints += fireList.get(2).getPointCost();
		} else {
			moveList.add("shd " + simE.getId()); currentPoints += fireList.get(7).getPointCost();
		}
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
	
	private int getMoveIndex() {
		return moveIndex;
	}

	private void setMoveIndex(int index) {
		this.moveIndex = index;
	}
}
