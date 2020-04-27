package com.game.darquest.data.actions.hostile;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Deception implements Fireable {

	private String output;
	public Deception() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getAwareness() < 2) {
			output = "Target's Awareness was already at it's minimum.\n\n";
			return;
		}
		
		int mutationEffect = p.getMutation() / 2;
		int before = choosen.getAwareness();
		choosen.setAwareness(choosen.getAwareness() - mutationEffect);
		p.setEng(p.getEng() - .1);
		
		output = "Deception successful: " + p.getName() + "\n"
				+ "Eng lost: -.1\n"
				+ choosen.getName() + " Awareness -" + mutationEffect + "\n"
				+ choosen.getName() + " Awareness before: " + before + "\n"
				+ choosen.getName() + " Awareness after: " + choosen.getAwareness() + "\n\n";
	}
	
	@Override
	public String getCommandId() {
		return "dec";
	}

	@Override
	public String getOutput() {
		return output;
	}

	@Override
	public int getPointCost() {
		return 2;
	}
	@Override
	public boolean isModifiable() {
		return true;
	}
}
