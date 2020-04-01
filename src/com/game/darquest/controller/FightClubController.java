package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Eat;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.actions.Sleep;
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
		fireList = Arrays.asList(new Eat(this.c), new Sleep(this.c), new Work(this.c));
	}
	
	@Override
	public void handle(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			String command = c.getView().getFightClubView().getCommand();
			runFireList(command, c.getPlayer());
		}
	}
	
	public void runFireList(String command, Person person) {
		for (int i = 0; i < fireList.size(); i++) {
			if(command.toLowerCase().equals(fireList.get(i).getCommandId())) {
				boolean valid = fireList.get(i).fire(person);
				String output = fireList.get(i).getOutput();
				if(valid) setPersonStats(person);
				setOutput(output, person);
			}
		}
	}
	
	private void setOutput(String output, Person person) {
		if(person instanceof Player) {
			c.getView().getFightClubView().setPlayerOutputTextArea(output);
			return;
		}
		c.getView().getFightClubView().setEnemyOutputTextArea(output+"\n");
	}
	
	private void setPersonStats(Person person) {
		if(person instanceof Player) {
			c.getView().getFightClubView().clearCommandField();
			c.getPlayer().setMoves(c.getPlayer().getMoves()-1);
			c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
			c.getPlayerInventoryAndStatsController().updateAllPlayerStats();
			doEnemyTurnIfPlayerTurnHasEnded();
			return;
		}
		c.getView().getFightClubView().setEnemyStats(enemyList.get(0));
	}
	
	private void doEnemyTurnIfPlayerTurnHasEnded() {
		if(c.getPlayer().getMoves()<1) {
			c.getView().getFightClubView().setDisableCommandField(true);
			c.getEnemyController().enemyTurn();
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


