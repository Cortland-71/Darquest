package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.rules.AttackRuleController;
import com.game.darquest.controller.rules.DeceptionRuleController;
import com.game.darquest.controller.rules.FearRuleController;
import com.game.darquest.controller.rules.HealRuleController;
import com.game.darquest.controller.rules.EchoRuleController;
import com.game.darquest.controller.rules.Ruleable;
import com.game.darquest.controller.rules.StealRuleController;
import com.game.darquest.data.Enemy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class EnemyController {
	
	private Controller c;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private List<Ruleable> ruleList;
	private int points = 0;
	
	private DeceptionRuleController deceptionRuleController;
	private StealRuleController stealRuleController;
	private HealRuleController healRuleController;
	private AttackRuleController attackRuleController;
	private FearRuleController fearRuleController;
	private EchoRuleController echoRuleController;
	
	public EnemyController(Controller c) {
		this.c = c;
		deceptionRuleController = new DeceptionRuleController(this.c);
		stealRuleController = new StealRuleController(this.c);
		healRuleController = new HealRuleController(this.c);
		attackRuleController = new AttackRuleController(this.c);
		fearRuleController = new FearRuleController(this.c);
		echoRuleController = new EchoRuleController(this.c);
		
		this.ruleList = Arrays.asList(
				attackRuleController, 
				stealRuleController,
				healRuleController,
				deceptionRuleController,
				fearRuleController,
				echoRuleController);
	}

	int count = 0;
	
	public void enemyTurn(Enemy enemy, List<Enemy> enemyList) {
		this.enemy = enemy;
		this.enemyList = enemyList;
		Timeline timeline = new Timeline();
		
		if(points < 5) points++;
		timeline.setCycleCount(points);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), 
				e-> {
					System.out.println("Test");
					}));
		
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	enemyTurnEnd();
		    	System.out.println();
		    }
		});
	}
	

	private void move() {
		System.out.println(enemy.getType().getName());
		List<Integer> allScores = enemy.getType().getAllScores();
		int index = getChoosenActionIndex(allScores);
		ruleList.get(index).getRule();
		updateAllStats();
		if(c.getPlayer().getHp() <= 0) {
			c.getView().getHubView().showHub();
		}
	}
	
	
	private int getChoosenActionIndex(List<Integer> allScores) {
		List<Integer> highScoreIndexes = new ArrayList<>();
		int choosenIndex = 0;
		int holder = 0;
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
		
		//If it finds there are two indexes with same high scores it will choose one randomly.
		if(highScoreIndexes.size() > 1) 
			choosenIndex = highScoreIndexes.get(rand.nextInt(highScoreIndexes.size()));
		return choosenIndex;
	}
	
	private void updateAllStats() {
		List<Enemy> list = c.getFightClubController().getEnemyList();
		c.getView().getFightClubView().getCenterEnemyBox().getChildren().clear();
		c.getHubController().drawAllEnemyBoxes(list);
		c.getPlayerInvStatsController().updateAllPlayerStats();
	}
	
	private void enemyTurnEnd() {
		count = 0;
		c.getView().getFightClubView().setCommandFieldDisabled(false);
		c.getView().getFightClubView().setCommandFeildFocused();
		c.getView().getHubView().setPlayerInventoryTabPaneDisabled(false);
		updateAllStats();
	}

	public Enemy getEnemy() {
		return enemy;
	}
	public List<Enemy> getEnemyList() {
		return enemyList;
	}
}
