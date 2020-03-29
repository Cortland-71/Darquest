package com.game.darquest.data.actions;

import java.text.NumberFormat;

import com.game.darquest.controller.Controller;
import com.game.darquest.controller.Fireable;
import com.game.darquest.data.Player;

public class Sleep implements Fireable {
	
	private Controller c;
	private Player p;
	
	public Sleep(Controller c) {
		this.c = c;
		this.p = (Player)this.c.getPlayer();
	}
	@Override
	public String fire() {

		if(p.getSleep()+.1 <= 1) {
			if(p.getWork() > 0 && p.getEat() > 0) {
				p.setSleep(p.getSleep()+.1);
				p.setWork(p.getWork()-.1);
				p.setEat(p.getEat()-.1);
				
				double gainedEng = p.getSleep()/4;
				p.setEng(p.getEng()+gainedEng);
				
				return "You feel rested.\nYou gained Eng: +"+gainedEng+
						"\nEat lost: -"+.1+
						"\nWork lost: -"+.1;
			}
			return "You can't sleep right now.\n"
			+ "You must have at least .1 work and .1 eat...";
		}
		p.setSleep(1);
		return "You have slept to your max capacity.";
	}

	@Override
	public String getCommandId() {
		return "sleep";
	}
}