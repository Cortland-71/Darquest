package com.game.darquest.data.actions.essential;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;

public class Sleep implements Fireable {

	private String output;

	@Override
	public boolean fire(Person p, Person choosen) {

		if (p.getSleep() + .1 <= p.getMAX_BAR()) {
			if (p.getWork() > p.getMIN()) {
				p.setSleep(p.getSleep() + .1);
				p.setWork(p.getWork() - .1);
				
				double gainedEng = 0;

				if(p instanceof Player) {
					gainedEng = p.getSleep() / 4;
				} else {
					gainedEng = p.getSleep() / 2;
				}
				
				p.setEng(p.getEng() + gainedEng);
				output = "Sleep successful: " + p.getName() + "\nEng: +" + gainedEng 
						+ "\nWork: -" + .1;
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