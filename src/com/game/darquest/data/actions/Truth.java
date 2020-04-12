package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Truth implements Fireable {
	
	private String output;

	public Truth() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		
		if(choosen.getAwareness() >= choosen.getMaxAwareness()) {
			output = "Target's Awareness is already at it's maximum.";
			return false;
		}
		
		if(p.getWork() == 0 && p.getSleep() == 0 && p.getEat() == 0) {
			
			choosen.setAwareness(choosen.getAwareness() + 1);
			p.setEng(p.getEng() - .2);
			
			output = "Truth successful: " + p.getName() + "\n" 
					+ "Eng lost: -" + .2 + "\n"
					+ choosen.getName() + " Awareness +" + 1;
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
