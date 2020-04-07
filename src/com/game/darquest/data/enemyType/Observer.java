package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class Observer implements Classable {

	private ItemHub ic;
	private Controller c;
	private int level;

	@Override
	public int getGenerateDef() {
		return rand.nextInt(level*2);
	}

	@Override
	public int getGenerateStealth() {
		return rand.nextInt(level);
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt(level*5);
	}

	@Override
	public Weapon getGenerateWeapon() {
		return (Weapon)ic.lowWeaponsList().get(rand.nextInt(ic.lowWeaponsList().size()));
	}

	@Override
	public Armor getGenerateArmor() {
		return (Armor)ic.midArmorList().get(rand.nextInt(ic.midArmorList().size()));
	}
	
	

	@Override
	public String getName() {
		return "Observer";
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
	public Tool getGenerateTool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLevel(int level) {
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

	@Override
	public List<Integer> getAllScores() {
		List<Integer> allScores = new ArrayList<>();
		allScores.add(attackQuestions());
		allScores.add(stealQuestions());
		allScores.add(healQuestions());
		
		return allScores;
	}

}
