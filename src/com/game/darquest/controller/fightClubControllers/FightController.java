package com.game.darquest.controller.fightClubControllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.Player;
import com.game.darquest.data.actions.Exe;
import com.game.darquest.data.actions.Fireable;
import com.game.darquest.data.actions.UtilityCommands;
import com.game.darquest.data.actions.mutationCommands.Acid;
import com.game.darquest.data.actions.mutationCommands.Deception;
import com.game.darquest.data.actions.mutationCommands.Echo;
import com.game.darquest.data.actions.mutationCommands.Fear;
import com.game.darquest.data.actions.mutationCommands.Hack;
import com.game.darquest.data.actions.mutationCommands.Shield;
import com.game.darquest.data.actions.primaryCommands.Attack;
import com.game.darquest.data.actions.primaryCommands.Heal;
import com.game.darquest.data.actions.primaryCommands.Steal;
import com.game.darquest.data.items.Item;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class FightController implements EventHandler<KeyEvent> {
	private List<Fireable> fireList;
	private List<Enemy> enemyList = new ArrayList<>();
	private int numberOfEnemys = 0;
	private FightClubWinController pwc;
	private String output = "";
	private List<String> commandQueue;
	private int maxMovePoints = 1;
	private int currentMovePoints = 0;
	private CommandHandler ch;
	
	private List<String> modifiers = new ArrayList<>();

	private Controller c;
	private Player p;

	public FightController(Controller c) {
		c.getView().getFightClubView().addCommandFieldListener(this);
		this.c = c;
		
		fireList = Arrays.asList(
				new Exe(),  
				new Heal(), 
				new Attack(this.c), 
				new Steal(), 
				new Deception(), 
				new Fear(),
				new Echo(),
				new Shield(),
				new Hack(),
				new Acid());
		
//		new Use(this.c);
		this.pwc = this.c.getPlayerWinController();  
		this.p = (Player)c.getPlayer();
	}

	@Override
	public void handle(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			String command = c.getView().getFightClubView().getCommand().toLowerCase().trim();
			
			ch = new CommandHandler(this);
			UtilityCommands utilityCom = new UtilityCommands(this.c);
			
			if(utilityCom.commandWasClear(command)) return;
			if(utilityCom.commandWasHelp(command)) return;
			if(commandWasExe(command)) return;
			
			if(ch.commandIsValid(command)) {
				if(currentMovePoints <= maxMovePoints) {
					c.getView().getFightClubView().setQueueLabel(currentMovePoints, maxMovePoints);
					c.getView().getFightClubView().addCommandToQueue(command);
					
				} else {
					c.getView().getFightClubView().setPlayerOutputTextArea("Can't add anymore commands.\n\n");
				}
			}
			c.getView().getFightClubView().clearCommandField();
		}
	}
	
	private boolean commandWasExe(String command) {
		if(command.equals("exe")) {
			currentMovePoints = 0;
			if(maxMovePoints < 5)
				maxMovePoints++;
			c.getView().getFightClubView().clearPlayerOutputTextArea();
			
			//check if player?
			commandQueue = c.getView().getFightClubView().getQueueCommands();
			addChainedCommands();
			
			c.getView().getFightClubView().clearQueue();
			if(noCommandsInQueue(commandQueue.size())) return true;
			fireEachCommand(commandQueue);
			afterPlayerExe();
			return true;
		}
		return false;
	}
	
	private void addChainedCommands() {
		int enemyListSize = enemyList.size();
		
		List<Integer> indexesForInsertion = new ArrayList<>();
		List<List<String>> commandsToBeInserted = new ArrayList<>();
		
		for (int i = 0; i < commandQueue.size(); i++) {
			if (commandQueue.get(i).contains("att")) {
				if (ch.hasModifier(commandQueue.get(i))) {
					indexesForInsertion.add(i);
					List<String> currentListToBeAdded = new ArrayList<>();
					Person personBeingAttacked = assignReceivingPerson(commandQueue.get(i));
					int indexOfPerson = enemyList.indexOf(personBeingAttacked);
					for (int j = indexOfPerson+1; j < enemyListSize; j++) {
						currentListToBeAdded.add("att " + (j+1));
					}
					commandsToBeInserted.add(currentListToBeAdded);
				}

			}
		}
		Collections.reverse(commandsToBeInserted);
		Collections.reverse(indexesForInsertion);
		
		for (int i = 0; i < commandsToBeInserted.size(); i++) {
			commandQueue.addAll(indexesForInsertion.get(i)+1, commandsToBeInserted.get(i));
		}
	}
	
	//Enemy will still do this when they have a list of moves to do.\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private Timeline timeline;
	private void fireEachCommand(List<String> commandList) {
		Collections.reverse(commandList);
		c.getView().getFightClubView().clearCommandField();
		c.getView().getFightClubView().clearQueue();
		timeline = new Timeline();
		timeline.setCycleCount(commandList.size());
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), 
				e-> {
					timeline.setCycleCount(timeline.getCycleCount()-1);
					runFire(commandList.get(timeline.getCycleCount()), p);
					c.getHubController().drawAllEnemyBoxes(enemyList);
					c.getPlayerInvStatsController().
					captureSelectedItemsUpdateInvAndStatsReEquipItems();
					}));
		
		timeline.play();
	}
	
	private boolean noCommandsInQueue(int commandQueueSize) {
		if(commandQueueSize <= 0) {
			c.getView().getFightClubView().clearCommandField();
			output = "Cannont excecute.\nNo commands in queue...\n\n";
			setOutput(output, p);
			return true;
		}
		return false;
	}
	
	public void runFire(String command, Person person) {
		Person choosen = person;
		String finalCommand = command;
		
		if (ch.hasModifier(command)) {
			choosen = assignReceivingPerson(command);
			if (choosen == null) {
				output = "ID not recognized.\n\n";
				setOutput(output, person);
				return;
			}
			finalCommand = ch.getCommandWithoutModifiers(command);
		}
		
		for (int i = 0; i < fireList.size(); i++) {
			if (finalCommand.equals(fireList.get(i).getCommandId())) {
				fireList.get(i).fire(person, choosen);
				output = fireList.get(i).getOutput();
				if(!fireList.get(i).getCommandId().equals("att")) setOutput(output, person);
				return;
			}
		} 

		output = "Command was not recognized.\n\n";
		setOutput(output, person);
//		if (p.getHp() <= 0) {
//			c.getView().getWindow().setScene(c.getView().getHubView().getDownTownScene());
//			return;
//		}
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

	public void setOutput(String output, Person person) {
		if (person instanceof Player) {
			c.getView().getFightClubView().setPlayerOutputTextArea(output);
			return;
		}
		c.getView().getFightClubView().setEnemyOutputTextArea(output + "\n");
	}

	private void afterPlayerExe() {
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	pwc.setNumPlayerMoves(pwc.getNumPlayerMoves() + 1);
				removeDeadEnemyFromList();
				if (playerWins())
					return;
				c.getView().getFightClubView().clearCommandField();
				c.getView().getFightClubView().setQueueLabel(currentMovePoints, maxMovePoints);
				// c.getView().getFightClubView().animateWorkBar((Player)p);
				c.getPlayerInvStatsController().updateAllPlayerStats();
				c.getHubController().drawAllEnemyBoxes(enemyList);
				addEnemyId(enemyList);
		    	doEnemyTurnIfPlayerTurnHasEnded();
		    }
		});
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
			p.setDef(p.getDefaultDef());
			p.setStealth(p.getDefaultStealth());
			p.setAwareness(p.getDefaultAwareness());
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

	private void doEnemyTurnIfPlayerTurnHasEnded() {
		c.getView().getFightClubView().clearEnemyOutputTextArea();
		c.getView().getFightClubView().setCommandFieldDisabled(true);
		c.getView().getHubView().setPlayerInventoryTabPaneDisabled(true);

		c.getEnemyController().enemyTurn(enemyList.get(currentEnemyIndex), enemyList);

		currentEnemyIndex++;

		if (currentEnemyIndex >= enemyList.size())
			currentEnemyIndex = 0;
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
	
	public int getCurrentMovePoints() {
		return currentMovePoints;
	}
	
	public int getMaxMovePoints() {
		return maxMovePoints;
	}
	
	public void setMaxMovePoints(int maxMovePoints) {
		this.maxMovePoints = maxMovePoints;
	}
	
	public void setCurrentMovePoints(int currentMovePoints) {
		this.currentMovePoints = currentMovePoints;
	}
	
	public List<String> getModifiers() {
		return this.modifiers;
	}
	
	public void addEnemyId(List<Enemy> list) {
		modifiers.clear();
		modifiers.add("0");
		for (int i = 0; i < list.size(); i++) {
			modifiers.add(Integer.toString(list.get(i).getId()));
		}
	}
	
	public CommandHandler getCommandHandler() {
		return this.ch;
	}
}

