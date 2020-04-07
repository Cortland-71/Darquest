package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class DeceptionRuleController implements Ruleable {

	private Controller c;
	public DeceptionRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		double eatRequired = .1;
		double sleepRequired = .1;
		double workRequired = .1;
		Enemy e = c.getEnemyController().getEnemy();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired)) return;
		
		c.getFightClubController().runFire("dec 0", e);
	}

}
