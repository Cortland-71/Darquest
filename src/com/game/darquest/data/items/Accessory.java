package com.game.darquest.data.items;

public abstract class Accessory extends Item {

	private final int MAX_CONDITION = 10;
	private int condition;
	public Accessory(String name, double price, double weight, int condition) {
		super(name, price, weight);
		this.condition = condition;
		setValue(getDamagedPrice(condition, price));
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getMaxCondition() {
		return MAX_CONDITION;
	}
	
	private double getDamagedPrice(int condition, double price) {
		return price - (((10-condition)/10d) * price);
	}
	@Override
	public String toString() {
		return super.toString() + "\nCondition: " + condition + "/" + MAX_CONDITION;
	}
}
