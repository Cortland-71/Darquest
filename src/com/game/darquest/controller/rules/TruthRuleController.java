package com.game.darquest.controller.rules;

import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class TruthRuleController implements Ruleable {

	private Controller c;
	public TruthRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		
		int id = e.getId();
		
		c.getFightClubController().runFire("tr " + id, e);
		System.out.println("TruthRule: Did truth");
	}
}
