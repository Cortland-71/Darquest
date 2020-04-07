package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class StealRuleController implements Ruleable {

	private Controller c;
	private Random rand = new Random();
	public StealRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		double workRequired = .2;
		double sleepRequired = 0;
		double eatRequired = 0;
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired)) return;
		c.getFightClubController().runFire("steal 0", c.getEnemyController().getEnemy());
		
	}
	
}
