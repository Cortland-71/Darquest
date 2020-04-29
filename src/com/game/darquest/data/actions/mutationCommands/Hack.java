package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Hack implements Fireable {
	private String output;
	public Hack() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getMutation() >= choosen.getMaxMutation()) {
			output = "Target's Mutation was already at it's maximum.";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = 1;
	
		int mutationBefore = choosen.getMutation();
		int mutationAfter = choosen.getMutation() + mutationEffect;

		choosen.setMutation(mutationAfter);
		
		output = "Hack successful from " + p.getName() + "\n"
				+ "Effected: " + choosen.getName() + "\n"
				+ "Mutation        +" + mutationEffect + "\n"
				+ "Mutation before: " + mutationBefore + "\n"
				+ "Mutation after:  " + mutationAfter + "\n\n";	
	}

	@Override
	public String getCommandId() {
		return "hac";
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
