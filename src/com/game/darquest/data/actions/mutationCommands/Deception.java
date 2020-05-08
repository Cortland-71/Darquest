package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
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
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = (int)Math.ceil(p.getMutation()/2d);

		int awarenessBefore = choosen.getAwareness();
		int stealthBefore = choosen.getStealth();
		int finalEffect = getFinalEffect(mutationEffect, awarenessBefore);
		int awarenessAfter = choosen.getAwareness() - finalEffect;
		int stealthAfter = choosen.getStealth() + finalEffect;
		
		choosen.setAwareness(awarenessAfter);
		choosen.setStealth(stealthAfter);
		
		output = "Deception successful from " + p.getName() + "\n"
				+ "Target:           " + choosen.getName() + "\n"
				+ "Awareness        -" + mutationEffect + "\n"
				+ "Stealth          +" + mutationEffect + "\n"
				+ "Awareness before: " + awarenessBefore + "\n"
				+ "Awareness after:  " + awarenessAfter + "\n"
				+ "Stealth before:   " + stealthBefore + "\n"
				+ "Stealth after:    " + stealthAfter + "\n\n";
	}
	
	private int getFinalEffect(int mutationEffect, int before) {
		int dif = before - mutationEffect;
		if(dif >= 1) return mutationEffect;
		return (mutationEffect + dif)-1;
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
		return 1;
	}
	@Override
	public boolean isModifiable() {
		return true;
	}
}
