package com.game.darquest.data.actions;

public class EfficiencyHandler {
	
	private static int efficiencyScore = 100;
	private static final int maxEfficiencyScore = 100;

	public EfficiencyHandler() {
		// TODO Auto-generated constructor stub
	}

	public static int getEfficiencyScore() {
		return efficiencyScore;
	}

	public static void setEfficiencyScore(int efficiencyScore) {
		if(efficiencyScore < 0) { EfficiencyHandler.efficiencyScore = 0; return; }
		EfficiencyHandler.efficiencyScore = efficiencyScore;
	}

	public static int getMaxefficiencyscore() {
		return maxEfficiencyScore;
	}

}
