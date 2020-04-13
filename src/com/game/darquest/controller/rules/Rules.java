package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;

public class Rules {

	public Rules() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean failedBasicCheck(Controller c, Enemy e, 
			double eatRequired, double sleepRequired, double workRequired, double cashRequired) {
		if (e.getCash() < cashRequired) {
			c.getEnemyController().rulesForWork();
			return true;
		} else if(e.getEat() < eatRequired) {
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
}
