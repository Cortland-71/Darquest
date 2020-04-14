package com.game.darquest.data.actions.defensive;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Shadow implements Fireable {

	private String output;
	public Shadow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		if(p.getWork() >= .1 && p.getSleep() >= .1 && p.getEat() >= .1) {
			p.setWork(p.getWork() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setEat(p.getEat() - .1);
			
			if(choosen.getStealth() >= choosen.getMaxStealth()) {
				output = "Target's Stealth is already at it's maximum but Shadow occured.";
				return true;
			}
			
			
			choosen.setStealth(choosen.getStealth() + 3);
			p.setEng(p.getEng() - .1);
			
			output = "Shadow successful: " + p.getName() + "\n" 
					+ "Eat: -.1\n"
					+ "Sleep: -.1\n"
					+ "Work: -.1\n"
					+ "Eng lost: -.1\n"
					+ choosen.getName() + " Stealth +3";
			return true;
		}
		
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to use Shadow";
		return false;
	}

	@Override
	public String getCommandId() {
		return "sh";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
