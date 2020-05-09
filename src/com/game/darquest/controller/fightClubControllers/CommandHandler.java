package com.game.darquest.controller.fightClubControllers;

import java.util.List;

import com.game.darquest.data.Enemy;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.Fireable;

public class CommandHandler {

	private List<Fireable> fireList;
	private int currentMovePoints;
	private int maxMovePoints;
	private List<String> modifiers;
	private String currentMod;
	private FightController fc;
	
	public CommandHandler(FightController fc) {
		this.fc = fc;
		this.fireList = fc.getFireList();
		this.currentMovePoints = fc.getCurrentMovePoints();
		this.maxMovePoints = fc.getMaxMovePoints();
		this.modifiers = fc.getModifiers();
	}
	
	public boolean commandIsValid(String command) {
		for (int i = 0; i < fireList.size(); i++) {
			if(command.equals(fireList.get(i).getCommandId())) {
				if(fireList.get(i).getPointCost() + fc.getCurrentMovePoints() <= fc.getMaxMovePoints()) {
					fc.setCurrentMovePoints(currentMovePoints+fireList.get(i).getPointCost());
					return true;
				}
			}
			if(command.startsWith(fireList.get(i).getCommandId())) {
				for (int j = 0; j < modifiers.size(); j++) {
					if(command.equals(fireList.get(i).getCommandId() + " " + modifiers.get(j))) {
						if(fireList.get(i).getPointCost() + currentMovePoints <= maxMovePoints) {
							fc.setCurrentMovePoints(currentMovePoints+fireList.get(i).getPointCost());
							return true; 
						}
					}
				}
			}
		}
		return false;
	}
	
	public String getCommandWithoutModifiers(String command) {
		String mod = command.substring(command.length() - 2, command.length());
		int indexAtMod = command.indexOf(mod);
		return command.substring(0, indexAtMod);
	}
	
	public boolean hasModifier(String command) {
		for (String e : modifiers)
			if (command.contains(e)) {
				currentMod = e;
				return true;
			}
		return false;
	}
	
	public int getCurrentMod() {
		return Integer.parseInt(currentMod);
	}

}
