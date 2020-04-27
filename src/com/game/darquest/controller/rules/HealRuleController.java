package com.game.darquest.controller.rules;

import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class HealRuleController implements Ruleable {

	private Controller c;
	
	public HealRuleController(Controller c) {
		this.c = c;
	}


	@Override
	public void getRule() {
		double cashRequired = 50;
		Enemy e = c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		
		int id = e.getId();
//		if(e.getType().getName().equals("Observer")) {
//			for (int i = 0; i < enemyList.size(); i++) {
//				if(enemyList.get(i).getHp() < 1) {
//					id = enemyList.get(i).getId();
//				}
//			}
//		}
		
		c.getFightClubController().runFire("he " + id, c.getEnemyController().getEnemy());
	}
}
