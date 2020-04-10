package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Deception implements Fireable {

	private String output;
	public Deception() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		
		if(choosen.getAwareness() < 2) {
			output = "Target's Awareness was already at it's minimum but deception took place.";
			return true;
		}
		
		if(p.getWork() >= .1 && p.getSleep() >= .1 && p.getEat() >= .1) {
			p.setWork(p.getWork() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setEat(p.getEat() - .1);
			
			choosen.setAwareness(choosen.getAwareness() - 3);
			
			output = "Deception successful: " + p.getName() + "\n" 
					+ "Eat: -.1\n"
					+ "Sleep: -.1\n"
					+ "Work: -.1\n"
					+ choosen.getName() + " Awareness -3";
			return true;
		}
		
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to Decept";
		return false;
	}
	
	@Override
	public String getCommandId() {
		return "dec";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
