package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Attack;
import com.game.darquest.data.actions.Deception;
import com.game.darquest.data.actions.Eat;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.actions.Heal;
import com.game.darquest.data.actions.Sleep;
import com.game.darquest.data.actions.Steal;
import com.game.darquest.data.actions.Use;
import com.game.darquest.data.actions.Work;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FightClubController implements EventHandler<KeyEvent> {

	private List<Fireable> fireList;
	private List<Enemy> enemyList = new ArrayList<>();

	private Controller c;

	public FightClubController(Controller c) {
		c.getView().getFightClubView().addCommandFieldListener(this);
		this.c = c;
		fireList = Arrays.asList(new Eat(), new Sleep(), new Work(), new Attack(this.c), new Use(this.c), new Steal(),
				new Heal(), new Deception());
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
		Person person = null;
		if (command.contains("0"))
			return c.getPlayer();
		for (String m : modifiers) {
			if (command.contains(m))
				mod = m;
		}

		for (Enemy e : enemyList) {
			if (e.getId() == Integer.parseInt(mod))
				person = e;
		}
		return person;
	}

	private void setOutput(String output, Person person) {
		if (person instanceof Player) {
			c.getView().getFightClubView().setPlayerOutputTextArea(output);
			return;
		}
		c.getView().getFightClubView().setEnemyOutputTextArea(output + "\n");
	}

	private void afterPlayerMove() {

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
			c.getView().getWindow().setScene(c.getView().getDownTownView().getDownTownScene());
			return true;
		}
		return false;
	}

	private void removeDeadEnemyFromList() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getHp() <= 0) {
				enemyList.remove(i);
				currentEnemyIndex = 0;
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
