package com.game.darquest.controller;

import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlayerWinController implements EventHandler<ActionEvent> {

	private Controller c;
	public PlayerWinController(Controller c) {
		this.c = c;
		c.getView().getPlayerWinView().addContinueButtonListener(this);
	}

	@Override
	public void handle(ActionEvent e) {
		Player p = (Player) c.getPlayer();
		p.setCash(p.getCash() + c.getFightClubController().getTotalCashEarned());
		List<Item> wonItemList = c.getFightClubController().getLootList();
	
		addItemsToPlayerInv(wonItemList);
		c.getPlayerInvStatsController().captureSelectedItemsUpdateInvReEquipItems();
		c.getView().getHubView().showHub();
	}
	
	public void addItemsToPlayerInv(List<Item> list) {
		for(Item item : list) {
			((Player)c.getPlayer()).addItemToPlayerInventory(item);
		}
	}
}
