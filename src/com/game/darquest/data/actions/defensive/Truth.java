package com.game.darquest.data.actions.defensive;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Truth implements Fireable {
	
	private String output;

	@Override
	public void fire(Person p, Person choosen) {
		
		if(choosen.getAwareness() >= choosen.getMaxAwareness()) {
			output = "Target's Awareness is already at it's maximum but Truth occured.";
			return;
		}
		
		int preserveEffect = p.getPreserve() / 2;
		int before = choosen.getAwareness();
		choosen.setAwareness(choosen.getAwareness() + preserveEffect);
		p.setEng(p.getEng() - .1);
		
		output = "Truth successful: " + p.getName() + "\n"
				+ choosen.getName() + " Awareness +" + preserveEffect + "\n"
				+ choosen.getName() + " Awareness before: " + before + "\n"
				+ choosen.getName() + " Awareness after: " + choosen.getAwareness() + "\n\n";
	}
	
	
	@Override
	public String getCommandId() {
		return "tr";
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
