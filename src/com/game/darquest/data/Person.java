package com.game.darquest.data;

import java.text.NumberFormat;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public abstract class Person {

	private String name;
	private int lvl = 1;
	private double cash = 1000.0;

	private double hp = 1;
	private double eng = 0;

	private final int MIN = 0;
	private final int MAX_BAR = 1;

	private Weapon equippedWeapon;
	private Armor equippedArmor;
	private Tool equippedTool;

	private int def;
	private int maxDef;
	private int stealth;
	private int maxStealth;
	private int awareness;
	private int maxAwareness;
	private int mutation;
	private int maxMutation;
	private int preservation;
	private int maxPreservation;

	public Person() {
	}

	public Person(String name, int maxDef, int maxStealth, int maxAwareness, int maxMutation, int maxPreserve,
			Weapon equippedWeapon, Armor equippedArmor, int lvl, double cash) {
		this.name = name;
		this.equippedWeapon = equippedWeapon;
		this.equippedArmor = equippedArmor;
		this.lvl = lvl;
		this.cash = cash;
		this.setMaxDef(maxDef);
		this.setMaxStealth(maxStealth);
		this.setMaxAwareness(maxAwareness);
		this.setMaxMutation(maxMutation);
		this.setMaxPreserve(maxPreserve);
		this.setDef(maxDef);
		this.setStealth(maxStealth);
		this.setAwareness(maxAwareness);
		this.setMutation(maxMutation);
		this.setPreserve(maxPreserve);
	}

	public void setEquippedItem(Item item) {
		if (item instanceof Weapon)
			this.equippedWeapon = (Weapon) item;
		else if (item instanceof Armor)
			this.equippedArmor = (Armor) item;
		else if (item instanceof Tool)
			this.equippedTool = (Tool) item;

	}
	
	public void setAllSkills(int def, int stealth, int awar) {
		setMaxDef(def);
		setMaxStealth(stealth);
		setMaxAwareness(awar);
		setDef(def);
		setStealth(stealth);
		setAwareness(awar);
	}
	

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		if (def < 1) {
			this.def = 1;
			return;
		} else if (def > this.getMaxDef()) {
			this.def = this.getMaxDef();
			return;
		}
		this.def = def;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		if (stealth < 1) {
			this.stealth = 1;
			return;
		} else if (stealth > this.getMaxStealth()) {
			this.stealth = this.getMaxStealth();
			return;
		}
		this.stealth = stealth;
	}

	public int getAwareness() {
		return awareness;
	}

	public void setAwareness(int awareness) {
		if (awareness < 1) {
			this.awareness = 1;
			return;
		} else if (awareness > this.getMaxAwareness()) {
			this.awareness = this.getMaxAwareness();
			return;
		}
		this.awareness = awareness;
	}

	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}

	public Armor getEquippedArmor() {
		return equippedArmor;
	}

	public Tool getEquippedTool() {
		return equippedTool;
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

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		if (hp > 1) {
			this.hp = MAX_BAR;
			return;
		}
		if (hp < 0) {
			this.hp = MIN;
			return;
		}
		this.hp = Math.round(hp * 100.0) / 100.0;
	}

	public double getEng() {
		return eng;
	}

	public void setEng(double eng) {
		if (eng > 1) {
			this.eng = MAX_BAR;
			return;
		}
		if (eng < 0) {
			this.eng = MIN;
			return;
		}
		this.eng = Math.round(eng * 100.0) / 100.0;
	}


	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		if (cash < 0) {
			this.cash = MIN;
			return;
		}
		this.cash = cash;
	}

	public String getCashFormatted() {
		return NumberFormat.getCurrencyInstance().format(cash);
	}

	public String getEquippedWeaponString() {
		return equippedWeapon.getName();
	}

	public String getEquippedArmorString() {
		return equippedArmor.getName();
	}

	public String getEquippedToolString() {
		return equippedTool.getName();
	}

	public int getMIN() {
		return MIN;
	}

	public int getMAX_BAR() {
		return MAX_BAR;
	}

	public int getMaxAwareness() {
		return maxAwareness;
	}

	public void setMaxAwareness(int maxAwareness) {
		this.maxAwareness = maxAwareness;
	}

	public int getMaxStealth() {
		return maxStealth;
	}

	public void setMaxStealth(int maxStealth) {
		this.maxStealth = maxStealth;
	}

	public int getMaxDef() {
		return maxDef;
	}

	public void setMaxDef(int maxDef) {
		this.maxDef = maxDef;
	}

	public int getMutation() {
		return mutation;
	}

	public void setMutation(int mutation) {
		this.mutation = mutation;
	}

	public int getMaxMutation() {
		return maxMutation;
	}

	public void setMaxMutation(int maxMutation) {
		this.maxMutation = maxMutation;
	}

	public int getPreserve() {
		return preservation;
	}

	public void setPreserve(int preservation) {
		this.preservation = preservation;
	}

	public int getMaxPreserve() {
		return maxPreservation;
	}

	public void setMaxPreserve(int maxPreservation) {
		this.maxPreservation = maxPreservation;
	}
	
	

}
