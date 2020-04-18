package com.game.darquest.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SpinnerValueFactory;

public class LevelUpController {
	private Controller c;
	protected int def, max;
	protected SpinnerValueFactory<Integer> valueFactory;
	protected int pointsAvailable = 3;
	private StealthLevelUpController sluc;
	private DefLevelUpController dluc;
	
	public LevelUpController(Controller c) {
		this.c = c;
		sluc = new StealthLevelUpController(this.c);
		dluc = new DefLevelUpController(this.c);
	}
	
	public LevelUpController() {}
	
	public SpinnerValueFactory<Integer> getFactory() {
		def = c.getPlayer().getDef();
		max = def + pointsAvailable;
		valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(def, max, def);
		return this.valueFactory;
	}
	
	public int getPointsAvailable() {
		return pointsAvailable;
	}

	public void setPointsAvailable(int pointsAvailable) {
		this.pointsAvailable = pointsAvailable;
	}

	public DefLevelUpController getDluc() {
		return dluc;
	}
	
	public StealthLevelUpController getSluc() {
		return this.sluc;
	}
}

class DefLevelUpController extends LevelUpController implements ChangeListener<Integer> {
	
	private Controller c;
	public DefLevelUpController(Controller c) {
		this.c = c;
	}

	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		System.out.println("def control");
		pointsAvailable += newValue > oldValue ? -1 : 1;
		this.c.getView().getLevelUpView().setLevelUpInfoLabel(pointsAvailable);
	}
	
}

class StealthLevelUpController extends LevelUpController implements ChangeListener<Integer> {
	
	private Controller c;
	public StealthLevelUpController(Controller c) {
		this.c = c;
	}
	
	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		System.out.println("this is stealth control");
		pointsAvailable += newValue > oldValue ? -1 : 1;
		this.c.getView().getLevelUpView().setLevelUpInfoLabel(pointsAvailable);
	}
	
}
