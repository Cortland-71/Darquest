package com.game.darquest.data.items;

public class Armor extends Item {

	private int rating;
	public Armor(String name, String description, double price, double weight, int condition, int maxCondition, int rating) {
		super(name, description, price, weight, condition, maxCondition);
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
