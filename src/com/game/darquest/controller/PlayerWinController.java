package com.game.darquest.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlayerWinController implements EventHandler<ActionEvent> {

	private static int efficiencyScore = 100;
	private Double xpEarned = 0.0;
	private Double cashEarned = 0.0;
	private Double bonusCashEarned = 0.0;
	private double totalCashEarned = 0.0;
	private Integer numPlayerMoves = 0;
	
	private List<Item> lootList = new ArrayList<>();

	private Controller c;
	public PlayerWinController(Controller c) {
		this.c = c;
		c.getView().getPlayerWinView().addContinueButtonListener(this);
	}

	public static int getEfficiencyScore() {
		return efficiencyScore;
	}

	public static void setEfficiencyScore(int efficiencyScore) {
		if(efficiencyScore < 0) { PlayerWinController.efficiencyScore = 0; return; }
		PlayerWinController.efficiencyScore = efficiencyScore;
	}
	
	public void setAllCountersToZero() {
		numPlayerMoves = 0;
		xpEarned = 0.0;
		cashEarned = 0.0;
		bonusCashEarned = 0.0;
	}
	
	public String getRating(int efficiencyScore) {
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

	@Override
	public void handle(ActionEvent e) {
		Player p = (Player) c.getPlayer();
		p.setCash(p.getCash() + getTotalCashEarned());
		List<Item> wonItemList = getLootList();
	
		addItemsToPlayerInv(wonItemList);
		c.getPlayerInvStatsController().captureSelectedItemsUpdateInvReEquipItems();
		c.getView().getHubView().showHub();
	}
	
	public void addItemsToPlayerInv(List<Item> list) {
		for(Item item : list) {
			((Player)c.getPlayer()).addItemToPlayerInventory(item);
		}
	}
	
	public List<Item> getLootList() {
		return lootList;
	}

	public double getTotalCashEarned() {
		return totalCashEarned;
	}
	
	public Double getXpEarned() {
		return xpEarned;
	}

	public void setXpEarned(Double xpEarned) {
		this.xpEarned = xpEarned;
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

	public void setTotalCashEarned(double totalCashEarned) {
		this.totalCashEarned = totalCashEarned;
	}

	public String getTotalCashEarnedFormatted() {
		totalCashEarned = bonusCashEarned + cashEarned;
		return NumberFormat.getCurrencyInstance().format(totalCashEarned);
	}
	
	public String getBonusCashEarnedFormatted() {
		double bonusCashEarned = PlayerWinController.getEfficiencyScore() * 10d;
		return NumberFormat.getCurrencyInstance().format(bonusCashEarned);
	}
	
	public void addXpToPlayer(double xp) {
		xpEarned += xp;
		Player p = ((Player) c.getPlayer());
		p.setXp(p.getXp() + xp);
	}
}
