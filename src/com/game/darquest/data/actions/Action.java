package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public abstract class Action {

	public Action() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean badId(Person choosen) {
		return choosen==null ? true : false;
	}

}
