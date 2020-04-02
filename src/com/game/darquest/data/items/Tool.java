package com.game.darquest.data.items;

public class Tool extends Item {

	private int minEffect;
	private int maxEffect;
	private final String catagory;

	public Tool(String name, String description, String catagory, double price, double weight, int condition, int maxCondition,
			int minEffect, int maxEffect) {
		super(name, description, price, weight, condition, maxCondition);
		this.minEffect = minEffect;
		this.maxEffect = maxEffect;
		this.catagory = catagory;
	}

	public int getMinEffect() {
		return minEffect;
	}

	public int getMaxEffect() {
		return maxEffect;
	}

	public String getCatagory() {
		return catagory;
	}
}
