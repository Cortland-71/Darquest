package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;

public class PreserveRuleController implements Ruleable {

	private Controller c;
	public PreserveRuleController(Controller c) {
		this.c = c;
	}

	@Override
	public void getRule() {
		Enemy e = c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		if(e.getAwareness() < e.getMaxAwareness()) {
			c.getFightClubController().runFire("tr", e);
			System.out.println("PreserveRule: did preserve\n");
			return;
		} 
		if(e.getDef() < e.getMaxDef()) {
			c.getFightClubController().runFire("va", e);
			System.out.println("PreserveRule: did preserve\n");
			return;
		}
		if(e.getStealth() < e.getMaxStealth()) {
			c.getFightClubController().runFire("sh", e);
			System.out.println("PreserveRule: did preserve\n");
			return;
		}
			
		
		
	}

}
