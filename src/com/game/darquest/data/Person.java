package com.game.darquest.data;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public abstract class Person {

	private String name;
	private int lvl = 1;
	private double cash = 1000.0;

	private double hp = 1;

	private final int MIN = 0;
	private final int MAX_BAR = 1;

	private Weapon equippedWeapon;
	private Armor equippedArmor;
	private Tool equippedTool;

	private int attack;
	private int defaultAttack;
	private int def;
	private int defaultDef;

	private int stealth;
	private int defaultStealth;
	private int awareness;
	private int defaultAwareness;

	private int mutation;
	private int defaultMutation;

	public Person() {
	}

	public Person(String name, Weapon equippedWeapon, Armor equippedArmor, int lvl, double cash, List<Integer> statList) {
		this.name = name;
		this.equippedWeapon = equippedWeapon;
		this.equippedArmor = equippedArmor;
		this.lvl = lvl;
		this.cash = cash;

		this.setDefaultAttack(statList.get(0));
		this.setAttack(statList.get(0));
		this.setDefaultDef(statList.get(1));
		this.setDef(statList.get(1));

		this.setDefaultStealth(statList.get(2));
		this.setStealth(statList.get(2));
		this.setDefaultAwareness(statList.get(3));
		this.setAwareness(statList.get(3));

		this.setDefaultMutation(statList.get(4));
		this.setMutation(statList.get(4));
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
		setDefaultDef(def);
		setDefaultStealth(stealth);
		setDefaultAwareness(awar);
		setDef(def);
		setStealth(stealth);
		setAwareness(awar);
	}

	public void setAttack(int attack) {
		if (attack < 0) {
			this.attack = 0;
			return;
		}
		this.attack = attack;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		if (def < 0) {
			this.def = 0;
			return;
		}
		this.def = def;
	}

	public int getStealth() {

		return stealth;
	}

	public void setStealth(int stealth) {
		if (stealth < 0) {
			this.stealth = 0;
			return;
		}
		this.stealth = stealth;
	}

	public int getAwareness() {
		return awareness;
	}

	public void setAwareness(int awareness) {
		if (awareness < 0) {
			this.awareness = 0;
			return;
		}
		this.awareness = awareness;
	}
	
	public void setMutation(int mutation) {
		if (mutation < 0) {
			this.mutation = 0;
			return;
		} else if (mutation > defaultMutation) {
			this.mutation = defaultMutation;
			return;
		}
		this.mutation = mutation;
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

	public int getDefaultAwareness() {
		return defaultAwareness;
	}

	public void setDefaultAwareness(int defaultAwareness) {
		this.defaultAwareness = defaultAwareness;
	}

	public int getDefaultStealth() {

		return defaultStealth;
	}

	public void setDefaultStealth(int defaultStealth) {
		this.defaultStealth = defaultStealth;
	}

	public int getDefaultDef() {
		return defaultDef;
	}

	public void setDefaultDef(int defaultDef) {
		this.defaultDef = defaultDef;
	}

	public int getMutation() {
		return mutation;
	}

	

	public int getDefaultMutation() {
		return defaultMutation;
	}

	public void setDefaultMutation(int defaultMutation) {
		this.defaultMutation = defaultMutation;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefaultAttack() {
		return defaultAttack;
	}

	public void setDefaultAttack(int defaultAttack) {
		this.defaultAttack = defaultAttack;
	}

	@Override
	public String toString() {
		return "name=" + name + 
				"\nlvl=" + lvl + 
				"\ncash=" + cash + 
				"\nhp=" + hp +
				"\nattack=" + attack + 
				"\ndef=" + def + 
				"\nstealth=" + stealth + 
				"\nawareness=" + awareness + 
				"\nmutation=" + mutation;
	}

	public List<Integer> getAllIntegerStatsForSimulation() {
		return Arrays.asList(this.getDefaultAttack(), this.getAttack(), this.getDefaultDef(), this.getDef(),
				this.getDefaultStealth(), this.getStealth(), this.getDefaultAwareness(), this.getAwareness(),
				this.getDefaultMutation(), this.getMutation());

	}

	public void setSimStats(List<Integer> list, double cash, double hp, String name, Weapon weapon) {
		this.setName(name);
		this.setCash(cash);
		this.setHp(hp);
		this.setDefaultAttack(list.get(0));
		this.setAttack(list.get(1));
		this.setDefaultDef(list.get(2));
		this.setDef(list.get(3));
		this.setDefaultStealth(list.get(4));
		this.setStealth(list.get(5));
		this.setDefaultAwareness(list.get(6));
		this.setAwareness(list.get(7));
		this.setDefaultMutation(list.get(8));
		this.setMutation(list.get(9));
		this.setEquippedItem(weapon);
	}

	public List<Double> getListOfMainStatsDouble() {
		return Arrays.asList(this.getHp());
//		return Arrays.asList(this.getCash(), this.getHp());
		
	}
	public List<Integer> getListOfMainStatsInts() {
		return Arrays.asList(this.getAttack(), this.getDef(), this.getStealth(), this.getAwareness(), this.getMutation());
	}

}
