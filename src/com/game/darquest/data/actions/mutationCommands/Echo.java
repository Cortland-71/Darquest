package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Echo implements Fireable {
	
	private String output;

	public Echo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getStealth() < 2) {
			output = "Target's Stealth was already at it's minimum.\n\n";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = (int)Math.ceil(p.getMutation()/2d);

		int stealthBefore = choosen.getStealth();
		int stealthAfter = choosen.getStealth() - mutationEffect;
		int awarenessBefore = choosen.getAwareness();
		int awarenessAfter = choosen.getAwareness() + mutationEffect;
		
		choosen.setAwareness(awarenessAfter);
		choosen.setStealth(stealthAfter);
		
		output = "Echo successful from " + p.getName() + "\n"
				+ "Target:           " + choosen.getName() + "\n"
				+ "Stealth          -" + mutationEffect + "\n"
				+ "Awareness        +" + mutationEffect + "\n"
				+ "Stealth before:   " + stealthBefore + "\n"
				+ "Stealth after:    " + stealthAfter + "\n"
				+ "Awareness before: " + awarenessBefore + "\n"
				+ "Awareness after:  " + awarenessAfter + "\n\n";
				
	}

	@Override
	public String getCommandId() {
		return "ech";
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
