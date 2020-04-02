package com.game.darquest.data.actions.use;

import com.game.darquest.data.Person;

public interface Useable {
	String use(Person choosen, int effect);
	String getCatagory();
}
