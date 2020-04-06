package com.game.darquest.controller.rules;

import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;

public class RuleController {
	
	protected Random rand = new Random();
	protected Person currentEnemy;
	protected List<Enemy> enemyList;	
	protected Controller c;

	public RuleController(Controller c) {
		this.c = c;
	}
	
	public void setCurrentEnemy(Person currentEnemy) {
		this.currentEnemy = currentEnemy;
	}
	
	
	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
	protected void rulesForEat() {
		double cashRequired = (currentEnemy.getEat() + .1) * 150.5;
		if(currentEnemy.getCash() >= cashRequired) {
			c.getFightClubController().runFire("eat", currentEnemy);
			return;
		}
		c.getFightClubController().runFire("work", currentEnemy);
	}
	
	protected void rulesForSleep() {
		double workRequired = .1;
		if(currentEnemy.getWork() >= workRequired) {
			c.getFightClubController().runFire("sleep", currentEnemy);
			return;
		}
		c.getFightClubController().runFire("work", currentEnemy);
	}
	
	public Person getCurrentEnemy() {
		return currentEnemy;
	}

}
