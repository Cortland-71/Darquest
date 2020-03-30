package com.game.darquest.data;

public class Enemy extends Person {
	
	private String type;

	public Enemy(String name, int def, int stealth, int awareness, String type) {
		super(name, def, stealth, awareness);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return super.toString() + "Enemy [type=" + type + "]";
	}
}
