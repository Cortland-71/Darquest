package com.game.darquest.controller;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;

public class EnemyController extends TimerTask {
	

	private Controller c;
	public EnemyController(Controller c) {
		this.c = c;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			holdOn(500);
			c.getView().getFightClubView().setEnemyOutputTextArea("move " + i);
		}

		this.cancel();
		c.getView().getFightClubView().setDisableCommandField(false);
		c.getPlayer().setMoves(c.getPlayer().getMaxMoves());
	}
	
	private void holdOn(int time) {
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
