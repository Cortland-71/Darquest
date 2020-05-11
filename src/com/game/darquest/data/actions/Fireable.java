package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public interface Fireable {
	
	//Put a constant for the min a stat can be
	final int minStat = 0;
	void fire(Person p, Person choosen);
	String getCommandId();
	String getOutput();
	int getPointCost();
	boolean isModifiable();

}
