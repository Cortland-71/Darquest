package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class AppartmentController implements EventHandler<ActionEvent> {
	private List<Clickable> appartmentActions;
	private Search search;
	private Email email;
	private Music music;
	private Stats stats;
	private Rest rest;
	private Leave leave;

	private Controller c;
	public AppartmentController(Controller c) {
		this.c = c;
		this.search = new Search(this.c);
		this.email = new Email(this.c);
		this.music = new Music(this.c);
		this.stats = new Stats(this.c);
		this.rest = new Rest(this.c);
		this.leave = new Leave(this.c);
		appartmentActions = Arrays.asList(search, email, music, stats, rest, leave);
		this.c.getView().getAppartmentView().addActionListener(this);
	}

	@Override
	public void handle(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		appartmentActions.get(eventIndex).clickAction();
	}
}

class Search implements Clickable {
	private Controller c;
	public Search(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Search");
	}
}

class Email implements Clickable {
	private Controller c;
	public Email(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Email");
	}
}

class Music implements Clickable {
	private Controller c;
	public Music(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Music");
	}
}

class Stats implements Clickable {
	private Controller c;
	public Stats(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("Stats");
	}
}

class Rest implements Clickable {
	private Controller c;
	public Rest(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		Player p = (Player)c.getPlayer();
		p.setHp(1);
		p.setEat(.5);
		p.setSleep(.5);
		p.setWork(.5);
		p.setDef(p.getMaxDef());
		p.setStealth(p.getMaxStealth());
		p.setAwareness(p.getMaxAwareness());
		c.getPlayerInvStatsController().updateAllPlayerStats();
	}
}

class Leave implements Clickable {
	private Controller c;
	public Leave(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		System.out.println("leave");
		c.getView().getHubView().showHub();
	}
}