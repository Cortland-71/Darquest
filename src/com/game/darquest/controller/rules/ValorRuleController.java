package com.game.darquest.controller.rules;

import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class ValorRuleController implements Ruleable {

	private Controller c;
	public ValorRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		double eatRequired = .1;
		double sleepRequired = .1;
		double workRequired = .1;
		double cashRequired = 0;
		if (Rules.failedBasicCheck(c, e, eatRequired, sleepRequired, workRequired, cashRequired)) return;
		
		int id = e.getId();
		
		for (int i = 0; i < enemyList.size(); i++) {
			if(enemyList.get(i).getDef() < enemyList.get(i).getMaxDef()) {
				id = enemyList.get(i).getId();
			}
		}
		c.getFightClubController().runFire("va " + id, e);
	}
}
