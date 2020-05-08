package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Acid implements Fireable {

	private String output;
	public Acid() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getMutation() < 2) {
			output = choosen.getName() + "'s Mutation was already at it's minimum.\n\n";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = 1;
		
		int mutationBefore = choosen.getMutation();
		int mutationAfter = choosen.getMutation() - mutationEffect;
		
		choosen.setMutation(mutationAfter);
		
		output = "Acid successful from " + p.getName() + "\n"
				+ "Target:          " + choosen.getName() + "\n"
				+ "Mutation        -" + mutationEffect + "\n"
				+ "Mutation before: " + mutationBefore + "\n"
				+ "Mutation after:  " + mutationAfter + "\n\n";
	}

	@Override
	public String getCommandId() {
		return "acd";
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
