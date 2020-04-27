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
			output = "Target's Defense is already at it's maximum.";
			return;
		}
		
		int preserveEffect = p.getPreserve() / 2;
		int before = choosen.getDef();
		choosen.setDef(choosen.getDef() + preserveEffect);
		p.setEng(p.getEng() - .1);
		
		output = "Valor successful: " + p.getName() + "\n"
				+ choosen.getName() + " Defense +" + preserveEffect + "\n"
				+ choosen.getName() + " Defense before: " + before + "\n"
				+ choosen.getName() + " Defense after: " + choosen.getDef() + "\n\n";
	}

	@Override
	public String getCommandId() {
		return "va";
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
