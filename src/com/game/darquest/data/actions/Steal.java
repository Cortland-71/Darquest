package com.game.darquest.data.actions;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

public class Steal implements Fireable {

	private String output;
	public Steal() {
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		if(Action.badId(choosen)) {
			output = "That id is not recognized.";
			return false;
		}
		
		
		if(p.getWork() >= .2) {
			
			if(choosen instanceof Player && p instanceof Player) {
				output = "You can't steal from yourself...";
				return false;
			}
			
			
			p.setWork(p.getWork()-.2);
			
			int stealthAmount = p.getStealth();
			int enemyAwareness = choosen.getAwareness();
			double choosenCash = choosen.getCash();
			
			double amountStolen = stealthAmount * 50;
			double finalAmountStolen = amountStolen - (enemyAwareness * 50);
			if(finalAmountStolen > 0) {
				if(finalAmountStolen > choosenCash) {
					p.setCash(p.getCash() + choosenCash);
					choosen.setCash(0);
				} else {
					p.setCash(p.getCash() + finalAmountStolen);
					choosen.setCash(choosen.getCash() - finalAmountStolen);
				}
				output = "Amount stolen: " + finalAmountStolen;
				return true;
			}
			
			output = "Failed stealing. "+ choosen.getName() +"'s Awareness to to high.";
			return true;
		}
		output = "You must have at least .2 work to steal...";
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
}
