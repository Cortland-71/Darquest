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
		
		Enemy e = c.getEnemyController().getEnemy();
		
		c.getFightClubController().runFire("dec 0", e);
	}

}
