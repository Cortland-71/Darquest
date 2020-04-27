package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Enforcer implements Classable {
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
		return rand.nextInt((maxStat - minStat)+1)+minStat;
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
		Player p = (Player)c.getPlayer();
		
		int score = 0;
		score += e.getEquippedWeapon().getMinDamage() >= p.getDef() ? 2 : 0;
		System.out.println("attack score: " + score);
		return score;
	}
	
	public int stealQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		
		int score = 0;
		score += e.getCash() < 50 ? 2 : 0;
		score *= (Math.round((1-e.getHp())*10d));
		System.out.println("Steal score: " + score);
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
	
	public int truthQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		
		int score = 0;
		score += e.getAwareness() < e.getMaxAwareness() ? 1 : 0;
		score *= (e.getMaxAwareness()-e.getAwareness());
		System.out.println("Truth score: " + score);
		return score;
	}
	
	public int valorQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		
		int score = 0;
		score += e.getDef() < e.getMaxDef() ? 1 : 0;
		score *= (e.getMaxDef() - e.getDef());
		System.out.println("Valor score: " + score);
		return score;
	}
	
	public int shadowQuestions() {
		Enemy e = (Enemy)c.getEnemyController().getEnemy();
		Player p = (Player)c.getPlayer();
		
		int score = 0;
		score += e.getStealth() < e.getMaxStealth() ? 1 : 0;
		score *= (e.getMaxStealth() - e.getStealth());
		System.out.println("Shadow score: " + score);
		return score;
	}
	
	


	public int getNoScore() {
		int score = 0;
		return score;
	}
	
	@Override
	public List<Integer> getAllScores() {
		
//		attackRuleController, 
//		stealRuleController,
//		deceptionRuleController,
//		fearRuleController,
//		lightRuleController,
//		healRuleController,
//		truthRuleController,
//		valorRuleController,
//		shadowRuleController);
		List<Integer> allScores = new ArrayList<>();
		allScores.add(attackQuestions()); //Attack
		allScores.add(stealQuestions()); //Steal
		allScores.add(getNoScore()); //Deception
		allScores.add(getNoScore()); //Fear
		allScores.add(getNoScore()); //Light
		allScores.add(healQuestions()); //Heal
		allScores.add(truthQuestions()); //Truth
		allScores.add(valorQuestions()); //Valor
		allScores.add(shadowQuestions()); //Shadow
		
		return allScores;
	}
	@Override
	public String getDescription() {
		return "Enforcer description";
	}
	


	
}
