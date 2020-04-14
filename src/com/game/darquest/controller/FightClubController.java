package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.actions.Help;
import com.game.darquest.data.actions.Use;
import com.game.darquest.data.actions.defensive.Heal;
import com.game.darquest.data.actions.defensive.Shadow;
import com.game.darquest.data.actions.defensive.Truth;
import com.game.darquest.data.actions.defensive.Valor;
import com.game.darquest.data.actions.essential.Eat;
import com.game.darquest.data.actions.essential.Sleep;
import com.game.darquest.data.actions.essential.Work;
import com.game.darquest.data.actions.hostile.Attack;
import com.game.darquest.data.actions.hostile.Deception;
import com.game.darquest.data.actions.hostile.Fear;
import com.game.darquest.data.actions.hostile.Light;
import com.game.darquest.data.actions.hostile.Steal;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class FightClubController implements EventHandler<KeyEvent> {

	private List<Fireable> fireList;
	private List<Enemy> enemyList = new ArrayList<>();
	private Integer numPlayerMoves = 0;

	private Controller c;

	public FightClubController(Controller c) {
		c.getView().getFightClubView().addCommandFieldListener(this);
		this.c = c;
		fireList = Arrays.asList(new Help(), new Eat(), new Sleep(), new Work(), 
				new Attack(this.c), new Use(this.c), new Steal(),
				new Heal(), new Deception(), new Truth(), new Fear(), 
				new Valor(), new Light(), new Shadow());
	}

	@Override
	public void handle(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			String command = c.getView().getFightClubView().getCommand();
			runFire(command.trim(), c.getPlayer());

		}
	}

	public void runFire(String command, Person person) {
		Person choosen = person;
		String finalCommand = command;
		if (hasModifier(command)) {
			choosen = assignReceivingPerson(command);
			if(choosen == null) return;
			finalCommand = getCommandWithoutModifiers(command);
		}

		for (int i = 0; i < fireList.size(); i++) {
			if (finalCommand.toLowerCase().equals(fireList.get(i).getCommandId())) {
				boolean valid = fireList.get(i).fire(person, choosen);
				String output = fireList.get(i).getOutput();
				if (valid) {
					if (person instanceof Player) {
						afterPlayerMove();
					}
				}
				setOutput(output, person);
			}
		}

		if (c.getPlayer().getHp() <= 0) {
			c.getView().getWindow().setScene(c.getView().getDownTownView().getDownTownScene());
			return;
		}
	}

	private String[] modifiers = { "0", "1", "2", "3" };

	private String getCommandWithoutModifiers(String command) {
		String mod = command.substring(command.length() - 2, command.length());
		int indexAtMod = command.indexOf(mod);
		return command.substring(0, indexAtMod);
	}

	private boolean hasModifier(String command) {
		for (String e : modifiers)
			if (command.contains(e))
				return true;
		return false;
	}

	private Person assignReceivingPerson(String command) {

		String mod = "";
		if (command.contains("0"))
			return c.getPlayer();
		for (String m : modifiers) {
			if (command.contains(m))
				mod = m;
		}

		for (Enemy e : enemyList) {
			if (e.getId() == Integer.parseInt(mod))
				return e;
		}
		return null;
	}

	private void setOutput(String output, Person person) {
		if (person instanceof Player) {
			c.getView().getFightClubView().setPlayerOutputTextArea(output);
			return;
		}
		c.getView().getFightClubView().setEnemyOutputTextArea(output + "\n");
	}

	private void afterPlayerMove() {

		numPlayerMoves++;
		removeDeadEnemyFromList();
		if (playerWins())
			return;
		c.getView().getFightClubView().clearCommandField();
		c.getPlayer().setMoves(c.getPlayer().getMoves() - 1);
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
		// c.getView().getFightClubView().animateWorkBar((Player)c.getPlayer());
		c.getPlayerInvStatsController().updateAllPlayerStats();
		c.getDownTownController().drawAllEnemyBoxes(enemyList);
		doEnemyTurnIfPlayerTurnHasEnded();
	}

	private boolean playerWins() {
		if (enemyList.size() <= 0) {
			c.getView().getFightClubView().clearEnemyOutputTextArea();
			c.getView().getFightClubView().setPlayerOutputTextArea("");
			c.getView().getFightClubView().clearCommandField();
			c.getPlayerInvStatsController().updateAllPlayerStats();
			
			FadeTransition ft = new FadeTransition(Duration.millis(500), 
					c.getView().getFightWinView().getFightWinPane());
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();
			
			List<String> listOfWinStats = Arrays.asList(numPlayerMoves.toString(), "Test xp", "test eff", 
					"loot", "cash", "rating");
			c.getView().getFightWinView().setWinStats(listOfWinStats);
			c.getView().getWindow().setScene(c.getView().getFightWinView().getFightWinScene());
			return true;
		}
		return false;
	}

	private void removeDeadEnemyFromList() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getHp() <= 0) {
				enemyList.remove(i);
				currentEnemyIndex = 0;
				Player p = ((Player)c.getPlayer());
				p.setXp(p.getXp() + p.getXpAdded());
			}
		}
	}

	private int currentEnemyIndex = 0;
	int deadEnemyIndex = -1;

	private void doEnemyTurnIfPlayerTurnHasEnded() {

		if (c.getPlayer().getMoves() < 1) {
			c.getView().getFightClubView().clearEnemyOutputTextArea();
			c.getView().getFightClubView().setDisableCommandField(true);

			c.getEnemyController().enemyTurn(enemyList.get(currentEnemyIndex), enemyList);

			currentEnemyIndex++;

			if (currentEnemyIndex >= enemyList.size())
				currentEnemyIndex = 0;
		}
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public List<Fireable> getFireList() {
		return fireList;
	}

}
