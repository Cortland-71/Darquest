package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Observer implements Classable {

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
		return 1;
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
		return (Armor)ic.midArmorList().get(rand.nextInt(ic.midArmorList().size()));
	}
	
	

	@Override
	public String getName() {
		return "Observer";
	}

	@Override
	public double getGeneratedCash() {
		int min = 1000;
		int max = 10000;
		
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
		this.ic = c.getItemHub();
		this.c = c;
		
	}

	public int attackQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		int score = 5;
		
		System.out.println("attack score: " + score);
		return score;
	}

	public int getNoScore() {
		int score = 0;
		return score;
	}
	
	@Override
	public List<Integer> getAllScores() {
		List<Integer> allScores = new ArrayList<>();
		allScores.add(getNoScore()); //Eng
		allScores.add(attackQuestions()); //Attack
		allScores.add(getNoScore()); //Steal
		allScores.add(getNoScore()); //Deception
		allScores.add(getNoScore()); //Fear
		allScores.add(getNoScore()); //Heal
		allScores.add(getNoScore()); //Truth
		allScores.add(getNoScore()); //Valor
		allScores.add(getNoScore()); //Light
		allScores.add(getNoScore()); //Shadow
		
		return allScores;
	}
	@Override
	public String getDescription() {
		return "Observer description";
	}
}
