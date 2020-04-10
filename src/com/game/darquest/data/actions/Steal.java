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
		
		if(p.getWork() >= .1 && p.getSleep() >= .1) {
			
			double choosenCash = choosen.getCash();
			int stealthAmount = p.getStealth();
			int awarenessAmount = choosen.getAwareness();
			double amountStolen = stealthAmount * stealthAmount;
			double engBonus = (p.getEng() * 100d) * 3d;
			
			
			if(choosen instanceof Player && p instanceof Player) {
				output = "You can't steal from yourself...";
				return false;
			}
			
			if(stealthAmount < awarenessAmount) {
				output = "Steal Failed. "+ choosen.getName() +"'s Awareness to to high.";
				return false;
			}
			
			if(p instanceof Enemy) {
				if(((Enemy) p).getType().getName().equals("Shinobi") || 
						((Enemy) p).getType().getName().equals("Observer")) {
					engBonus = (p.getEng() * 100d) * 50d;
				}
			} 
			
			if(p.getEng() > 0) amountStolen += engBonus;
			
			if(stealthAmount == awarenessAmount) {
				stealTransaction(p, choosen, amountStolen, choosenCash);
				return true;
			}
			
			double finalAmountStolen = amountStolen - (awarenessAmount * awarenessAmount);
			if(finalAmountStolen > 0) {
				stealTransaction(p, choosen, finalAmountStolen, choosenCash);
				return true;
			}
		}
		output = "You must have at least .1 Work and .1 Sleep to steal...";
		return false;
	}
	
	private void stealTransaction(Person p, Person choosen, double finalAmountStolen, double choosenCash) {
		p.setWork(p.getWork()-.1);
		p.setSleep(p.getSleep() - .1);
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
