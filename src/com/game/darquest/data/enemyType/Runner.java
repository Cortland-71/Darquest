package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Runner implements Classable {

	private ItemHub ic;
	private Controller c;
	private int level;
	private int minStat = 5;
	private int maxStat;
	
	@Override
	public int getGenerateDef() {
		maxStat = minStat * level;
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateStealth() {
		maxStat = minStat * level;
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateAwareness() {
		maxStat = minStat * level;
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}


	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.midWeaponsList().get(rand.nextInt(ic.midWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.lowArmorList().get(rand.nextInt(ic.lowArmorList().size()));
	}

	@Override
	public String getName() {
		return "Runner";
	}

	@Override
	public double getGeneratedCash() {
		int min = 100;
		int max = 1000;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	@Override
	public void setLevel(int level) {
		maxStat = minStat + level;
		this.level = level;
		
	}
	
	@Override
	public void setController(Controller c) {
		this.ic = c.getItemHub();
		this.c = c;
		
	}


	public int attackQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += c.getPlayer().getHp() == 1 ? 1 : 0;
		score += c.getPlayer().getHp() > .5 ? 1 : 0;
		score += c.getPlayer().getHp() < .2 ? 1 : 0;
		score += c.getPlayer().getHp() > e.getHp() ? 1 : 0;
		score += c.getPlayer().getHp() == e.getHp() ? 1 : 0;
		score += c.getPlayer().getDef() <= e.getDef() ? 1 : 0;
		score += e.getEng() > .3 ? 1 : 0;
		for(Enemy enemy : enemyList) {
			score += enemy.getType().getName().equals("Enforcer") ? 1 : 0;
		}
		
		System.out.println("attack score: " + score);
		return score;
	}
	
	public int stealQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		int score = 0;
		score += c.getPlayer().getCash() > e.getCash() ? 1 : 0;
		score += c.getPlayer().getCash() > 500 ? 1 : 0;
		score += c.getPlayer().getWork() <= .2 ? 1 : 0;
		score += c.getPlayer().getEat() > .2 ? 1 : 0;
		score += e.getCash() > 1000 ? 1 : 0;
		score += e.getCash() < 1000 ? 1 : 0;
		
		System.out.println("Steal score: " + score);
		return score;
	}
	
	public int healQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += e.getHp() < 1 ? 1 : 0;
		score += e.getHp() < .8 ? 1 : 0;
		score += e.getHp() < .5 ? 1 : 0;
		score += e.getHp() < .3 ? 1 : 0;
		score += e.getHp() < .2 ? 1 : 0;
		score += c.getPlayer().getHp() > e.getHp() ? 1 : 0;
		for(Enemy enemy : enemyList) {
			score += enemy.getHp() > e.getHp() ? 1 : 0;
		}
		
		System.out.println("Heal score: " + score);
		return score;
 	}
	
	public int engQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		int score = 0;
		
		score += c.getPlayer().getEng() > e.getEng() ? 1 : 0;
		score += e.getEng() < 1 ? 1 : 0;
		score += e.getEng() < .5 ? 1 : 0;
		score += e.getEng() < .4 ? 1 : 0;
		System.out.println("Eng score: " + score);
		return score;
	}

	public int truthQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += e.getAwareness() < e.getMaxAwareness() ? 2 : 0;
		for(Enemy enemy : enemyList) {
			score += enemy.getAwareness() < enemy.getMaxAwareness() ? 2 : 0;
		}
		
		System.out.println("Truth score: " + score);
		return score;
		
	}
	

	@Override
	public List<Integer> getAllScores() {
		List<Integer> allScores = new ArrayList<>();
		allScores.add(attackQuestions());
		allScores.add(stealQuestions());
		allScores.add(healQuestions());
		allScores.add(truthQuestions());
		allScores.add(engQuestions());
		
		return allScores;
	}
	
	@Override
	public String getDescription() {
		return "Runner description";
	}

	@Override
	public boolean failedTypeCheck(Controller c, Enemy e) {
		// TODO Auto-generated method stub
		return false;
	}
}
