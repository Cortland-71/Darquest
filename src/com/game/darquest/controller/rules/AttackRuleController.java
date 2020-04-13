package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class AttackRuleController implements Ruleable {

	private Controller c;
	public AttackRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		double eatRequired = .1;
		double sleepRequired = .1;
		double workRequired = 0;
		double cashRequired = 0;
		Enemy e = c.getEnemyController().getEnemy();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired, cashRequired)) return;
		c.getFightClubController().runFire("att 0", e);
		
	}
	
	
}
