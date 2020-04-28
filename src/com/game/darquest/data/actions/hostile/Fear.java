package com.game.darquest.data.actions.hostile;

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
		
		if(choosen.getAwareness() <= p.getMutation()) {
			int mutationEffect = p.getMutation() / 2;
			int before = choosen.getDef();
			choosen.setDef(choosen.getDef() - mutationEffect);
			
			output = "Fear successful: " + p.getName() + "\n"
					+ "Eng lost: -.1\n"
					+ choosen.getName() + " Defense -" + mutationEffect + "\n"
					+ choosen.getName() + " Defense before: " + before + "\n"
					+ choosen.getName() + " Defense after: " + choosen.getDef() + "\n\n";
			return;
		}
		output = "Fear failed: " + p.getName() + "\n"
				+ choosen.getName() + " Awareness is too high\n\n";
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
		return 2;
	}
	@Override
	public boolean isModifiable() {
		return true;
	}
}
