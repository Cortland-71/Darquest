package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class EchoRuleController implements Ruleable {

	private Controller c;
	public EchoRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		
		
		c.getFightClubController().runFire("ght 0", e);	
		System.out.println("LightRule: did light\n");
	}
}
