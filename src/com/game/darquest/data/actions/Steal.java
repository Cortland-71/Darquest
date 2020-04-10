package com.game.darquest.data.actions;

import java.text.NumberFormat;

import com.game.darquest.data.Enemy;
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
		
		if(p.getWork() >= .1) {
			
			if(choosen instanceof Player && p instanceof Player) {
				output = "You can't steal from yourself...";
				return false;
			}
			
			
			
			double choosenCash = choosen.getCash();
			int stealthAmount = p.getStealth();
			int awarenessAmount = choosen.getAwareness();
			double amountStolen = stealthAmount * stealthAmount;
			double engBonus = (p.getEng() * 100d) * 3d;
			
			if(p instanceof Enemy) {
				engBonus = (p.getEng() * 100d) * 10d;
			} 
			
			if(p.getEng() > 0) amountStolen += engBonus;
			
			if(stealthAmount == awarenessAmount) {
				stealTransaction(p, choosen, amountStolen, choosenCash);
				return true;
			}
			
			double finalAmountStolen = amountStolen - (awarenessAmount * awarenessAmount);
			System.out.println("Steal class finalAmount" + finalAmountStolen);
			if(finalAmountStolen > 0) {
				stealTransaction(p, choosen, finalAmountStolen, choosenCash);
				return true;
			}
			
			output = "Steal Failed. "+ choosen.getName() +"'s Awareness to to high.";
			return false;
		}
		output = "You must have at least .2 work to steal...";
		return false;
	}
	
	private void stealTransaction(Person p, Person choosen, double finalAmountStolen, double choosenCash) {
		p.setWork(p.getWork()-.1);
		p.setEng(p.getEng() - .2);
		
		if(finalAmountStolen > choosenCash) finalAmountStolen = choosenCash;
		p.setCash(p.getCash() + finalAmountStolen);
		choosen.setCash(choosen.getCash() - finalAmountStolen);
		output = "Steal: " + NumberFormat.getCurrencyInstance().format(finalAmountStolen);
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
