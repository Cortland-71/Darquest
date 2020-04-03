package com.game.darquest.data.items;

import java.text.NumberFormat;

public abstract class Item {
	
	private String name;
	private String description;
	private double price;
	private double value;
	private double weight;
	private int maxCondition;
	private int condition;
	
	public Item(String name, String description, double price, double weight, int condition, int maxCondition) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.value = price;
		this.weight = weight;
		this.setCondition(condition);
		this.maxCondition = maxCondition;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value - (((10-condition)/10d) * value);
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
	public String getPriceFormatted() {
		return NumberFormat.getCurrencyInstance().format(this.price);
	}
	
	public String getValueFormatted() {
		return NumberFormat.getCurrencyInstance().format(this.value);
	}
	
	@Override
	public String toString() {
		return name + "\nPrice:\t" + getPriceFormatted() + "\nValue:\t" + getValueFormatted() + 
				"\nWeight:\t" + weight +
				"\nCond:\t" + condition + "/" + maxCondition;
	}
	
	public String getToStringForPlayerInventory() {
		return name + "\nValue:\t" + getValueFormatted() + 
				"\nWeight:\t" + weight + 
				"\nCond:\t" + condition + "/" + maxCondition;
	}

	public int getMaxCondition() {
		return maxCondition;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}
}
