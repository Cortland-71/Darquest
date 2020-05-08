package com.game.darquest.data.actions;

import com.game.darquest.controller.Controller;

public class UtilityCommands {

	private Controller c;
	
	public UtilityCommands(Controller c) {
		this.c = c;
	}
	
	private String output = "Commands fall under 3 categories.\n"
			+ "Mutation, Hostile and Utility\n\n"
			+ "-----------------------------------------------\n"
			+ "Mutation:\n"
			+ "All Mutation commands are governed by your\n"
			+ "Mutation level. The higher your Mutation level\n"
			+ "the more effect it will have. All Mutation\n"
			+ "commands can take the ID of the choosen person\n"
			+ "you want to effect (ex: dec 1) this command\n"
			+ "would perform Deception on the person with\n"
			+ "ID 1. If you don't specify an ID you will\n"
			+ "effect yourself.\n\n"
			+ "sld - Shield lowers Attack / raises Defense.\n"
			+ "Point cost: 1\n\n"
			+ "fear - Fear lowers Defense / raises Attack.\n"
			+ "Point cost: 1\n\n"
			+ "ech - Echo lowers Stealth / raises Awareness.\n"
			+ "Point cost: 1\n\n"
			+ "dec - Deception lowers Awareness / raises Stealth\n"
			+ "Point cost: 1\n\n"
			+ "acd - Acid lowers Mutation by 1 level.\n"
			+ "Point cost: 1\n\n"
			+ "hack - Hack raises Mutation by 1 level.\n"
			+ "Point cost: 1\n\n"
			+ "hea - Heal raises HP.\n"
			+ "Point cost: 3\n"
			+ "Cash cost:  $50\n\n"
			+ "-----------------------------------------------\n"
			+ "Hostile:\n"
			+ "Much like the Mutation commands, hostile\n"
			+ "commands take an ID after the command. However\n"
			+ "what is special about hostile commands is that\n"
			+ "they can be chained to effect more than one\n"
			+ "person. For example if there are three people\n"
			+ "on the board and you Attack the first person\n"
			+ "on the left who's ID is 1 (att 1) The attack\n"
			+ "will hit that person as well as anyone to the\n"
			+ "right of them.\n\n"
			+ "att - Attack lowers targets HP.\n"
			+ "Counter attribute: Defense\n"
			+ "Point cost: 3\n\n"
			+ "stea - Steal takes money from your target and\n"
			+ "adds it to your Cash\n"
			+ "Counter attribute: Awareness\n"
			+ "Point cost: 3\n\n"
			+ "-----------------------------------------------\n"
			+ "Utility:\n"
			+ "exe - Execute the queue of commands.\n"
			+ "Point cost: 0\n\n"
			+ "use - Use command allows you to use the\n"
			+ "Tool you have equipped\n"
			+ "Point cost: 2\n\n"
			+ "help - Help commands shows this page.\n"
			+ "Point cost: 0";

	
	
	public boolean commandWasClear(String command) {
		if(command.equals("clear")) {
			c.getFightClubController().setCurrentMovePoints(0);
			c.getView().getFightClubView().setQueueLabel(
					c.getFightClubController().getCurrentMovePoints(), c.getFightClubController().getMaxMovePoints());
			c.getView().getFightClubView().clearQueue();
			c.getView().getFightClubView().clearCommandField();
			c.getView().getFightClubView().setCommandFeildFocused();
			c.getView().getFightClubView().clearPlayerOutputTextArea();
			return true;
		}
		return false;
	}
	
	public boolean commandWasHelp(String command) {
		if(command.equals("help")) {
			c.getView().getFightClubView().clearPlayerOutputTextArea();
			c.getView().getFightClubView().clearCommandField();
			c.getView().getFightClubView().setCommandFeildFocused();
			c.getView().getFightClubView().setPlayerOutputTextArea(output);
			return true;
		}
		return false;
	}
}
