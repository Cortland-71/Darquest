package com.game.darquest.data.actions.defensive;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Shadow implements Fireable {

	private String output;
	public Shadow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getStealth() >= choosen.getMaxStealth()) {
			output = "Target's Stealth is already at it's maximum but Shadow occured.";
			return;
		}
		
		
		choosen.setStealth(choosen.getStealth() + 3);
		p.setEng(p.getEng() - .1);
		
		output = "Shadow successful: " + p.getName() + "\n" 
				+ "Eat: -.1\n"
				+ "Sleep: -.1\n"
				+ "Work: -.1\n"
				+ "Eng lost: -.1\n"
				+ choosen.getName() + " Stealth +3";
		
		
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to use Shadow";
	}

	@Override
	public String getCommandId() {
		return "sh";
	}

	@Override
	public String getOutput() {
		return output;
	}
	
	@Override
	public int getPointCost() {
		return 3;
	}
	@Override
	public boolean isModifiable() {
		return true;
	}
}
