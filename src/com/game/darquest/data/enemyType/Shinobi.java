package com.game.darquest.data.enemyType;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.ItemHub;
import com.game.darquest.data.items.Weapon;

public class Shinobi implements Classable {

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
	public int getGenerateMutation() {
		return rand.nextInt((maxStat - minStat)+1)+minStat;
	}

	@Override
	public int getGenerateSecurity() {
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
	
	//Hostile questions
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

		public int stealQuestions() {
			Enemy e = (Enemy)c.getEnemyController().getEnemy();
			Player p = (Player)c.getPlayer();
			
			int score = 0;
			score += e.getCash() < 50 ? 2 : 0;
			score *= (Math.round((1-e.getHp())*10d));
			System.out.println("Steal score: " + score);
			return score;
		}
		
		public int preserveQuestions() {
			Enemy e = (Enemy)c.getEnemyController().getEnemy();
			Player p = (Player)c.getPlayer();
			
			int score = 0, dif = 0;
			score += e.getAwareness() < e.getMaxAwareness() ? 1 : 0;
			score += e.getDef() < e.getMaxDef() ? 1 : 0;
			score += e.getStealth() < e.getMaxStealth() ? 1 : 0;
			dif = e.getMaxAwareness() - e.getAwareness();
			dif += e.getMaxDef() - e.getDef();
			dif += e.getMaxStealth() - e.getStealth();
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
		allScores.add(stealQuestions()); //Steal
		allScores.add(getNoScore()); //Heal
		allScores.add(preserveQuestions()); //Preserve
		
		return allScores;
	}
	

	@Override
	public String getDescription() {
		return "Shinobi description";
	}
}
