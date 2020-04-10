package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.use.HpTool;
import com.game.darquest.data.actions.use.Useable;
import com.game.darquest.data.items.Tool;

public class Use implements Fireable {

	private String output;
	private DecimalFormat f2 = new DecimalFormat("0.00");
	
	private List<Useable> useableTools = Arrays.asList(new HpTool());

	private Controller c;
	public Use(Controller c) {
		this.c = c;
	}
	
	@Override
	public boolean fire(Person p, Person choosen) {

		String catagory = p.getEquippedTool().getCatagory();
		if(catagory.equals("none")) {
			output = "You can't use nothing...";
			return false;
		}
		if(choosen.getHp() >= 1) {
			output = "Your HP is already full...";
			return false;
		}
		Tool t = p.getEquippedTool();
		int effect = getRandomToolAmount(t);
		
		for (int i = 0; i < useableTools.size(); i++) {
			if(catagory.equals(useableTools.get(i).getCatagory())) {
				output = useableTools.get(i).use(choosen, effect);
			}
		}
		c.getPlayerInvStatsController().removeItemWhenUsedUp(t);
		return true;
	}
	
	public int getRandomToolAmount(Tool t) {
		Random rand = new Random();
		int min = t.getMinEffect();
		int max = t.getMaxEffect();
		t.setCondition(t.getCondition() - 1);
		return rand.nextInt((max - min) + 1) + min;
	}
	
	@Override
	public String getCommandId() {
		return "se";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
