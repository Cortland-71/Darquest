package com.game.darquest.data.enemyType;

import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

public interface Classable {
	Random rand = new Random();
	int getGenerateDef();
	int getGenerateStealth();
	int getGenerateAwareness();
	double getGeneratedCash();
	Weapon getGenerateWeapon();
	Armor getGenerateArmor();
	String getName();
	void setLevel(int level);
	void setController(Controller c);
	List<Integer> getAllScores();
	String getDescription();
	boolean failedTypeCheck(Controller c, Enemy e);
}
