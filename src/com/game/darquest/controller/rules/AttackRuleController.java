package com.game.darquest.controller.rules;

import java.util.Random;

import com.game.darquest.controller.Controller;

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
		
		if(c.getEnemyController().getEnemy().getEat() < eatRequired) {
			c.getEnemyController().rulesForEat();
			return;
		} else if(c.getEnemyController().getEnemy().getSleep() < sleepRequired) {
			c.getEnemyController().rulesForSleep();
			return;
		}
		if(c.getEnemyController().getEnemy().getEng() >= .5 && c.getEnemyController().getEnemy().getEng() < 1) {
			boolean coinFlip = rand.nextBoolean();
			if(coinFlip) {
				c.getEnemyController().rulesForSleep();
				return;
			} 
			c.getFightClubController().runFire("attack 0", c.getEnemyController().getEnemy());
			return;
		}
		int coinFlip = rand.nextInt(10);
		if(coinFlip < 7) {
			c.getEnemyController().rulesForSleep();
			return;
		} 
		c.getFightClubController().runFire("attack 0", c.getEnemyController().getEnemy());
		
	}
	
	
}
