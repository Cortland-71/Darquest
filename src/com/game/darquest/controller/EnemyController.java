package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.fightClubControllers.FightController;
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
	private int maxPoints = 8;
	private int currentPoints = 0;
	private int moveIndex = 0;
	private FightController fc;
	private Timeline timeline;

	public EnemyController(Controller c) {
		this.c = c;
		this.fc = this.c.getFightClubController();
	}

	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		List<String> finalList = getFinalListOfMoves();

		timeline = new Timeline();
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
			timeline.stop();
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
		if(simE.getAttack() >= simP.getDef() || simP.getCash() <= 200) {
			attackLogic(moveList, simP, simE);
		} else if(currentPoints < 3 && simE.getStealth() < simP.getAwareness()) {
			if(simE.getAwareness() > 0) {
				moveList.add(fc.getDeception().getCommandId() + " " + simE.getId());
				currentPoints += fc.getDeception().getPointCost();
			} else {
				moveList.add(fc.getDeception().getCommandId() + " 0");
				currentPoints += fc.getDeception().getPointCost();
			}
		} else if(currentPoints > 6 && simE.getDef() < simP.getAttack()) {
			if(simE.getAwareness() < simP.getStealth()) {
				moveList.add(fc.getEcho().getCommandId() + " " + simE.getId()); 
				currentPoints += fc.getEcho().getPointCost();
			} else {
				moveList.add(fc.getDisarm().getCommandId() + " " + simE.getId()); 
				currentPoints += fc.getDisarm().getPointCost();
			}
		} else {
			moveList.add(fc.getSteal().getCommandId() + " 0"); 
			currentPoints += fc.getSteal().getPointCost();
		}
		
		for (int i = 0; i < moveList.size(); i++) runSimFire(moveList.get(i), simP, simE);
		
		if(currentPoints < maxPoints) stealFocused(moveList, simP, simE);
		
		currentPoints = 0;
		return moveList;
	}
	
	private void attackLogic(List<String> moveList, Player simP, Enemy simE) {
		if(simE.getMutation() < simE.getDefaultMutation()) {
			moveList.add(fc.getVitaminc().getCommandId() + " " + simE.getId()); 
			currentPoints += fc.getVitaminc().getPointCost();
		} else if(currentPoints < 3 && simE.getAttack() < simP.getDef()) {
			moveList.add(fc.getFear().getCommandId() + " " + simE.getId()); 
			currentPoints += fc.getFear().getPointCost();
		} else if(currentPoints > 6 && simE.getDef() < simP.getAttack()) {
			moveList.add(fc.getDisarm().getCommandId() + " " + simE.getId()); 
			currentPoints += fc.getDisarm().getPointCost();
		} else {
			canAttack(moveList, simE);
		}
	}
	
	private void canAttack(List<String> moveList, Enemy simE) {
		if(maxPoints - currentPoints >= fc.getAttack().getPointCost()) {
			moveList.add(fc.getAttack().getCommandId() + " 0"); 
			currentPoints += fc.getAttack().getPointCost();
		} else {
			moveList.add(fc.getDisarm().getCommandId() + " " + simE.getId()); 
			currentPoints += fc.getDisarm().getPointCost();
		}
	}

	private void runSimFire(String command, Player simPlayer, Enemy simEnemy) {
		List<Fireable> fireList = c.getFightClubController().getFireList();
		Person choosen = assignReceivingSimPerson(command, simPlayer, simEnemy);
		String finalCommand = c.getFightClubController().getCommandHandler().getCommandWithoutModifiers(command);

		for (int i = 0; i < fireList.size(); i++) {
			if (finalCommand.equals(fireList.get(i).getCommandId())) {
				fireList.get(i).fire(simEnemy, choosen);
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
