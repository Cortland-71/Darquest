package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class Rules {

	public Rules() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean failedBasicCheck(Controller c, Enemy e, 
			double eatRequired, double sleepRequired, double workRequired) {
		if(e.getEat() < eatRequired) {
			c.getEnemyController().rulesForEat();
			return true;
		} else if(e.getSleep() < sleepRequired) {
			c.getEnemyController().rulesForSleep();
			return true;
		} else if(e.getWork() < workRequired) {
			c.getEnemyController().rulesForWork();
			return true;
		}
		return false;
	}
	
	public static boolean failedBasicCheck(Controller c, Enemy e) {
		
		if(e.getWork() > 0 && e.getSleep() > 0 && e.getEat() > 0) {
			c.getEnemyController().getDeceptionRuleController().getRule();
			return true;
		} else if(e.getEat() > 0 && e.getSleep() > 0 && e.getWork() == 0) {
			c.getEnemyController().getAttackRuleController().getRule();
			return true;
		} else if(e.getWork() > 0) {
			System.out.println("work is more than 0. Enemy trying to use Truth. steal rule");
			c.getEnemyController().getStealRuleController().getRule();
			return true;
		} else if(e.getEat() > 0) {
			System.out.println("eat is more than 0. Enemy trying to use Truth. Heal rule");
			c.getEnemyController().getAttackRuleController().getRule();
			return true;
		} else if(e.getSleep() > 0) {
			System.out.println("Eat is more than 0. Enemy trying to use Truth. Attacking rule");
			c.getEnemyController().getHealRuleController().getRule();
			return true;
		} 
		return false;
	}
	
	

}
