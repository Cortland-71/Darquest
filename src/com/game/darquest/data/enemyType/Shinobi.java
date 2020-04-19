package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Shinobi implements Classable {

	private ItemHub ic;
	private Controller c;

	private int minStat = 5;
	private int maxStat;
	
	@Override
	public int getGenerateDef() {
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateStealth() {
		int stealthBuff = maxStat + (maxStat/2);
		return rand.nextInt((stealthBuff - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.lowWeaponsList().get(rand.nextInt(ic.lowWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.lowArmorList().get(rand.nextInt(ic.lowArmorList().size()));
	}

	@Override
	public String getName() {
		return "Shinobi";
	}

	@Override
	public double getGeneratedCash() {
		int min = 100;
		int max = 5000;
		
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
		score += e.getEng() > .2 ? 1 : 0;
		score += e.getEng() > .3 ? 1 : 0;
		score += e.getEng() > .4 ? 1 : 0;
		System.out.println("attack score: " + score);
		return score;
	}
	
	public int stealQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		int score = 0;
		score += c.getPlayer().getCash() > e.getCash() ? 1 : 0;
		score += c.getPlayer().getCash() > 500 ? 2 : 0;
		score += e.getCash() < 500 ? 1 : 0;
		score += c.getPlayer().getAwareness() < c.getPlayer().getMaxAwareness() ? 1 : 0;
		score += c.getPlayer().getAwareness() > e.getAwareness() ? 1 : 0;
		score += c.getPlayer().getAwareness() == e.getAwareness() ? 1 : 0;
		score += e.getAwareness() > c.getPlayer().getAwareness() ? 1 : 0;
		score += e.getAwareness() < c.getPlayer().getAwareness() ? 2 : 0;
		System.out.println("Steal score: " + score);
		return score;
	}
	
	public int healQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += e.getHp() < 1 ? 1 : 0;
		score += e.getHp() < .8 ? 1 : 0;
		score += e.getHp() < .7 ? 1 : 0;
		score += e.getHp() < .6 ? 1 : 0;
		score += e.getHp() < .5 ? 1 : 0;
		score += e.getHp() < .3 ? 1 : 0;
		score += e.getHp() < .2 ? 1 : 0;
		score += c.getPlayer().getHp() > e.getHp() ? 1 : 0;
		for(Enemy enemy : enemyList) {
			score += enemy.getHp() < .5 ? 1 : 0;
		}
		System.out.println("Heal score: " + score);
		return score;
 	}
	
	public int engQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		int score = 0;
	
		score += c.getPlayer().getHp() >= .8 ? 1 : 0;
		score += e.getHp() < 1 ? 1 : 0;
		score += e.getEng() < .4 ? 1 : 0;
		score += e.getEng() < .3 ? 1 : 0;
		score += e.getEng() < .2 ? 2 : 0;
		score += e.getEng() < .1 ? 3 : 0;
		System.out.println("Eng score: " + score);
		return score;
	}
	
	public int fearQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += c.getPlayer().getDef() > e.getEquippedWeapon().getMinDamage() ? 4 : 0;
		for(Enemy enemy : enemyList) {
			score += c.getPlayer().getDef() > enemy.getEquippedWeapon().getMinDamage() ? 2 : 0;
		}
		System.out.println("Fear score: " + score);
		return score;
	}
	
	private int deceptionQuestions() {
		int score = 0;
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		score += c.getPlayer().getAwareness() == c.getPlayer().getMaxAwareness() ? 2 : 0;
		for(Enemy enemy : enemyList) {
			score += c.getPlayer().getAwareness() >= enemy.getAwareness() ? 2 : 0;
		}
		System.out.println("Dec score: " + score);
		return score;
	}
	
	private int lightQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		List<Enemy> enemyList = c.getEnemyController().getEnemyList();
		int score = 0;
		score += c.getPlayer().getStealth() > e.getAwareness() ? 2 : 0;
		score += c.getPlayer().getStealth() == e.getAwareness() ? 1 : 0;
		score += c.getPlayer().getCash() > 1000 ? 1 : 0;
		for(Enemy enemy : enemyList) {
			score += c.getPlayer().getStealth() >= enemy.getAwareness() ? 2 : 0;
		}
		System.out.println("Light score: " + score);
		return score;
	}
	
	public int getNoScore() {
		int score = 0;
		return score;
	}
	

	@Override
	public List<Integer> getAllScores() {
		List<Integer> allScores = new ArrayList<>();
		allScores.add(engQuestions()); //Eng
		allScores.add(attackQuestions()); //Attack
		allScores.add(stealQuestions()); // Steal
		allScores.add(deceptionQuestions()); // Deception
		allScores.add(fearQuestions()); // Fear
		allScores.add(healQuestions()); //Heal
		allScores.add(getNoScore()); //Truth
		allScores.add(getNoScore()); // Valor
		allScores.add(lightQuestions()); //Light
		allScores.add(getNoScore()); //Shadow
		
		return allScores;
	}

	@Override
	public String getDescription() {
		return "Shinobi description";
	}
}
