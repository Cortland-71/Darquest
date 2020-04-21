package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.EnemyGenerator;
import com.game.darquest.data.enemyType.Classable;
import com.game.darquest.data.enemyType.Enforcer;
import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Weapon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ChallengeController implements EventHandler<ActionEvent> {
	
	private List<Challengable> actionList;

	private Controller c;
	public ChallengeController(Controller c) {
		this.c = c;
		this.c.getView().getFightClubHub().getChallengesSelectView().addChallengeListener(this);
		actionList = new ArrayList<>();
		actionList.add(new Challenge1(this.c));
		
	}
	@Override
	public void handle(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		List<Enemy> list = actionList.get(Integer.parseInt(id)).getEnemyList();
		fireChallenge(list);
	}
	
	private void fireChallenge(List<Enemy> enemyList) {
		c.getPlayerWinController().setAllCountersToDefault();
		c.getFightClubController().setEnemyList(enemyList);
		
		c.getHubController().drawAllEnemyBoxes(enemyList);
		
		c.getPlayer().setMoves(3);
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
		c.getView().getFightClubView().clearAllOutputTextAreas();
		c.getView().getFightClubView().clearCommandField();
		c.getView().getHubView().showRandomFight();
		c.getView().getFightClubView().setCommandFeildFocused();
	}
}

class Challenge1 implements Challengable {
	private Controller c;
	public Challenge1(Controller c) {
		this.c = c;
	}

	@Override
	public List<Enemy> getEnemyList() {
		List<Enemy> enemyList = new ArrayList<>();
		Classable type = new Enforcer();
		type.setController(this.c);
		type.setLevel(1);
		enemyList.add(new Enemy("Zero", 5,5,5,
				(Weapon)c.getItemHub().getAllWeapons().get(0),
				(Armor)c.getItemHub().getAllArmors().get(0),
				1, 100, type, 1, "Enforcer1Big.png"));
		return enemyList;
	}
}




interface Challengable {
	List<Enemy> getEnemyList();
}