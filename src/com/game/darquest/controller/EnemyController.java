package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.rules.AttackRuleController;
import com.game.darquest.controller.rules.DeceptionRuleController;
import com.game.darquest.controller.rules.EngRuleController;
import com.game.darquest.controller.rules.FearRuleController;
import com.game.darquest.controller.rules.HealRuleController;
import com.game.darquest.controller.rules.Ruleable;
import com.game.darquest.controller.rules.StealRuleController;
import com.game.darquest.controller.rules.TruthRuleController;
import com.game.darquest.controller.rules.ValorRuleController;
import com.game.darquest.data.Enemy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private List<Ruleable> ruleList;
	
	private List<Integer> allScores = new ArrayList<>();
	private int holder = 0;
	
	private DeceptionRuleController deceptionRuleController;
	private StealRuleController stealRuleController;
	private HealRuleController healRuleController;
	private AttackRuleController attackRuleController;
	private TruthRuleController truthRuleController;
	private EngRuleController engRuleController;
	private FearRuleController fearRuleController;
	private ValorRuleController valorRuleController;
	
	public EnemyController(Controller c) {
		this.c = c;
		deceptionRuleController = new DeceptionRuleController(this.c);
		stealRuleController = new StealRuleController(this.c);
		healRuleController = new HealRuleController(this.c);
		attackRuleController = new AttackRuleController(this.c);
		truthRuleController = new TruthRuleController(this.c);
		engRuleController = new EngRuleController(this.c);
		fearRuleController = new FearRuleController(this.c);
		valorRuleController = new ValorRuleController(this.c);
		this.ruleList = Arrays.asList(
				engRuleController,
				attackRuleController, 
				stealRuleController,
				deceptionRuleController,
				fearRuleController,
				healRuleController,
				truthRuleController,
				valorRuleController);
		
	}
	
	public HealRuleController getHealRuleController() {
		return healRuleController;
	}

	public AttackRuleController getAttackRuleController() {
		return attackRuleController;
	}

	public DeceptionRuleController getDeceptionRuleController() {
		return deceptionRuleController;
	}
	
	public StealRuleController getStealRuleController() {
		return stealRuleController;
	}
	
	public TruthRuleController getTruthRuleController() {
		return truthRuleController;
	}

	public FearRuleController getFearRuleController() {
		return fearRuleController;
	}

	public ValorRuleController getValorRuleController() {
		return valorRuleController;
	}





	int count = 0;
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(600), 
						ae-> move()),
				new KeyFrame(Duration.millis(600*2), 
						ae-> move()),
				new KeyFrame(Duration.millis(600*3), 
						ae-> move()),
				new KeyFrame(Duration.millis(600*3), 
						ae-> enemyTurnEnd())
				);
			timeline.playFromStart();
	}
	

	private void move() {
		System.out.println(enemy.getType().getName());
		allScores = enemy.getType().getAllScores();
		int index = getChoosenActionIndex();
		ruleList.get(index).getRule();
		updateAllStats();
	}
	
	
	private int getChoosenActionIndex() {
		List<Integer> highScoreIndexes = new ArrayList<>();
		int choosenIndex = 0;
		holder = 0;
		Random rand = new Random();
		
		//Finds Highest score in the list of all scores.
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) > holder) {
				holder = allScores.get(i);
				choosenIndex = i;
			}
		}
		
		//Adds the index of duplicates of the highest score found to a list of highScoreIndexes.
		for (int i = 0; i < allScores.size(); i++) {
			if(allScores.get(i) == holder) {
				highScoreIndexes.add(i);
			}
		}
		
		highScoreIndexes.forEach(e->System.out.println("Enemy Controller: High Score index: " + e));
		
		//If it finds there are two indexes with same high scores it will choose one randomly.
		if(highScoreIndexes.size() > 1) 
			choosenIndex = highScoreIndexes.get(rand.nextInt(highScoreIndexes.size()));
		
		System.out.println("Choosen index: " + choosenIndex + "\n");
		return choosenIndex;
	}
	
	private void updateAllStats() {
		List<Enemy> list = c.getFightClubController().getEnemyList();
		c.getView().getFightClubView().getCenterEnemyBox().getChildren().clear();
		c.getDownTownController().drawAllEnemyBoxes(list);
		c.getPlayerInvStatsController().updateAllPlayerStats();
	}
	
	private void enemyTurnEnd() {
		count = 0;
		c.getView().getFightClubView().setDisableCommandField(false);
		c.getView().getFightClubView().setCommandFeildFocused();
		c.getPlayer().setMoves(c.getPlayer().getMaxMoves());
		c.getView().getFightClubView().setPlayerMovesLeft(c.getPlayer());
	}

	public Enemy getEnemy() {
		return enemy;
	}
	public List<Enemy> getEnemyList() {
		return enemyList;
	}
	
	public void rulesForEat() {
		double cashRequired = (getEnemy().getEat() + .1) * 150.5;
		if(getEnemy().getCash() >= cashRequired) {
			c.getFightClubController().runFire("eat", getEnemy());
			return;
		}
		rulesForWork();
	}
	
	public void rulesForSleep() {
		double workRequired = .1;
		if(getEnemy().getWork() >= workRequired) {
			c.getFightClubController().runFire("zz", getEnemy());
			return;
		}
		rulesForWork();
	}
	
	public void rulesForWork() {
		if(getEnemy().getWork() < 1) {
			c.getFightClubController().runFire("wq", getEnemy());
			return;
		}
		rulesForSleep();
			
	}
}
