package com.game.darquest.controller.rules;

import com.game.darquest.controller.Controller;

public class EngRuleController implements Ruleable {

	private Controller c;
	public EngRuleController(Controller c) {
		this.c = c;
	}
	@Override
	public void getRule() {
		System.out.println("ENG Rule controller");
	}

}
