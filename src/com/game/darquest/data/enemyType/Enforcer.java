package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Enforcer implements Classable {
	private ItemHub ic;
	private Controller c;
	private int minStat = 5;
	private int maxStat;
	
	
	@Override
	public int getGenerateDef() {
		int defBuff = maxStat + (maxStat/2);
		return rand.nextInt((defBuff - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateStealth() {
		
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateAwareness() {
	
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.highWeaponsList().get(rand.nextInt(ic.highWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.highArmorList().get(rand.nextInt(ic.highArmorList().size()));
	}
	
	@Override
	public Tool getGenerateTool() {
		return null;
	}


	@Override
	public String getName() {
		return "Enforcer";
	}

	@Override
	public double getGeneratedCash() {
		int min = 1;
		int max = 100;
		
		int dollars = rand.nextInt((max-min)+1)+min;
		double cents = rand.nextDouble();
		
		return dollars + cents;
	}

	@Override
	public void setLevel(int level) {
		maxStat = minStat + level;		
	}

	@Override
	public void setController(Controller c) {
		this.c = c;
		this.ic = c.getItemHub();
		
	}


	public int attackQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		int score = 0;
		score += c.getPlayer().getHp() == 1 ? 1 : 0;
		score += c.getPlayer().getHp() > .5 ? 1 : 0;
		score += c.getPlayer().getHp() < .2 ? 1 : 0;
		score += c.getPlayer().getHp() > e.getHp() ? 1 : 0;
		score += c.getPlayer().getHp() == e.getHp() ? 1 : 0;
		score += c.getPlayer().getDef() <= e.getDef() ? 1 : 0;
		score += e.getEng() > .5 ? 1 : 0;
		score += e.getEng() > .6 ? 1 : 0;
		score += e.getEng() > .7 ? 1 : 0;
		score += e.getEng() > .8 ? 1 : 0;
		score += e.getEng() > .9 ? 1 : 0;
		
		System.out.println("attack score: " + score);
		return score;
	}
	
	
	public int healQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += e.getHp() < 1 ? 1 : 0;
		score += e.getHp() < .8 ? 1 : 0;
		score += e.getHp() < .5 ? 1 : 0;
		score += e.getHp() < .3 ? 2 : 0;
		score += e.getHp() < .2 ? 2 : 0;
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
	
		score += c.getPlayer().getHp() > .8 ? 1 : 0;
		score += e.getEng() < .6 ? 1 : 0;
		score += e.getEng() < .5 ? 1 : 0;
		score += e.getEng() < .4 ? 1 : 0;
		score += e.getEng() < .3 ? 1 : 0;
		score += e.getEng() < .2 ? 1 : 0;
		score += e.getEng() < .1 ? 1 : 0;
		System.out.println("Eng score: " + score);
		return score;
	}
	
	public int truthQuestions() {
		int score = 0;
		System.out.println("Truth score: " + score);
		return score;
	}
	
	public int stealQuestions() {
		int score = 0;
		System.out.println("Steal score: " + score);
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
}
