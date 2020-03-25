package com.game.darquest.data;

public class TutorialData {

	public static String getTutorialData() {
		return getTheBasics() 
				+ "\n\n" + getHelpTutorial() 
				+ "\n\n" + getEatTutorial()
				+ "\n\n" + getSleepTutorial()
				+ "\n\n" + getWorkTutorial()
				+ "\n\n" + getAttackTutorial()
				+ "\n\n" + getStealTutorial()
				+ "\n\n" + getHealTutorial()
				+ "\n\n" + getKillTutorial();
	}

	private static String getTheBasics() {
		return "The Basics:\n" + "Okay, so the idea is simple, don't die and take out anyone who "
				+ "stands in "
				+ "your way. You might be faced with 1 or up to 5 enemys at once so be aware and "
				+ "well prepaired. The first thing to pay attention to is your \"Essentials\" "
				+ "this "
				+ "includes the three bars indicating [Eat] [Sleep] and [Work] along with the "
				+ "amount "
				+ "of [Cash] you have. These four things will help you determine your possible "
				+ "next "
				+ "moves. You have 3 moves during your turn. Your [Moves LEFT] counter will show "
				+ "you how many moves you have left. Once you complete all 3 moves it will be "
				+ "the other person's turn who will also have 3 moves. Your [HP] bar determines "
				+ "your "
				+ "health and your [ENG] bar determines your energy level. Keeping this as full "
				+ "as "
				+ "possible will allow you to do more damage and take heavier hits. Once it's "
				+ "full "
				+ "you can perform the [Kill] command (Read about this below). The basic loop "
				+ "is this: "
				+ "You must [Work] to gain [Cash], you must have [Cash] in order to [Eat] and "
				+ "to gain "
				+ "[ENG] you must [Sleep]. Having raised the \"Essentials\" meters will allow "
				+ "you "
				+ "perform a command.";
	}

	private static String getHelpTutorial() {
		return "Command: help\n" + "This command can be executed anytime durring your turn "
				+ "and will not use up a turn. "
				+ "It will list all the basic "
				+ "commands availble in Darquest. Some commands won't be available deppending on "
				+ "your current situation however so be aware of your stats and availible "
				+ "abilities. If you execute a command without thinking, you may waist a turn, "
				+ "so be careful. \n\n****";
	}

	private static String getEatTutorial() {
		return "Command: eat\n" 
				+ "Eating is a basic nessesity, in order "
				+ "to eat you must have money. You can obtain money in a few ways, but the easiest "
				+ "way is by working. Filling up your [Eat] bar will level up your eating ability "
				+ "indicated by your [Eat lvl]. Having a higher [Eat lvl] will allow you to eat "
				+ "more but will cost more each time. Your [Eat] bar will also be extended by a "
				+ "certain amount making it harder to level up your [Eat lvl]. You must have at "
				+ "least 1 [Sleep] and [Cash] in order to [Eat]. \n\n****";
	}
	
	private static String getSleepTutorial() {
		return "Command: sleep\n" 
				+ "Sleeping will allow you to raise your [ENG]. [ENG] is the bar under your [HP] "
				+ "that allows you to deal more damage when attacking and take heavier hits."
				+ "Sleeping will lower your [Eat] bar as well as your [Work] bar. You must have"
				+ "at least 1 [Eat] and 1 [Work] in order to [Sleep].\n\n****";
	}
	
	private static String getWorkTutorial() {
		return "Command: work\n"
				+ "\n\n****";
	}
	
	private static String getAttackTutorial() {
		return "Command: attack\n"
				+ "\n\n****";
	}
	
	private static String getStealTutorial() {
		return "Command: steal\n"
				+ "\n\n****";
	}
	
	private static String getHealTutorial() {
		return "Command: heal\n"
				+ "\n\n****";
	}
	
	private static String getKillTutorial() {
		return "Command: kill\n"
				+ "\n\n****";
	}
}
