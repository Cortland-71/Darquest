package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Exe implements Fireable {

	public Exe() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCommandId() {
		return "exe";
	}

	@Override
	public String getOutput() {
		return "Executing";
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
