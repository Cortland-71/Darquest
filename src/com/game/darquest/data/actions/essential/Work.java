package com.game.darquest.data.actions.essential;

import java.text.NumberFormat;

import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class Work implements Fireable {

	private final double basePay = 8.5;
	private String output;

	@Override
	public boolean fire(Person p, Person choosen) {
		if (p.getWork() + .1 <= p.getMAX_BAR()) {

			p.setWork(p.getWork() + .1);
			double finalPay = (p.getWork() * 10) + basePay;

			p.setCash(p.getCash() + finalPay);
			
			output = "Work successful: " + p.getName() + "\nCash: +"
					+ NumberFormat.getCurrencyInstance().format(finalPay);
			return true;
		}
		p.setWork(p.getMAX_BAR());
		output = p.getName() + " cannot work anymore...\n" + "Try sleeping to recover.";
		return false;
	}

	@Override
	public String getCommandId() {
		return "wq";
	}

	@Override
	public String getOutput() {
		return output;
	}
}
