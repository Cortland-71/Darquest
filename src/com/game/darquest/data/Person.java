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

	private final int MIN = 0;
	private final int MAX_BAR = 1;

	private Weapon equippedWeapon;
	private Armor equippedArmor;
	private Tool equippedTool;

	private int attack;
	private int maxAttack;
	private int def;
	private int maxDef;
	
	private int stealth;
	private int maxStealth;
	private int awareness;
	private int maxAwareness;
	
	private int mutation;
	private int maxMutation;

	public Person() {
	}

	public Person(String name, int maxAttack, int maxDef, int maxStealth, int maxAwareness, int maxMutation, int maxSecurity,
			Weapon equippedWeapon, Armor equippedArmor, int lvl, double cash) {
		this.name = name;
		this.equippedWeapon = equippedWeapon;
		this.equippedArmor = equippedArmor;
		this.lvl = lvl;
		this.cash = cash;
		
		this.setMaxAttack(maxAttack);
		this.setAttack(maxAttack);
		this.setMaxDef(maxDef);
		this.setDef(maxDef);
		
		this.setMaxStealth(maxStealth);
		this.setStealth(maxStealth);
		this.setMaxAwareness(maxAwareness);
		this.setAwareness(maxAwareness);
		
		this.setMaxMutation(maxMutation);
		this.setMutation(maxMutation);
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
		if(mutation < 1) {
			this.mutation = 1;
			return;
		} else if(mutation > maxMutation) {
			this.mutation = maxMutation;
			return;
		}
		this.mutation = mutation;
	}

	public int getMaxMutation() {
		return maxMutation;
	}

	public void setMaxMutation(int maxMutation) {
		this.maxMutation = maxMutation;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getMaxAttack() {
		return maxAttack;
	}

	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", lvl=" + lvl + ", cash=" + cash + ", hp=" + hp + ", MIN=" + MIN + ", MAX_BAR="
				+ MAX_BAR + ", equippedWeapon=" + equippedWeapon + ", equippedArmor=" + equippedArmor
				+ ", equippedTool=" + equippedTool + ", attack=" + attack + ", maxAttack=" + maxAttack + ", def=" + def
				+ ", maxDef=" + maxDef + ", stealth=" + stealth + ", maxStealth=" + maxStealth + ", awareness="
				+ awareness + ", maxAwareness=" + maxAwareness + ", mutation=" + mutation + ", maxMutation="
				+ maxMutation + "]";
	}


	
	
	

}
