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

		
		int id = e.getId();

		c.getFightClubController().runFire("va " + id, e);
		System.out.println("ValorRule: Did valor\n");
	}
}
