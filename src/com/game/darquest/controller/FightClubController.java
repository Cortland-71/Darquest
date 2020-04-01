package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.actions.Eat;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.actions.Sleep;
import com.game.darquest.data.actions.Work;

import javafx.concurrent.Task;
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
			for (int i = 0; i < fireList.size(); i++) {
				if(command.toLowerCase().equals(fireList.get(i).getCommandId())) {
					String output = fireList.get(i).fire();
					if(fireList.get(i).isValid()) {
						setPlayerTurnOutput();
						doEnemyTurnIfPlayerTurnHasEnded();
					}
					c.getView().getFightClubView().setPlayerOutputTextArea(output);
					
				}
			}
		}
	}
	
	private void doEnemyTurnIfPlayerTurnHasEnded() {
		if(c.getPlayer().getMoves()<1) {
			c.getView().getFightClubView().setDisableCommandField(true);
			EnemyController ec = new EnemyController(this.c);
			Timer t = new Timer(true);
			t.scheduleAtFixedRate(ec, 1, 3);
			
		}
	}
	
	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
	private void setPlayerTurnOutput() {
		c.getView().getFightClubView().clearCommandField();
		c.getPlayer().setMoves(c.getPlayer().getMoves()-1);
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
		c.getPlayerInventoryAndStatsController().updateAllPlayerStats();
	}

}


