package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;

public class HealRuleController implements Ruleable {

	private Controller c;
	public HealRuleController(Controller c) {
		this.c = c;
	}


	@Override
	public void getRule() {
		double sleepRequired = .2;
		if(c.getEnemyController().getEnemy().getSleep() < sleepRequired) {
			c.getEnemyController().rulesForSleep();
			return;
		}
		c.getFightClubController().runFire("heal", c.getEnemyController().getEnemy());
		
	}

}
