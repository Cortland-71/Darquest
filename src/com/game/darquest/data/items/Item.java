package com.game.darquest.data.items;

import java.text.NumberFormat;

public abstract class Item {
	
	private String name;
	private double price;
	private double value;
	private double weight;
	
	public Item(String name, double price, double weight) {
		this.name = name;
		this.price = price;
		this.weight = weight;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
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
		return name + "\nPrice: " + getPriceFormatted() + "\nValue: " + getValueFormatted() + 
				"\nWeight:" + weight;
	}
}
