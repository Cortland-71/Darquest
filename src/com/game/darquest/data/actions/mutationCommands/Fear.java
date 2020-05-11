package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;

public class Fear implements Fireable {
	
	private String output;

	public Fear() {
		
	}

	@Override
	public void fire(Person p, Person choosen) {
		
		if(choosen.getDef() <= minStat) {
			output = "Target's Defense was already at it's minimum.\n\n";
			if(p instanceof Player)
				FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = p.getMutation();
		
		int attackBefore = choosen.getAttack();
		int defBefore = choosen.getDef();
		
		int finalEffect = getFinalEffect(mutationEffect, defBefore);
		int defAfter = choosen.getDef() - finalEffect;
		int attackAfter = choosen.getAttack() + finalEffect;
		
		choosen.setDef(defAfter);
		choosen.setAttack(attackAfter);
		
		output = "Fear successful from " + p.getName() + "\n"
				+ "Target:         " + choosen.getName() + "\n"
				+ "Defense        -" + mutationEffect + "\n"
				+ "Attack         +" + mutationEffect + "\n"
				+ "Defense before: " + defBefore + "\n"
				+ "Defense after:  " + defAfter + "\n"
				+ "Attack before:  " + attackBefore + "\n"
				+ "Attack after:   " + attackAfter + "\n\n";
	}
	
	private int getFinalEffect(int mutationEffect, int before) {
		int dif = before - mutationEffect;
		if(dif >= 0 ) return mutationEffect;
		else return mutationEffect + dif;
	}

	@Override
	public String getCommandId() {
		return "fear";
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
