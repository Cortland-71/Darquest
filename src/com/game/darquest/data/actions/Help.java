package com.game.darquest.data.actions;

import com.game.darquest.data.Person;

public class Help implements Fireable {
	
	private String output;

	public Help() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Person p, Person choosen) {
		output = "Commands fall under 3 categories.\n"
				+ "Essential, Hostile and Defensive\n\n"
				
				+ "Essential:\n"
				+ "eat - Eat [eat] raises the eat bar by .1 and\n"
				+ "Cost: $15.5 (multiplied by the amount of eat you currently have).\n\n"
				+ "zz - Sleep [zz] will raise the sleep bar by .1 and the higher your\n"
				+ "sleep, the more Eng you will gain\n"
				+ "Cost: .1 work\n\n"
				+ "wq - Work [wq] raises the work bar by .1 and\n"
				+ "Gain: $8.5 (multiplied by the amount of work you currently have).\n\n"
				
				+ "Hostile:\n"
				+ "att - Attack [att (id)] will deal damage to\n"
				+ "a specific enemy. This command takes a parameter at the end\n"
				+ "in the form of the enemy ID (Example: att 1) will attack the enemy\n"
				+ "with ID 1. BE CAREFULL! If you don't specify an ID at the end of\n"
				+ "this command you will attack yourself. The higher your Eng the more\n"
				+ "damage you will do.\n"
				+ "Cost: .1 Eat\n"
				+ "Cost: .1 Sleep\n"
				+ "Eng lost: .3\n\n"
				+ "st - Steal [st (id)] lets you steal cash from a\n"
				+ "specific enemy. Like the att command, this takes an ID at the end.\n"
				+ "(Example: st 1) will steal cash from enemy with ID 1. The higher your\n"
				+ "Eng the more you can steal.\n"
				+ "Cost: .1 Work\n"
				+ "Eng lost: .2\n\n"
				+ "dec - Deception [dec (id)] lets you lower the Awareness of\n"
				+ "a specific enemy. Lowering their Awareness below or equal to your Stealth\n"
				+ "level allows you to steal from them. The lower their Awareness is to your\n"
				+ "Stealth the more you can steal.\n"
				+ "Cost: .1 Eat\n"
				+ "Cost: .1 Sleep\n"
				+ "Cost: .1 Work\n\n"
				+ "tt - Light [tt (id)] lowers the Stealth of a specific enemy.";
	}

	@Override
	public String getCommandId() {
		return "help";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
