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
			System.out.println("Failed basic check Rules Class");
			return true;
		} 
		return false;
	}
}
