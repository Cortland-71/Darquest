package com.game.darquest.data.items;

public class Tool extends Item {

	private int minEffect;
	private int maxEffect;

	public Tool(String name, String description, double price, double weight, int condition, int maxCondition,
			int minEffect, int maxEffect) {
		super(name, description, price, weight, condition, maxCondition);
		this.minEffect = minEffect;
		this.maxEffect = maxEffect;
	}
}
