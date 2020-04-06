package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;

public class StealRuleController extends RuleController implements Ruleable {

	public StealRuleController(Controller c) {
		super(c);
	}

	@Override
	public void getRule() {
		double workRequired = .2;
		if(currentEnemy.getWork() < workRequired) {
			c.getFightClubController().runFire("work", currentEnemy);
			return;
		}
		c.getFightClubController().runFire("steal 0", currentEnemy);
		
	}

	
}
