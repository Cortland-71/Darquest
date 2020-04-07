package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class HealRuleController implements Ruleable {

	private Controller c;
	public HealRuleController(Controller c) {
		this.c = c;
	}


	@Override
	public void getRule() {
		double sleepRequired = .2;
		double eatRequired = 0;
		double workRequired = 0;
		Enemy e = c.getEnemyController().getEnemy();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired)) return;
		c.getFightClubController().runFire("heal", c.getEnemyController().getEnemy());
		
	}

}
