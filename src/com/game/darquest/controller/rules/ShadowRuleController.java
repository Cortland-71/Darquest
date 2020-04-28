package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class ShadowRuleController implements Ruleable {

	private Controller c;
	public ShadowRuleController(Controller c) {
		this.c = c;
	}
	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		
		
		c.getFightClubController().runFire("sh 0", e);	
		System.out.println("ShadowRule: did shadow\n");
	}

}
