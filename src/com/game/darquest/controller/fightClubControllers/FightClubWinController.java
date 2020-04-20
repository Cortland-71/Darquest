package com.game.darquest.controller.fightClubControllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.game.darquest.controller.Controller;
import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FightClubWinController implements EventHandler<ActionEvent> {

	private DecimalFormat f2 = new DecimalFormat("0.00");
	private static int efficiencyScore = 100;
	private double xpEarned = 0.0;
	private double cashEarned = 0.0;
	private double bonusCashEarned = 0.0;
	private double totalCashEarned = 0.0;
	private int numPlayerMoves = 0;
	
	private List<Item> lootList = new ArrayList<>();

	private Controller c;
	public FightClubWinController(Controller c) {
		this.c = c;
		c.getView().getPlayerWinView().addContinueButtonListener(this);
	}
	
	@Override
	public void handle(ActionEvent e) {
		Player p = (Player) c.getPlayer();
		p.setCash(p.getCash() + totalCashEarned);
		List<Item> wonItemList = lootList;
	
		addItemsToPlayerInv(wonItemList);
		c.getPlayerInvStatsController().captureSelectedItemsUpdateInvReEquipItems();
		c.getView().getHubView().showFightClubHub();
	}
	
	public void setAllCountersToDefault() {
		FightClubWinController.setEfficiencyScore(100);
		numPlayerMoves = 0;
		xpEarned = 0.0;
		cashEarned = 0.0;
		bonusCashEarned = 0.0;
	}

	public static int getEfficiencyScore() {
		return efficiencyScore;
	}

	public static void setEfficiencyScore(int efficiencyScore) {
		if(efficiencyScore < 0) { FightClubWinController.efficiencyScore = 0; return; }
		FightClubWinController.efficiencyScore = efficiencyScore;
	}
	
	public String getRating() {
		Map<Integer, String> gradeMap = new HashMap<>();
		gradeMap.put(100, "S");
		gradeMap.put(95, "A+");
		gradeMap.put(90, "A");
		gradeMap.put(85, "B+");
		gradeMap.put(80, "B");
		gradeMap.put(75, "C+");
		gradeMap.put(70, "C");
		for (Entry<Integer, String> entry : gradeMap.entrySet()) {
			if (efficiencyScore == entry.getKey())
				return entry.getValue();
		}
		return "D";
	}
	
	public void addItemsToPlayerInv(List<Item> list) {
		for(Item item : list) {
			((Player)c.getPlayer()).addItemToPlayerInventory(item);
		}
	}
	
	public List<Item> getLootList() {
		return lootList;
	}
	
	public String getXpEarned() {
		return f2.format(xpEarned);
	}

	public String getCashEarnedFormatted() {
		return NumberFormat.getCurrencyInstance().format(cashEarned);
	}

	public void setCashEarned(Double cashEarned) {
		this.cashEarned = cashEarned;
	}

	public Integer getNumPlayerMoves() {
		return numPlayerMoves;
	}

	public void setNumPlayerMoves(Integer numPlayerMoves) {
		this.numPlayerMoves = numPlayerMoves;
	}

	public String getTotalCashEarnedFormatted() {
		totalCashEarned = bonusCashEarned + cashEarned;
		return NumberFormat.getCurrencyInstance().format(totalCashEarned);
	}
	
	public String getBonusCashEarnedFormatted() {
		bonusCashEarned = FightClubWinController.getEfficiencyScore() * 10d;
		return NumberFormat.getCurrencyInstance().format(bonusCashEarned);
	}
	
	public void addXpToPlayer(double xp) {
		xpEarned += xp;
		Player p = ((Player) c.getPlayer());
		p.setXp(p.getXp() + xp);
	}
}
