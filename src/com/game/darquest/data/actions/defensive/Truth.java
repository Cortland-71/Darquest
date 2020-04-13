package com.game.darquest.data.actions.defensive;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Truth implements Fireable {
	
	private String output;

	@Override
	public boolean fire(Person p, Person choosen) {
		
		if(p.getWork() >= .1 && p.getSleep() >= .1 && p.getEat() >= .1) {
			p.setWork(p.getWork() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setEat(p.getEat() - .1);
			
			if(choosen.getAwareness() >= choosen.getMaxAwareness()) {
				output = "Target's Awareness is already at it's maximum but Truth occured.";
				return true;
			}
			
			
			choosen.setAwareness(choosen.getAwareness() + 3);
			p.setEng(p.getEng() - .1);
			
			output = "Truth successful: " + p.getName() + "\n" 
					+ "Eat: -.1\n"
					+ "Sleep: -.1\n"
					+ "Work: -.1\n"
					+ "Eng lost: -.1\n"
					+ choosen.getName() + " Awareness +3";
			return true;
		}
		
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to use Valor";
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
