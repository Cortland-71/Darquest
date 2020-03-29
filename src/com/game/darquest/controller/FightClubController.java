package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FightClubController implements EventHandler<KeyEvent> {

	private List<Fireable> fireList;
	private Controller c;
	public FightClubController(Controller c) {
		fireList = Arrays.asList(new Eat(), new Sleep(), new Work());
		c.getView().getFightClubView().addCommandFieldListener(this);
		this.c = c;
	}
	
	@Override
	public void handle(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			String command = c.getView().getFightClubView().getCommand();
			for (int i = 0; i < fireList.size(); i++) {
				if(command.toLowerCase().equals(fireList.get(i).getCommandId())) {
					fireList.get(i).fire();
				}
			}
		}
	}

}

class Eat implements Fireable {

	@Override
	public void fire() {
		System.out.println("Eating");
	}

	@Override
	public String getCommandId() {
		return "eat";
	}
}

class Sleep implements Fireable {

	@Override
	public void fire() {
		System.out.println("Sleeping");
	}

	@Override
	public String getCommandId() {
		return "sleep";
	}
}

class Work implements Fireable {

	@Override
	public void fire() {
		System.out.println("Working");
	}

	@Override
	public String getCommandId() {
		return "work";
	}
}