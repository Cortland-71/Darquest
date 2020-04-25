package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public interface Fireable {
	void fire(Person p, Person choosen);
	String getCommandId();
	String getOutput();

}
