package com.game.darquest.data.actions;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Person;
import com.game.darquest.data.actions.use.HpTool;
import com.game.darquest.data.actions.use.Useable;

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
		int effect = p.getRandomToolAmount();
		
		for (int i = 0; i < useableTools.size(); i++) {
			if(catagory.equals(useableTools.get(i).getCatagory())) {
				output = useableTools.get(i).use(choosen, effect);
			}
		}
		c.getPlayerInvStatsController().removeItemWhenUsed();
		return true;
	}
	
	

	@Override
	public String getCommandId() {
		return "use";
	}

	@Override
	public String getOutput() {
		return output;
	}

}
