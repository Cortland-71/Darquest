package com.game.darquest.data.actions.use;

import java.text.DecimalFormat;

import com.game.darquest.data.Person;

public class HpTool implements Useable {

	private DecimalFormat f2 = new DecimalFormat("0.00");

	@Override
	public String use(Person choosen, int effect) {
		double finalEffect = (effect/100d);
		choosen.setHp(choosen.getHp() + finalEffect);
		return "Healed: " + choosen.getName()
		+ "\nHP +: "+f2.format(finalEffect);
	}

	@Override
	public String getCatagory() {
		return "hp";
	}

}
