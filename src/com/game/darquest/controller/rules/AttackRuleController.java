package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;

public class AttackRuleController implements Ruleable {

	private Controller c;
	public AttackRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		if(e.getEquippedWeapon().getMinDamage() >= p.getDef()) {
			c.getFightClubController().runFire("att 0", e);
			System.out.println("AttackRule: did attack\n");
			return;
		} else {
			if(p.getAwareness() <= e.getMutation()) {
				c.getFightClubController().runFire("fear 0", e);
			} else {
				c.getFightClubController().runFire("dec 0", e);
			}
		}
			
		System.out.println("AttackRule: did attack\n");
	}
}
