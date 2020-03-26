package com.game.darquest.data;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public abstract class Person {
	

	private String name;
	private int lvl = 1;
	private double cash = 186666.75;
	
	private double xp = .05;
	private double xpAdded = .05;
	private double maxXp = 1;
	private final double minXp = 0;

	private double hp = .5;
	private double maxHp = 1;
	private final double minHp = 0;

	private double eng = .03;
	private double maxEng = 1;
	private final double minEng = 0;

	private double eat = .5;
	private double maxEat = 1;
	private final double minEat = 0;

	private double sleep = .5;
	private double maxSleep = 1;
	private final double minSleep = 0;

	private double work = .5;
	private double maxWork = 1;
	private final double minWork = 0;
	
	private List<Double> statList;
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public double getXp() {
		return xp;
	}
	

	public void setXp(double xp) {
		this.xp = xp;
	}

	public double getXpAdded() {
		return xpAdded;
	}

	public void setXpAdded(double xpAdded) {
		this.xpAdded = xpAdded;
	}

	public double getMaxXp() {
		return maxXp;
	}

	public void setMaxXp(double maxXp) {
		this.maxXp = maxXp;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}

	public double getEng() {
		return eng;
	}

	public void setEng(double eng) {
		this.eng = eng;
	}

	public double getMaxEng() {
		return maxEng;
	}

	public void setMaxEng(double maxEng) {
		this.maxEng = maxEng;
	}

	public double getEat() {
		return eat;
	}

	public void setEat(double eat) {
		this.eat = eat;
	}

	public double getMaxEat() {
		return maxEat;
	}

	public void setMaxEat(double maxEat) {
		this.maxEat = maxEat;
	}

	public double getSleep() {
		return sleep;
	}

	public void setSleep(double sleep) {
		this.sleep = sleep;
	}

	public double getMaxSleep() {
		return maxSleep;
	}

	public void setMaxSleep(double maxSleep) {
		this.maxSleep = maxSleep;
	}

	public double getWork() {
		return work;
	}

	public void setWork(double work) {
		this.work = work;
	}

	public double getMaxWork() {
		return maxWork;
	}

	public void setMaxWork(double maxWork) {
		this.maxWork = maxWork;
	}

	public double getCash() {
		return cash;
	}
	public String getCashFormatted() {
		return NumberFormat.getCurrencyInstance().format(cash);
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", lvl=" + lvl + ", cash=" + cash + ", xp=" + xp + ", xpAdded=" + xpAdded
				+ ", maxXp=" + maxXp + ", minXp=" + minXp + ", hp=" + hp + ", maxHp=" + maxHp + ", minHp=" + minHp
				+ ", eng=" + eng + ", maxEng=" + maxEng + ", minEng=" + minEng + ", eat=" + eat + ", maxEat=" + maxEat
				+ ", minEat=" + minEat + ", sleep=" + sleep + ", maxSleep=" + maxSleep + ", minSleep=" + minSleep
				+ ", work=" + work + ", maxWork=" + maxWork + ", minWork=" + minWork + "]";
	}
	
	

}
