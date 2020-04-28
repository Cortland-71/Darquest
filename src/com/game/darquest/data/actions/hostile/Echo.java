package com.game.darquest.data.actions.hostile;

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
			output = "Target's Stealth was already at it's minimum.";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		if(choosen.getAwareness() <= p.getMutation()) {
			int mutationEffect = p.getMutation() / 2;
			int before = choosen.getStealth();
			choosen.setStealth(choosen.getStealth() - mutationEffect);
			
			output = "Light successful: " + p.getName() + "\n"
					+ choosen.getName() + " Stealth -" + mutationEffect + "\n"
					+ choosen.getName() + " Stealth before " + before + "\n"
					+ choosen.getName() + " Stealth after " + choosen.getStealth() + "\n\n";
			return;
		}
		output = "Light failed: " + p.getName() + "\n"
				+ choosen.getName() + " Awareness is too high\n\n";
	}

	@Override
	public String getCommandId() {
		return "ght";
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
