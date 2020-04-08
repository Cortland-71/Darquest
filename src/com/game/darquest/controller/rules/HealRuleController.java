package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class HealRuleController implements Ruleable {

	private Controller c;
	private Random rand = new Random();
	
	public HealRuleController(Controller c) {
		this.c = c;
	}


	@Override
	public void getRule() {
		double cashRequired = 50;
		double sleepRequired = .2;
		double eatRequired = 0;
		double workRequired = 0;
		Enemy e = c.getEnemyController().getEnemy();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired)) return;
		
		if (e.getCash() < cashRequired) {
			if(rand.nextBoolean()) {
				c.getEnemyController().rulesForWork();
				return;
			}
			c.getEnemyController().getStealRuleController().getRule();
			return;
		}
		
		c.getFightClubController().runFire("ea", c.getEnemyController().getEnemy());
		
	}

}
