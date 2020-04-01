package com.game.darquest.data.actions;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Player;

public class Sleep implements Fireable {
	
	private Controller c;
	private Player p;
	private boolean isValid;
	
	public Sleep(Controller c) {
		this.c = c;
		this.p = (Player)this.c.getPlayer();
	}
	@Override
	public String fire() {

		if(p.getSleep()+.1 <= p.getMaxSleep()) {
			if(p.getWork() > p.getMinWork() && p.getEat() > p.getMinEat()) {
				p.setSleep(p.getSleep()+.1);
				p.setWork(p.getWork()-.1);
				p.setEat(p.getEat()-.1);
				
				double gainedEng = p.getSleep()/4;
				p.setEng(p.getEng()+gainedEng);
				isValid = true;
				return "You feel rested.\nYou gained Eng: +"+gainedEng+
						"\nEat lost: -"+.1+
						"\nWork lost: -"+.1;
			}
			isValid = false;
			return "You can't sleep right now.\n"
			+ "You must have at least .1 work and .1 eat...";
		}
		p.setSleep(p.getMaxSleep());
		return "You have slept to your max capacity.";
	}

	@Override
	public String getCommandId() {
		return "sleep";
	}
	@Override
	public boolean isValid() {
		return isValid;
	}
}