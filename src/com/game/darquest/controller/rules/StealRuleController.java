package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class StealRuleController implements Ruleable {

	private Controller c;
	public StealRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		
		if (e.getStealth() < c.getPlayer().getAwareness()) {
			if(e.getStealth() < e.getMaxStealth()) {
				c.getFightClubController().runFire("sh", e);
				return;
			}
			c.getFightClubController().runFire("dec 0", e);
			return;
		}
		c.getFightClubController().runFire("st 0", e);
		System.out.println("StealRule: Did steal\n");
	}
	
}
