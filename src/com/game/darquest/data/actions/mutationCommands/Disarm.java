package com.game.darquest.data.actions.mutationCommands;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;

public class Disarm implements Fireable {

	private String output;
	public Disarm() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		if(choosen.getAttack() < 2) {
			output = choosen.getName() + "'s Attack was already at it's minimum.\n\n";
			if(p instanceof Player)
				FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}
		
		int mutationEffect = p.getMutation();
		
		int defBefore = choosen.getDef();
		int attackBefore = choosen.getAttack();
		
		int finalEffect = getFinalEffect(mutationEffect, attackBefore);
		int attackAfter = choosen.getAttack() - finalEffect;
		int defAfter = choosen.getDef() + finalEffect;
	
		
		choosen.setDef(defAfter);
		choosen.setAttack(attackAfter);
		
		output = "Disarm successful from " + p.getName() + "\n"
				+ "Target:         " + choosen.getName() + "\n"
				+ "Attack         -" + mutationEffect + "\n"
				+ "Defense        +" + mutationEffect + "\n"
				+ "Attack before:  " + attackBefore + "\n"
				+ "Attack after:   " + attackAfter + "\n"
				+ "Defense before: " + defBefore + "\n"
				+ "Defense after:  " + defAfter + "\n\n";
	}
	
	private int getFinalEffect(int mutationEffect, int before) {
		int dif = mutationEffect - before;
		if(dif < 0 ) return mutationEffect-1;
		else if(dif > 0) return dif;
		else return mutationEffect - 1;
	}

	@Override
	public String getCommandId() {
		return "da";
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
