package com.game.darquest.data.enemyType;

import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public interface Classable {
	Random rand = new Random();
	int getGenerateDef();
	int getGenerateStealth();
	int getGenerateAwareness();
	double getGeneratedCash();
	Weapon getGenerateWeapon();
	Armor getGenerateArmor();
	Tool getGenerateTool();
	String getType();
	void setLevel(int level);
	void setController(Controller c);
}
