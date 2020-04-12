package com.game.darquest.controller.rules;

import java.util.List;
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
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired)) return;
		
		if (e.getCash() < cashRequired) {
			c.getEnemyController().rulesForWork();
			return;
		}
		
		int id = e.getId();
		for (int i = 0; i < enemyList.size(); i++) {
			if(enemyList.get(i).getHp() < 1) {
				id = enemyList.get(i).getId();
			}
		}
		
		c.getFightClubController().runFire("he " + id, c.getEnemyController().getEnemy());
		
	}

}
