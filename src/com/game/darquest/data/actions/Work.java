package com.game.darquest.data.actions;

import java.text.NumberFormat;

import com.game.darquest.controller.Controller;
import com.game.darquest.controller.Fireable;
import com.game.darquest.data.Player;

public class Work implements Fireable {
	
	private Controller c;
	private Player p;
	private final double basePay = 8.5;
	
	public Work(Controller c) {
		this.c = c;
		this.p = (Player)this.c.getPlayer();
	}
	
	@Override
	public String fire() {
		if(p.getWork()+.1 <= p.getMaxWork()) {
			
			p.setWork(p.getWork()+.1);
			double finalPay = (p.getWork()*10) * basePay;
			double lostEng = 0;
			if(p.getEng() > p.getMinEng()) lostEng = p.getWork()/4;
			
			p.setCash(p.getCash()+finalPay);
			p.setEng(p.getEng()-lostEng);
			
			return "Your hard work is paying off.\nYou gained: +"+NumberFormat.getCurrencyInstance().format(finalPay) +
					"\nEng lost: "+lostEng;
		}
		p.setWork(p.getMaxWork());
		return "You cannot work anymore...\n"
				+ "Try sleeping to recover.";
	}

	@Override
	public String getCommandId() {
		return "work";
	}
}
