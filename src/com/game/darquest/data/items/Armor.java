package com.game.darquest.data.items;

public class Armor extends Accessory {

	private int rating;
	public Armor(String name, double price, double weight, int condition, int rating) {
		super(name, price, weight, condition);
		this.setRating(rating);
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return super.toString() + "\nRating:\t" + rating;
	}
	
	
}
