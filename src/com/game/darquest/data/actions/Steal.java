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

		if (playerStealFromThemselves(p, choosen))
			return false;

		if (p.getWork() >= .1 && p.getSleep() >= .1) {

			int awarenessAmount = choosen.getAwareness();
			int stealthAmount = p.getStealth();
			double eng = p.getEng();

			double initStolen = getInitStolen(stealthAmount);

			double stolenWithEngMult = getStealWithEngMult(eng, initStolen);
			double finalAmountStolen = getFinalStealAmount(stealthAmount, awarenessAmount, stolenWithEngMult);
			double limitRaised = 0;

			p.setWork(p.getWork() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setEng(p.getEng() - .2);

			if (p.getStealth() < choosen.getAwareness()) {
				output = "You got caught." + "\nStealth: " + p.getStealth() + "\n" + choosen.getName() + "'s Awareness: "
						+ choosen.getAwareness() + "\nAwareness to high.";
				return true;
			}

			if (choosen instanceof Enemy) {
				Enemy e = (Enemy) choosen;
				if (e.getType().getName().equals("Observer")) {
					if (e.getLimit() < 1) {
						limitRaised = initStolen / 1000;
						e.setLimit(e.getLimit() + limitRaised);
					}
				}
			}

			p.setCash(p.getCash() + finalAmountStolen);
			choosen.setCash(choosen.getCash() - finalAmountStolen);
			output = "Limit raised: +" + Math.round(limitRaised * 100d) / 100d + "\n\nStolen from: " + choosen.getName()
					+ "\nInit stolen: " + initStolen + "\nInit stolen + Eng multiplyer: " + stolenWithEngMult + "\n"
					+ choosen.getName() + "'s Awareness: " + choosen.getAwareness() + "\nFinal amount Stolen: "
					+ NumberFormat.getCurrencyInstance().format(finalAmountStolen);
			return true;
		}
		output = "You must have at least .1 Work and .1 Sleep to steal...";
		return false;
	}

	private double getInitStolen(int stealthAmount) {
		return stealthAmount * 50;
	}

	private double getStealWithEngMult(double eng, double initStolen) {
		if (eng > 0)
			return initStolen + (eng * 200);
		return initStolen;
	}

	private double getFinalStealAmount(int stealth, int awareness, double stealWithEngMult) {
		if (stealth == awareness)
			return stealWithEngMult;

		double amount = stealWithEngMult + ((stealth - awareness) * 10);
		return amount;
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
}
