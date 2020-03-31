package com.game.darquest.controller;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Armor;
import com.game.darquest.data.items.Item;
import com.game.darquest.data.items.Tool;
import com.game.darquest.data.items.Weapon;

public class ItemController {

	public ItemController() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Item> getWeaponsList() {
		return Arrays.asList(
				new Weapon("Studded Gloves", studedGlovesDes(), 150, .01, 8, 8, 2, 8),
				new Weapon("Brass Knuckles", brassKnucklesDes(), 350, .05, 10, 10, 3, 9),
				new Weapon("Slugger (wood)", sluggerDes(), 250, .07, 15, 15, 5, 10),
				new Weapon("Bat (metal)", batDes(), 530, .2, 20, 20, 6, 13),
				new Weapon("Hammer", hammerDes(), 600, .08, 20, 20, 5, 15),
				new Weapon("Pocket Knife", pocketKnifeDes(), 250, .01, 10, 10, 5, 12),
				new Weapon("Switch Blade", switchBladeDes(), 350, .02, 12, 12, 6, 13),
				new Weapon("Tanto", tantoDes(), 800, .08, 30, 30, 7, 12),
				new Weapon("Machete", macheteDes(), 1200, .07, 25, 25, 8, 15),
				new Weapon("Katana", katanaDes(), 2200, .1, 10, 50, 50, 20),
				new Weapon("Throwing Star", throwingStarDes(), 1500, .01, 3, 3, 10, 13),
				new Weapon("G26 9mm", g26Des(), 1800, .35, 13, 13, 12, 22),
				new Weapon("Reefer .38", reeferDes(), 1800, .25, 8, 8, 10, 20),
				new Weapon("Winterfield .40", winterFieldDes(), 2000, .3, 12, 12, 12, 25),
				new Weapon("AX-15", axDes(), 2500, .5, 20, 20, 15, 20),
				new Weapon("Cherry", cherryDes(), 2100, .3, 12, 12, 12, 18),
				new Weapon("Sniper Rifle", sniperRifleDes(), 3700, .6, 8, 8, 20, 30),
				new Weapon("Shotgun", shotgunDes(), 4000, .4, 12, 12, 22, 40),
				new Weapon("The Machine", machineDes(), 5500, .7, 100, 100, 25, 40),
				new Weapon("Chainsaw", chainsawDes(), 5800, .5, 15, 15, 20, 35));
	}
	
	public List<Item> getArmorList() {
		return Arrays.asList(
				new Armor("Raincoat", raincoatDes(), 100, .01, 20, 20, 1),
				new Armor("Helmet", helmetDes(), 150, .02, 12, 12, 1),
				new Armor("Simple Vest", simpleVestDes(), 250, .02, 10, 10, 2),
				new Armor("Gas Mask", gasMaskDes(), 300, .01, 25, 25, 2),
				new Armor("Leather Jacket", leatherJacketDes(), 800, .02, 30, 30, 3),
				new Armor("Ballistic Vest", ballisticVestDes(), 1000, .03, 50, 50, 4));
	}
	
	public List<Item> getToolList() {
		return Arrays.asList(
				new Tool("Weak Hp Vial", hpVileDes(), 50, .01, 1, 1, 5, 10),
				new Tool("Strong Hp Vial", hpVileDes(), 150, .02, 1, 1, 20, 50),
				new Tool("Weak Eng Vial", engVileDes(), 200, .02, 1, 1, 1, 5),
				new Tool("Strong Eng Vial", engVileDes(), 400, .03, 1, 1, 5, 15),
				new Tool("Cup of Fear", fearDes(), 250, .01, 1, 1, 2, 5),
				new Tool("Cup of Valor", valorDes(), 250, .01, 1, 1, 2, 5),
				new Tool("Cup of Light", lightDes(), 250, .01, 1, 1, 2, 5),
				new Tool("Cup of Shadow", shadowDes(), 250, .01, 1, 1, 2, 5),
				new Tool("Cup of Truth", truthDes(), 250, .01, 1, 1, 2, 5),
				new Tool("Cup of Deception", deceptionDes(), 250, .01, 1, 1, 2, 5),
				new Tool("Bottle of Fear", fearDes(), 850, .02, 1, 1, 20, 30),
				new Tool("Bottle of Valor", valorDes(), 850, .02, 1, 1, 20, 30),
				new Tool("Bottle of Light", lightDes(), 850, .02, 1, 1, 20, 30),
				new Tool("Bottle of Shadow", shadowDes(), 850, .02, 1, 1, 20, 30),
				new Tool("Bottle of Truth", truthDes(), 850, .02, 1, 1, 20, 30),
				new Tool("Bottle of Deception", deceptionDes(), 850, .02, 1, 1, 20, 30),
				new Tool("Raw Fear", fearDes(), 2500, .01, 1, 1, 100, 100),
				new Tool("Raw Valor",valorDes(), 2500, .01, 1, 1, 100, 100),
				new Tool("Raw Light", lightDes(), 2500, .01, 1, 1, 100, 100),
				new Tool("Raw Shadow", shadowDes(), 2500, .01, 1, 1, 100, 100),
				new Tool("Raw Truth", truthDes(), 2500, .01, 1, 1, 100, 100),
				new Tool("Raw Deception", deceptionDes(), 2500, .01, 1, 1, 100, 100));
	}
	
	private String truthDes() {
		return "";
	}
	
	private String deceptionDes() {
		return "";
	}
	
	private String shadowDes() {
		return "";
	}
	
	private String lightDes() {
		return "";
	}
	
	private String valorDes() {
		return "";
	}
	
	private String fearDes() {
		return "";
	}
	
	private String hpVileDes() {
		return "";
	}
	
	private String engVileDes() {
		return "";
	}
	
	private String studedGlovesDes() {
		return "";
	}
	
	private String brassKnucklesDes() {
		return "";
	}
	
	private String sluggerDes() {
		return "";
	}
	
	private String batDes() {
		return "";
	}
	
	private String hammerDes() {
		return "";
	}
	
	private String pocketKnifeDes() {
		return "";
	}
	
	private String switchBladeDes() {
		return "";
	}
	
	private String tantoDes() {
		return "";
	}
	
	private String macheteDes() {
		return "";
	}
	
	private String katanaDes() {
		return "";
	}
	
	private String throwingStarDes() {
		return "";
	}
	
	private String g26Des() {
		return "";
	}
	
	private String reeferDes() {
		return "";
	}
	
	private String winterFieldDes() {
		return "";
	}
	
	private String axDes() {
		return "";
	}
	
	private String cherryDes() {
		return "";
	}
	
	private String sniperRifleDes() {
		return "";
	}
	
	private String shotgunDes() {
		return "";
	}

	private String machineDes() {
		return "";
	}
	
	private String chainsawDes() {
		return "";
	}
	
	private String raincoatDes() {
		return "";
	}

	private String helmetDes() {
		return "";
	}
	
	private String simpleVestDes() {
		return "";
	}
	
	private String gasMaskDes() {
		return "";
	}

	private String leatherJacketDes() {
		return "";
	}
	
	private String ballisticVestDes() {
		return "";
	}

}
