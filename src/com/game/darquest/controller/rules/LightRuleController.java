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
		Enemy e = c.getEnemyController().getEnemy();
		
		
		c.getFightClubController().runFire("ght 0", e);	
	}
}
