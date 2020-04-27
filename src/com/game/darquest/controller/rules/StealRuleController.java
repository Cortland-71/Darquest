package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class StealRuleController implements Ruleable {

	private Controller c;
	public StealRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		
		if (e.getStealth() < c.getPlayer().getAwareness()) {
			c.getEnemyController().getDeceptionRuleController().getRule();
			return;
		}
		c.getFightClubController().runFire("st 0", e);
		
	}
	
}
