package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Clear implements Fireable {

	public Clear() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCommandId() {
		return "clear";
	}

	@Override
	public String getOutput() {
		return "Cleared";
	}

	@Override
	public int getPointCost() {
		return 0;
	}

	@Override
	public boolean isModifiable() {
		return false;
	}

}
