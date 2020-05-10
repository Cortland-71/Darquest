package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Observer implements Classable {

	private ItemHub ic;
	private Controller c;

	private int minStat = 5;
	private int maxStat;
	
	@Override
	public int getGenerateAttack() {
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}
	
	
	
	@Override
	public int getGenerateDef() {
		
		return rand.nextInt((minStat-1)+1)+1;
	}

	@Override
	public int getGenerateStealth() {
		return rand.nextInt((minStat-1)+1)+1;
	}

	@Override
	public int getGenerateAwareness() {
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}
	

	@Override
	public int getGenerateMutation() {
		return rand.nextInt(((maxStat+minStat) - minStat)+1)+minStat;
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
		minStat = level*2;
		maxStat = minStat + level;
	}

	@Override
	public void setController(Controller c) {
		this.ic = c.getItemHub();
		this.c = c;
		
	}
	
	public int attackQuestions() {
		int score = 4;
		System.out.println("attack score: " + score);
		return score;
	}

	public int healQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		
		int score = 0;
		score += e.getHp() < 1 ? 1 : 0;
		score *= (Math.round((1-e.getHp())*10d));
 		System.out.println("Heal score: " + score);
		return score;
	}
	
	
	public int preserveQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		
		int score = 0, dif = 0;
		score += e.getAwareness() < e.getDefaultAwareness() ? 1 : 0;
		score += e.getDef() < e.getDefaultDef() ? 1 : 0;
		score += e.getStealth() < e.getDefaultStealth() ? 1 : 0;
		dif = e.getDefaultAwareness() - e.getAwareness();
		dif += e.getDefaultDef() - e.getDef();
		dif += e.getDefaultStealth() - e.getStealth();
		score *= dif;
		System.out.println("Preserve score: " + score);
		return score;
	}

	public int getNoScore() {
		int score = 0;
		return score;
	}
	
	@Override
	public List<Integer> getAllScores() {
		List<Integer> allScores = new ArrayList<>();
		allScores.add(attackQuestions()); //Attack
		allScores.add(getNoScore()); //Steal
		allScores.add(healQuestions()); //Heal
		allScores.add(preserveQuestions()); //Preserve
		
		return allScores;
	}
	@Override
	public String getDescription() {
		return "Observer description";
	}



	@Override
	public List<String> getAllowedMoves() {
		return Arrays.asList("dec", "fear", "ech", "hac", "shd", "acd", "att", "he");
	}
}
