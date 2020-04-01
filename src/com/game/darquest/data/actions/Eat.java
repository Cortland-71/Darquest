package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Player;

public class Eat implements Fireable {
	
	private Controller c;
	private Player p;
	private DecimalFormat f2 = new DecimalFormat("0.00");
	private boolean isValid;
	
	public Eat(Controller c) {
		this.c = c;
		this.p = (Player)this.c.getPlayer();
	}

	@Override
	public String fire() {
		if(p.getEat()+.1 <= p.getMaxEat()) {
			double costToEat = (p.getEat()+.1)*150.5;
			if(p.getCash() >= costToEat) {
				p.setEat(p.getEat()+.1);
				p.setCash(p.getCash()-costToEat);
				
				double hpGained = p.getEat()*.1;
				p.setHp(p.getHp()+hpGained);
				isValid = true;
				return "You ate and feel much better.\n"
						+ "HP gained: +"+f2.format(hpGained)+"\n"
						+ "Cost: "+NumberFormat.getCurrencyInstance().format(costToEat);
			}
			isValid = false;
			return "You can't aford to eat at this point...\n"
					+ "It would cost: "+NumberFormat.getCurrencyInstance().format(costToEat);
		}
		isValid = false;
		p.setEat(p.getMaxEat());
		return "You can't eat anymore or you'll explode...";
	}

	@Override
	public String getCommandId() {
		return "eat";
	}

	@Override
	public boolean isValid() {
		return isValid;
	}
}

