package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;

public class StealRuleController implements Ruleable {

	private Controller c;
	private Random rand = new Random();
	public StealRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		double workRequired = .2;
		if(c.getEnemyController().getEnemy().getWork() < workRequired) {
			c.getFightClubController().runFire("work", c.getEnemyController().getEnemy());
			return;
		}
		c.getFightClubController().runFire("steal 0", c.getEnemyController().getEnemy());
		
	}
	
}
