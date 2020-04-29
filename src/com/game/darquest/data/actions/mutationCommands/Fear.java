package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Fear implements Fireable {
	
	private String output;

	public Fear() {
		
	}

	@Override
	public void fire(Person p, Person choosen) {
		
		if(choosen.getDef() < 2) {
			output = "Target's Defense was already at it's minimum.";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = p.getMutation();

		int defBefore = choosen.getDef();
		int defAfter = choosen.getDef() - mutationEffect;
		int attackBefore = choosen.getAttack();
		int attackAfter = choosen.getAttack() + mutationEffect;
		
		choosen.setDef(defAfter);
		choosen.setAttack(attackAfter);
		
		output = "Fear successful from " + p.getName() + "\n"
				+ choosen.getName() + " Defense -" + mutationEffect + "\n"
				+ choosen.getName() + " Attack +" + mutationEffect + "\n"
				+ choosen.getName() + " Defense before: " + defBefore + "\n"
				+ choosen.getName() + " Defense after: " + defAfter + "\n"
				+ choosen.getName() + " Attack before: " + attackBefore + "\n"
				+ choosen.getName() + " Attack after: " + attackAfter + "\n\n";
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
