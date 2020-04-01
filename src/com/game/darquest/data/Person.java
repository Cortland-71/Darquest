package com.game.darquest.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public abstract class Person {

	private DecimalFormat f2 = new DecimalFormat("0.00");
	private DecimalFormat f1 = new DecimalFormat("0.0");

	private Random rand = new Random();

	private final int maxMoves = 3;
	private int moves;

	private String name;
	private int lvl = 5;
	private double cash = 1000.0;

	private double hp = 1;
	private double eng = 0;
	private double eat = 0;
	private double sleep = 0;
	private double work = 0;

	private final int MIN = 0;
	private final int MAX_BAR = 1;

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

	public double getRandomDamageAmount() {
		int min = this.equippedWeapon.getMinDamage();
		int max = this.equippedWeapon.getMaxDamage();

		double weaponDamage = rand.nextInt((max - min) + 1) + min;
		System.out.println("Weapon damage random: " + weaponDamage);

		double engMult = this.getEng();
		System.out.println("Eng: " + engMult);

		return (weaponDamage / 100d) + (engMult / 2);
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		if (def < 0) {
			this.def = MIN;
			return;
		}
		this.def = def;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		if (stealth < 0) {
			this.stealth = MIN;
			return;
		}
		this.stealth = stealth;
	}

	public int getAwareness() {
		return awareness;
	}

	public void setAwareness(int awareness) {
		if (awareness < 0) {
			this.awareness = MIN;
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
		this.hp = Double.parseDouble(f2.format(hp));
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
		this.eng = Double.parseDouble(f2.format(eng));
	}

	public double getEat() {
		return eat;
	}

	public void setEat(double eat) {
		if (eat > 1) {
			this.eat = MAX_BAR;
			return;
		}
		if (eat < 0) {
			this.eat = MIN;
			return;
		}
		this.eat = Double.parseDouble(f1.format(eat));
	}

	public double getSleep() {
		return sleep;
	}

	public void setSleep(double sleep) {
		if (sleep > 1) {
			this.sleep = MAX_BAR;
			return;
		}
		if (sleep < 0) {
			this.sleep = MIN;
			return;
		}
		this.sleep = Double.parseDouble(f1.format(sleep));
	}

	public double getWork() {
		return work;
	}

	public void setWork(double work) {
		if (work > 1) {
			this.work = MAX_BAR;
			return;
		}
		if (work < 0) {
			this.work = MIN;
			return;
		}
		this.work = Double.parseDouble(f1.format(work));
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

	@Override
	public String toString() {
		return "Person [f2=" + f2 + ", f1=" + f1 + ", rand=" + rand + ", moves=" + moves + ", name=" + name + ", lvl="
				+ lvl + ", cash=" + cash + ", hp=" + hp + ", eng=" + eng + ", eat=" + eat + ", sleep=" + sleep
				+ ", work=" + work + ", equippedWeapon=" + equippedWeapon + ", equippedArmor=" + equippedArmor
				+ ", equippedTool=" + equippedTool + ", def=" + def + ", stealth=" + stealth + ", awareness="
				+ awareness + "]";
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

	public int getMIN() {
		return MIN;
	}

	public int getMAX_BAR() {
		return MAX_BAR;
	}

}
