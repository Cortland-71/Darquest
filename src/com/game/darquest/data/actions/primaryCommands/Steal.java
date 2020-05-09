package com.game.darquest.data.actions.primaryCommands;

import java.text.NumberFormat;

import com.game.darquest.controller.fightClubControllers.FightClubWinController;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;

public class Steal implements Fireable {

	private String output;

	public Steal() {
	}

	@Override
	public void fire(Person p, Person choosen) {

		if (playerStealFromThemselves(p, choosen))
			return;

		// current stats from person stealing and target
		int awarenessAmount = choosen.getAwareness();
		int stealthAmount = p.getStealth();
		double choosenCashBefore = choosen.getCash();

		double finalAmountStolen = 0;

		if (p.getStealth() < choosen.getAwareness()) {
			output = "Steal failed: " + p.getName() + "\n"
					+ choosen.getName() + "'s Awareness: " + awarenessAmount + "\n" 
					+ "Awareness to high.\n\n";
			FightClubWinController.setEfficiencyScore(FightClubWinController.getEfficiencyScore() - 5);
			return;
		}

		finalAmountStolen = getFinalStealAmount(stealthAmount, awarenessAmount, choosenCashBefore);

		if(choosen instanceof Enemy) {
			Enemy e = (Enemy)choosen;
			if(e.getType().getName().equals("Observer")) {
				e.setLimit(e.getLimit()+.2);
			}
		}
		p.setCash(p.getCash() + finalAmountStolen);
		choosen.setCash(choosen.getCash() - finalAmountStolen);
		
		output = "Steal successful: " + p.getName() + "\n"
				+ choosen.getName() + " Cash -" + finalAmountStolen + "\n"
				+ choosen.getName() + " Cash before " + choosenCashBefore + "\n"
				+ choosen.getName() + " Cash after " + choosen.getCash() + "\n\n";
	}


	private double getFinalStealAmount(int stealth, int awareness, double choosenCash) {
		double amount = 0;
		amount = ((stealth - awareness)+1) * 50;
		if (amount <= choosenCash) return amount;
		return choosenCash;
	}

	private boolean playerStealFromThemselves(Person p, Person choosen) {
		if (choosen instanceof Player && p instanceof Player) {
			output = "You can't steal from yourself...";
			return true;
		}
		return false;
	}

	@Override
	public String getCommandId() {
		return "st";
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
