package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;

public class AttackRuleController extends RuleController implements Ruleable {

	public AttackRuleController(Controller c) {
		super(c);
	}

	@Override
	public void getRule() {
		double eatRequired = .1;
		double sleepRequired = .1;
		
		if(currentEnemy.getEat() < eatRequired) {
			rulesForEat();
			return;
		} else if(currentEnemy.getSleep() < sleepRequired) {
			rulesForSleep();
			return;
		}
		if(currentEnemy.getEng() >= .5 && currentEnemy.getEng() < 1) {
			boolean coinFlip = rand.nextBoolean();
			if(coinFlip) {
				rulesForSleep();
				return;
			} 
			c.getFightClubController().runFire("attack 0", currentEnemy);
			return;
		}
		int coinFlip = rand.nextInt(10);
		if(coinFlip < 7) {
			rulesForSleep();
			return;
		} 
		c.getFightClubController().runFire("attack 0", currentEnemy);
		
	}
}
