package com.game.darquest.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.enemyType.Classable;
import com.game.darquest.data.enemyType.Enforcer;
import com.game.darquest.data.enemyType.Observer;
import com.game.darquest.data.enemyType.Shinobi;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public class EnemyGenerator {

	private List<Enemy> enemyList;
	private Random rand = new Random();
	private Controller c;

	private List<String> enemyPics = Arrays.asList("Enforcer1Big.png", "Shinobi1Big.png", "Runner1.png",
			"Shinobi2Rev.png");

	public EnemyGenerator(Controller c) {
		this.c = c;
	}

	public void generateEnemys() {
		enemyList = new ArrayList<>();
		int numberOfEnemies = getRandomNumberOfEnemies();

		for (int i = 0; i < numberOfEnemies; i++) {
			String picture = enemyPics.get(rand.nextInt(enemyPics.size()));
			String name = getRandomName();
			int level = getRandomLevel();
			Classable type = getRandomType();
			type.setController(this.c);
			enemyList.add(getEnemyObject(name, level, type, i, picture));
		}
	}

	private Enemy getEnemyObject(String name, int level, Classable type, int index, String imagePath) {
		Classable t = type;
		int pointsToSpend = 0;
		int attack = 5;
		int def = 5;
		int stealth = 5;
		int awareness = 5;
		int mutation = 5;
		List<Integer> statList = Arrays.asList(attack, def, stealth, awareness, mutation);
		
		if(level > 1) {
			for (int i = 0; i < level-1; i++) pointsToSpend += 3;
		}
		
		for (int i = 0; i < pointsToSpend; i++) {
			Random rand = new Random();
			int choosenIndex = rand.nextInt(statList.size());
			statList.set(choosenIndex, statList.get(choosenIndex)+1);	
		}

		Weapon wep = t.getGenerateWeapon();
		Armor armor = t.getGenerateArmor();
		double cash = t.getGeneratedCash();

		return new Enemy(name, wep, armor, level, cash, type, index + 1, imagePath, statList);
	}

	private Classable getRandomType() {
		List<Classable> classList = Arrays.asList(
				new Enforcer(),
				//new Observer());
				new Shinobi());
		return classList.get(rand.nextInt(classList.size()));
	}

	private int getRandomLevel() {
		int playerLevel = c.getPlayer().getLvl();
		int min = 1;
		int max = playerLevel * 2;
		return rand.nextInt((max - min) + 1) + min;
	}

	private String getRandomName() {
		List<String> enemyNames = Arrays.asList("Joclop", "Dead Martin", "71", "Scog", "Wool", "Forek", "Beatle",
				"Amarillo", "Rotter", "Fat Head", "Solomon", "Jac", "Kieron", "Xanhast", "Thoth", "The Bastard");
		return enemyNames.get(rand.nextInt(enemyNames.size()));
	}

	private int getRandomNumberOfEnemies() {
		 //return rand.nextInt((3 - 1) + 1) + 1;
		return rand.nextInt((3 - 3) + 1) + 3;
		//return rand.nextInt((2 - 2) + 1) + 2;
		 //return rand.nextInt((1 - 1) + 1) + 1;

	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

}
