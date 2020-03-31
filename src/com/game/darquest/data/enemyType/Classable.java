package com.game.darquest.data.enemyType;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public interface Classable {
	int getGenerateDef();
	int getGenerateStealth();
	int getGenerateAwareness();
	double getGeneratedCash();
	Weapon getGenerateWeapon();
	Armor getGenerateArmor();
	Tool getGenerateTool();
	String getType();
}
