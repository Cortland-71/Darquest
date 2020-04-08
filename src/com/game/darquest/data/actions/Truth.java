package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Truth implements Fireable {
	
	private String output;

	public Truth() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		if(Action.badId(choosen)) {
			output = "That id is not recognized.";
			return false;
		}
		
		if(choosen.getAwareness() >= choosen.getMaxAwareness()) {
			output = "Target's Awareness is already at it's maximum.";
			return false;
		}
		
		if(p.getWork() == 0 && p.getSleep() == 0 && p.getEat() == 0) {
			int amountAdded = choosen.getAwareness() + 3 > choosen.getMaxAwareness() ? 
					(choosen.getAwareness() + 3) - choosen.getMaxAwareness() : choosen.getAwareness() + 3;
			choosen.setAwareness(amountAdded);
			
			output = "Truth successful: " + choosen.getName() + "\n" 
					+ choosen.getName() + " Awareness +3";
			return true;
		}
		
		output = "You must have 0 Eat 0 Sleep and 0 Work to know the Truth.";
		return false;
	}

	@Override
	public String getCommandId() {
		return "tr";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
