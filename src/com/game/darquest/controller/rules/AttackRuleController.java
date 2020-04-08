package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class AttackRuleController implements Ruleable {

	private Controller c;
	private Random rand = new Random();
	public AttackRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		double eatRequired = .1;
		double sleepRequired = .1;
		double workRequired = 0;
		Enemy e = c.getEnemyController().getEnemy();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired)) return;
		
		if(e.getEng() >= .5 && e.getEng() < 1) {
			boolean coinFlip = rand.nextBoolean();
			if(coinFlip) {
				c.getEnemyController().rulesForSleep();
				return;
			} 
			c.getFightClubController().runFire("att 0", e);
			return;
		}
		int coinFlip = rand.nextInt(10);
		if(coinFlip < 7) {
			c.getEnemyController().rulesForSleep();
			return;
		} 
		c.getFightClubController().runFire("att 0", e);
		
	}
	
	
}
