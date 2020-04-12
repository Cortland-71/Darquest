package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Fear implements Fireable {
	
	private String output;

	public Fear() {
		
	}

	@Override
	public boolean fire(Person p, Person choosen) {
		
		if(p.getWork() >= .1 && p.getSleep() >= .1 && p.getEat() >= .1) {
			p.setWork(p.getWork() - .1);
			p.setSleep(p.getSleep() - .1);
			p.setEat(p.getEat() - .1);
			
			if(choosen.getDef() < 2) {
				output = "Target's Defense was already at it's minimum but Fear took place.";
				return true;
			}
			
			double modEng = p.getEng() * 10;
			if(modEng < 1) modEng = 1;
			int amountTaken = (int)(Math.round(modEng));
			choosen.setDef(choosen.getDef() - amountTaken);
			p.setEng(p.getEng() - (amountTaken / 10d));
			
			output = "Fear successful: " + p.getName() + "\n" 
					+ "Eat: -.1\n"
					+ "Sleep: -.1\n"
					+ "Work: -.1\n"
					+ "Eng lost: -" + (amountTaken / 10d) + "\n"
					+ choosen.getName() + " Defense -" + amountTaken;
			return true;
		}
		
		output = "You must have at least .1 Eat .1 Sleep and .1 Work to Fear";
		return false;
	}

	@Override
	public String getCommandId() {
		return "fear";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
