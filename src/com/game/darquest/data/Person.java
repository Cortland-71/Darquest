package com.game.darquest.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public abstract class Person {

	private DecimalFormat f2 = new DecimalFormat("0.00");
	private DecimalFormat f1 = new DecimalFormat("0.0");
	
	private final int maxMoves = 3;
	private int moves;

	private String name;
	private int lvl = 1;
	private double cash = 1000.0;

	private double hp = 1;
	private final double maxHp = 1;
	private final double minHp = 0;

	private double eng = 0;
	private final double maxEng = 1;
	private final double minEng = 0;

	private double eat = 0;
	private final double maxEat = 1;
	private final double minEat = 0;

	private double sleep = 0;
	private final double maxSleep = 1;
	private final double minSleep = 0;

	private double work = 0;
	private final double maxWork = 1;
	private final double minWork = 0;

	private Weapon equippedWeapon;
	private Armor equippedArmor;
	private Tool equippedTool;

	private int def;
	private int stealth;
	private int awareness;

	public Person() {
	}

	public Person(String name, int def, int stealth, int awareness, Weapon equippedWeapon, Armor equippedArmor,
			Tool equippedTool, int lvl, double cash) {
		this.name = name;
		this.def = def;
		this.stealth = stealth;
		this.awareness = awareness;
		this.equippedWeapon = equippedWeapon;
		this.equippedArmor = equippedArmor;
		this.equippedTool = equippedTool;
		this.lvl = lvl;
		this.cash = cash;
	}

	public void setEquippedItem(Item item) {
		if (item instanceof Weapon)
			this.equippedWeapon = (Weapon) item;
		else if (item instanceof Armor)
			this.equippedArmor = (Armor) item;
		else if (item instanceof Tool)
			this.equippedTool = (Tool) item;

	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		this.stealth = stealth;
	}

	public int getAwareness() {
		return awareness;
	}

	public void setAwareness(int awareness) {
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
		this.hp = Double.parseDouble(f2.format(hp));
	}

	public double getMaxHp() {
		return maxHp;
	}

	public double getEng() {
		return eng;
	}

	public void setEng(double eng) {
		if (eng < 0) {
			this.eng = 0;
			return;
		}
		this.eng = Double.parseDouble(f2.format(eng));
	}

	public double getMaxEng() {
		return maxEng;
	}

	public double getEat() {
		return eat;
	}

	public void setEat(double eat) {
		this.eat = Double.parseDouble(f1.format(eat));
	}

	public double getMaxEat() {
		return maxEat;
	}

	public double getSleep() {
		return sleep;
	}

	public void setSleep(double sleep) {
		this.sleep = Double.parseDouble(f1.format(sleep));
	}

	public double getMaxSleep() {
		return maxSleep;
	}

	public double getWork() {
		return work;
	}

	public void setWork(double work) {
		this.work = Double.parseDouble(f1.format(work));
	}

	public double getMaxWork() {
		return maxWork;
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

	public double getMinHp() {
		return minHp;
	}

	public double getMinEng() {
		return minEng;
	}

	public double getMinEat() {
		return minEat;
	}

	public double getMinSleep() {
		return minSleep;
	}

	public double getMinWork() {
		return minWork;
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", lvl=" + lvl + ", cash=" + cash + ", hp=" + hp + ", maxHp=" + maxHp
				+ ", minHp=" + minHp + ", eng=" + eng + ", maxEng=" + maxEng + ", minEng=" + minEng + ", eat=" + eat
				+ ", maxEat=" + maxEat + ", minEat=" + minEat + ", sleep=" + sleep + ", maxSleep=" + maxSleep
				+ ", minSleep=" + minSleep + ", work=" + work + ", maxWork=" + maxWork + ", minWork=" + minWork + "]";
	}

	public int getMaxMoves() {
		return maxMoves;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

}
