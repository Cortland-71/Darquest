package com.game.darquest.data.actions.hostile;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Light implements Fireable {
	
	private String output;

	public Light() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getStealth() < 2) {
			output = "Target's Stealth was already at it's minimum but Light took place.";
			return;
		}
		
		choosen.setStealth(choosen.getStealth() - 3);
		p.setEng(p.getEng() - .1);
		
		output = "Light successful: " + p.getName() + "\n" 
				+ "Eat: -.1\n"
				+ "Sleep: -.1\n"
				+ "Work: -.1\n"
				+ "Eng lost: -.1\n"
				+ choosen.getName() + " Stealth -3";
	}

	@Override
	public String getCommandId() {
		return "tt";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
