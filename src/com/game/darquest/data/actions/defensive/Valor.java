package com.game.darquest.data.actions.defensive;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Valor implements Fireable {

	private String output;
	public Valor() {
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getDef() >= choosen.getMaxDef()) {
			output = "Target's Defense is already at it's maximum but Valor occured.";
			return;
		}
		
		choosen.setDef(choosen.getDef() + 3);
		p.setEng(p.getEng() - .1);
		
		output = "Valor successful: " + p.getName() + "\n" 
				+ "Eat: -.1\n"
				+ "Sleep: -.1\n"
				+ "Work: -.1\n"
				+ "Eng lost: -.1\n"
				+ choosen.getName() + " Defense +3";
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to use Valor";
	}

	@Override
	public String getCommandId() {
		return "va";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
