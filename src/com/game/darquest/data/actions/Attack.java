package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

public class Attack implements Fireable {

	private Controller c;
	private String output;
	private String id = "0";
	private DecimalFormat f2 = new DecimalFormat("0.00");
	public Attack(Controller c) {
		this.c = c;
	}
	
	@Override
	public void setChoosenID(String id) {
		this.id = id;
	}

	@Override
	public boolean fire(Person p) {
		if(p.getEat() >= .1 && p.getSleep() >= .1 && p.getWork() >= .1) {
			
			int def = 0;
			double finalDamage = 0;
			double damageAmount = p.getRandomDamageAmount();
			
			if(p instanceof Player) {
				List<Enemy> enemyList = c.getFightClubController().getEnemyList();
				
				for (int i = 0; i < enemyList.size(); i++) {
					def = enemyList.get(i).getDef();
					finalDamage = damageAmount - ((def/2d)/100d);
					enemyList.get(i).setHp(enemyList.get(i).getHp() - finalDamage);
				}
			} else {
				def = c.getPlayer().getDef();
				finalDamage = damageAmount - ((def/2d)/100d);
				c.getPlayer().setHp(c.getPlayer().getHp() - finalDamage);
			}
			
			output = "Attack landed successfully!"
					+ "\nInit Damage: "+f2.format(damageAmount)
					+"\nEnemy Def: "+def
					+"\nFinal Damage: "+ f2.format(finalDamage);
			return true;
			
		}
		output = "You failed to attack. You must have .1 Eat, .1 Sleep and .1 Work...";
		return false;
	}

	@Override
	public String getCommandId() {
		return "attack";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
