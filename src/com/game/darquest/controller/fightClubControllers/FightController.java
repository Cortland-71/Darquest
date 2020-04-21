package com.game.darquest.controller.fightClubControllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.actions.Help;
import com.game.darquest.data.actions.Use;
import com.game.darquest.data.actions.defensive.Heal;
import com.game.darquest.data.actions.defensive.Shadow;
import com.game.darquest.data.actions.defensive.Truth;
import com.game.darquest.data.actions.defensive.Valor;
import com.game.darquest.data.actions.essential.Eat;
import com.game.darquest.data.actions.essential.Sleep;
import com.game.darquest.data.actions.essential.Work;
import com.game.darquest.data.actions.hostile.Attack;
import com.game.darquest.data.actions.hostile.Deception;
import com.game.darquest.data.actions.hostile.Fear;
import com.game.darquest.data.actions.hostile.Light;
import com.game.darquest.data.actions.hostile.Steal;
import com.game.darquest.data.items.Item;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class FightController implements EventHandler<KeyEvent> {


	private List<Fireable> fireList;
	private List<Enemy> enemyList = new ArrayList<>();
	private int numberOfEnemys = 0;
	private FightClubWinController pwc;

	private Controller c;
	private Player p;

	public FightController(Controller c) {
		c.getView().getFightClubView().addCommandFieldListener(this);
		this.c = c;
		fireList = Arrays.asList(new Help(), new Eat(), new Sleep(), new Work(), new Attack(this.c), 
				new Use(this.c),
				new Steal(), new Heal(), new Deception(), new Truth(), new Fear(), new Valor(), new Light(),
				new Shadow());
		this.pwc = this.c.getPlayerWinController();  
		this.p = (Player)c.getPlayer();
	}

	@Override
	public void handle(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			String command = c.getView().getFightClubView().getCommand();
			runFire(command.trim(), p);

		}
	}

	public void runFire(String command, Person person) {
		Person choosen = person;
		String finalCommand = command;
		if (hasModifier(command)) {
			choosen = assignReceivingPerson(command);
			if (choosen == null)
				return;
			finalCommand = getCommandWithoutModifiers(command);
		}

		for (int i = 0; i < fireList.size(); i++) {
			if (finalCommand.toLowerCase().equals(fireList.get(i).getCommandId())) {
				boolean valid = fireList.get(i).fire(person, choosen);
				String output = fireList.get(i).getOutput();
				if (valid) {
					if (person instanceof Player) {
						afterPlayerMove();
					}
				}
				setOutput(output, person);
			}
		}

		if (p.getHp() <= 0) {
			c.getView().getWindow().setScene(c.getView().getHubView().getDownTownScene());
			return;
		}
	}

	private String[] modifiers = { "0", "1", "2", "3" };

	private String getCommandWithoutModifiers(String command) {
		String mod = command.substring(command.length() - 2, command.length());
		int indexAtMod = command.indexOf(mod);
		return command.substring(0, indexAtMod);
	}

	private boolean hasModifier(String command) {
		for (String e : modifiers)
			if (command.contains(e))
				return true;
		return false;
	}

	private Person assignReceivingPerson(String command) {

		String mod = "";
		if (command.contains("0"))
			return p;
		for (String m : modifiers) {
			if (command.contains(m))
				mod = m;
		}

		for (Enemy e : enemyList) {
			if (e.getId() == Integer.parseInt(mod))
				return e;
		}
		return null;
	}

	private void setOutput(String output, Person person) {
		if (person instanceof Player) {
			c.getView().getFightClubView().setPlayerOutputTextArea(output);
			return;
		}
		c.getView().getFightClubView().setEnemyOutputTextArea(output + "\n");
	}

	private void afterPlayerMove() {
		pwc.setNumPlayerMoves(pwc.getNumPlayerMoves() + 1);
		removeDeadEnemyFromList();
		if (playerWins())
			return;
		c.getView().getFightClubView().clearCommandField();
		p.setMoves(p.getMoves() - 1);
		c.getView().getFightClubView().setPlayerMovesLeft(p);
		// c.getView().getFightClubView().animateWorkBar((Player)p);
		c.getPlayerInvStatsController().updateAllPlayerStats();
		c.getHubController().drawAllEnemyBoxes(enemyList);
		doEnemyTurnIfPlayerTurnHasEnded();
	}

	private boolean playerWins() {
		if (enemyList.size() <= 0) {
			
			p.setKills(p.getKills() + numberOfEnemys); 
			
			for (int i = 0; i < p.getChallengeBools().size(); i++) {
				if(p.getChallengeBools().get(i) == false) {
					c.getChallengeController().runChallengeTest(i);
				}
			}
			
			p.getChallengeBools().forEach(System.out::println);
			
			String loot = "\n" + addGeneratedLootToList();
			String totalMoves = pwc.getNumPlayerMoves().toString();
			String formattedXp = pwc.getXpEarned();
			String efficiencyScore = "%" + FightClubWinController.getEfficiencyScore();
			String cash = pwc.getCashEarnedFormatted();
			String rating = pwc.getRating();
			String bonusCash = pwc.getBonusCashEarnedFormatted();
			String totalCashEarned = pwc.getTotalCashEarnedFormatted();
			fade();

			List<String> listOfWinStats = Arrays.asList(totalMoves, formattedXp, efficiencyScore, 
					cash, bonusCash,totalCashEarned, loot, rating);
			c.getView().getFightWinView().setWinStats(listOfWinStats);
			p.setDef(p.getMaxDef());
			p.setStealth(p.getMaxStealth());
			p.setAwareness(p.getMaxAwareness());
			c.getPlayerInvStatsController().updateAllPlayerStats();
			c.getView().getHubView().showWin();
			return true;
		}
		return false;
	}

	

	private void fade() {
		FadeTransition ft = new FadeTransition(Duration.millis(500), c.getView().getFightWinView().getFightWinCenter());
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	private String addGeneratedLootToList() {
		pwc.getLootList().clear();
		List<List<List<Item>>> allItems = Arrays.asList(c.getItemHub().getListsOfWeapons(),
				c.getItemHub().getListsOfArmors(), c.getItemHub().getListsOfTools());
		for (int i = 0; i < allItems.size(); i++) {
			pwc.getLootList().add(getRandomItem(allItems.get(i)));
		}
		StringBuilder sb = new StringBuilder();
		pwc.getLootList().forEach(e -> sb.append("* " + e.getName() + "\n"));
		return sb.toString();
	}

	private void removeDeadEnemyFromList() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getHp() <= 0) {
				enemyList.remove(i);
				currentEnemyIndex = 0;
				pwc.addXpToPlayer(((Player) p).getXpAddedBeatEnemy());
			}
		}
	}

	private int currentEnemyIndex = 0;
	int deadEnemyIndex = -1;

	private void doEnemyTurnIfPlayerTurnHasEnded() {

		if (p.getMoves() < 1) {
			c.getView().getFightClubView().clearEnemyOutputTextArea();
			c.getView().getFightClubView().setDisableCommandField(true);

			c.getEnemyController().enemyTurn(enemyList.get(currentEnemyIndex), enemyList);

			currentEnemyIndex++;

			if (currentEnemyIndex >= enemyList.size())
				currentEnemyIndex = 0;
		}
	}

	private Item getRandomItem(List<List<Item>> listOfLists) {
		Random rand = new Random();
		int index = rand.nextInt(listOfLists.size());
		int itemIndex = rand.nextInt(listOfLists.get(index).size());
		return listOfLists.get(index).get(itemIndex);
	}

	public void setEnemyList(List<Enemy> enemyList) {
		pwc.setCashEarned(enemyList.size() * 1000d);
		numberOfEnemys = enemyList.size();
		this.enemyList = enemyList;
	}
	
	public int getNumberOfEnemysFoughtAtOnce() {
		return numberOfEnemys;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public List<Fireable> getFireList() {
		return fireList;
	}

}
