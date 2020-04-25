package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class AttackRuleController implements Ruleable {

	private Controller c;
	public AttackRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		c.getFightClubController().runFire("att 0", e);
	}
}
