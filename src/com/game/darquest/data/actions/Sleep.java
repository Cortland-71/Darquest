package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Sleep implements Fireable {

	private String output;

	@Override
	public boolean fire(Person p, Person choosen) {

		if (p.getSleep() + .1 <= p.getMAX_BAR()) {
			if (p.getWork() > p.getMIN()) {
				p.setSleep(p.getSleep() + .1);
				p.setWork(p.getWork() - .1);

				double gainedEng = p.getSleep() / 4;
				p.setEng(p.getEng() + gainedEng);
				output = p.getName() + " feels rested.\nGained Eng: +" + gainedEng 
						+ "\nWork lost: -" + .1;
				return true;
			}
			output = p.getName() + " can't sleep right now.\n" + "You must have at least .1 work and .1 eat...";
			return false;
		}
		p.setSleep(p.getMAX_BAR());
		output = p.getName() + "... You have slept to your max capacity.";
		return false;
	}

	@Override
	public String getCommandId() {
		return "zz";
	}

	@Override
	public String getOutput() {
		return output;
	}
}