package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class LightRuleController implements Ruleable {

	private Controller c;
	public LightRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		double eatRequired = .1;
		double sleepRequired = .1;
		double workRequired = .1;
		double cashRequired = 0;
		Enemy e = c.getEnemyController().getEnemy();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired, cashRequired)) return;
		
		c.getFightClubController().runFire("tt 0", e);	
	}
}
