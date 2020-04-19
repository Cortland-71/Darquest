package com.game.darquest.controller;


import com.game.darquest.data.Player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SpinnerValueFactory;

public class LevelUpController implements EventHandler<ActionEvent> {
	private Controller c;
	protected SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;
	protected static int pointsAvailable = 3;
	private StealthLevelUpController sluc;
	private DefLevelUpController dluc;
	private AwarLevelUpController awaruc;
	int defMax = 0;
	int stMax = 0;
	int awarMax = 0;
	
	public LevelUpController(Controller c) {
		this.c = c;
		sluc = new StealthLevelUpController(this.c);
		dluc = new DefLevelUpController(this.c);
		awaruc = new AwarLevelUpController(this.c);
		this.c.getView().getLevelUpView().addFinishButtonListener(this);
	}
	
	public LevelUpController() {}
	
	public int getPointsAvailable() {
		return pointsAvailable;
	}

	public void setPointsAvailable(int pointsAvailable) {
		LevelUpController.pointsAvailable = pointsAvailable;
	}

	public DefLevelUpController getDluc() {
		return dluc;
	}
	
	public StealthLevelUpController getSluc() {
		return this.sluc;
	}

	public AwarLevelUpController getAwaruc() {
		return awaruc;
	}

	@Override
	public void handle(ActionEvent event) {
		Player p = (Player)c.getPlayer();
		int def = c.getView().getLevelUpView().getDefFactory().getValue();
		int stealth = c.getView().getLevelUpView().getStealthFactory().getValue();
		int awar = c.getView().getLevelUpView().getAwarFactory().getValue();
		p.setAllSkills(def, stealth, awar);
		p.setXp(p.getXp() - 1);
		p.setLvl(p.getLvl()+1);
		c.getView().getHubView().setPlayerStats(p);
		c.getView().getHubView().showHub();
	}
}

class DefLevelUpController extends LevelUpController implements ChangeListener<Integer> {
	
	private Controller c;
	public DefLevelUpController(Controller c) {
		this.c = c;
	}

	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		pointsAvailable += oldValue < newValue ? -1 : 1;
		c.getView().getLevelUpView().setLevelUpInfoLabel(pointsAvailable);
		int currentDefPoints = c.getView().getLevelUpView().getDefFactory().getValue();
		int currentStealthPoints = c.getView().getLevelUpView().getStealthFactory().getValue();
		int currentAwarPoints = c.getView().getLevelUpView().getAwarFactory().getValue();
		defMax = currentDefPoints + pointsAvailable;
		stMax = currentStealthPoints + pointsAvailable;
		awarMax = currentAwarPoints + pointsAvailable;
		c.getView().getLevelUpView().getDefFactory().setMax(defMax);
		c.getView().getLevelUpView().getStealthFactory().setMax(stMax);
		c.getView().getLevelUpView().getAwarFactory().setMax(awarMax);
	}
}

class StealthLevelUpController extends LevelUpController implements ChangeListener<Integer> {
	
	private Controller c;
	public StealthLevelUpController(Controller c) {
		this.c = c;
	}
	
	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		pointsAvailable += oldValue < newValue ? -1 : 1;
		c.getView().getLevelUpView().setLevelUpInfoLabel(pointsAvailable);
		int currentDefPoints = c.getView().getLevelUpView().getDefFactory().getValue();
		int currentStealthPoints = c.getView().getLevelUpView().getStealthFactory().getValue();
		int currentAwarPoints = c.getView().getLevelUpView().getAwarFactory().getValue();
		defMax = currentDefPoints + pointsAvailable;
		stMax = currentStealthPoints + pointsAvailable;
		awarMax = currentAwarPoints + pointsAvailable;
		c.getView().getLevelUpView().getDefFactory().setMax(defMax);
		c.getView().getLevelUpView().getStealthFactory().setMax(stMax);
		c.getView().getLevelUpView().getAwarFactory().setMax(awarMax);
	}
}

class AwarLevelUpController extends LevelUpController implements ChangeListener<Integer> {
	
	private Controller c;
	public AwarLevelUpController(Controller c) {
		this.c = c;
	}

	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		pointsAvailable += oldValue < newValue ? -1 : 1;
		c.getView().getLevelUpView().setLevelUpInfoLabel(pointsAvailable);
		int currentDefPoints = c.getView().getLevelUpView().getDefFactory().getValue();
		int currentStealthPoints = c.getView().getLevelUpView().getStealthFactory().getValue();
		int currentAwarPoints = c.getView().getLevelUpView().getAwarFactory().getValue();
		defMax = currentDefPoints + pointsAvailable;
		stMax = currentStealthPoints + pointsAvailable;
		awarMax = currentAwarPoints + pointsAvailable;
		c.getView().getLevelUpView().getDefFactory().setMax(defMax);
		c.getView().getLevelUpView().getStealthFactory().setMax(stMax);
		c.getView().getLevelUpView().getAwarFactory().setMax(awarMax);
	}
}
