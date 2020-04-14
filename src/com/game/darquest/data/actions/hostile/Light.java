package com.game.darquest.data.actions.hostile;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Light implements Fireable {
	
	private String output;

	public Light() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		if(p.getWork() >= .1 && p.getSleep() >= .1 && p.getEat() >= .1) {
			p.setWork(p.getWork() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setEat(p.getEat() - .1);
			
			if(choosen.getStealth() < 2) {
				output = "Target's Stealth was already at it's minimum but Light took place.";
				return true;
			}
			
			choosen.setStealth(choosen.getStealth() - 3);
			p.setEng(p.getEng() - .1);
			
			output = "Light successful: " + p.getName() + "\n" 
					+ "Eat: -.1\n"
					+ "Sleep: -.1\n"
					+ "Work: -.1\n"
					+ "Eng lost: -.1\n"
					+ choosen.getName() + " Stealth -3";
			return true;
		}
		
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to use Light";
		return false;
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
