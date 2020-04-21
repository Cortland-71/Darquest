package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ChallengeController implements EventHandler<MouseEvent> {
	
	private Player p;
	private Controller c;
	private int compleated = 0;
	private List<Challengable> challengeChecks = new ArrayList<>();
	
	public ChallengeController(Controller c) {
		this.c = c;
		this.c.getView().getFightClubHub().getChallengesSelectView().addChallengeListener(this);
		this.c.getView().getFightClubHub().getChallengesSelectView().addBackButtonListener(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						c.getView().getHubView().showFightClubHub();
					}
				});
 		this.p = (Player)c.getPlayer();
		challengeChecks.add(new Challenge1(this.p));
		challengeChecks.add(new Challenge2(this.c));
	}

	@Override
	public void handle(MouseEvent e) {
		System.out.println(((Label)e.getSource()).getId());
		
	}
	
	public void updateChallengeList() {
		compleated = 0;
		c.getView().getFightClubHub().getChallengesSelectView().getChallengeSelectionBox().getChildren().clear();
		List<Label> labelList = c.getView().getFightClubHub().getChallengesSelectView().getChallengeLabels();
		for (int i = 0; i < labelList.size(); i++) {
			if(p.getChallengeBools().get(i)) {
				labelList.get(i).setStyle("-fx-font: 15px \"8-BIT FORTRESS\";"
										+ "-fx-text-fill: light;"
										+ "-fx-background-color: dark;"
										+ "-fx-border-color: light;");
				labelList.set(i, labelList.get(i));
				compleated++;
			}
			c.getView().getFightClubHub().getChallengesSelectView().getChallengeSelectionBox().getChildren().add(labelList.get(i));
		}
		c.getView().getFightClubHub().getChallengesSelectView().setCounterLabel(compleated);
	}
	
	public int getChallengeCheckListSize() {
		return challengeChecks.size();
	}
	
	public void runChallengeTest(int i) {
		boolean state = challengeChecks.get(i).checkChallenge();
		p.setChallengeBoolsByIndex(i, state);
		
	}
}

class Challenge1 implements Challengable {
	private Player p;
	public Challenge1(Person p) {
		this.p = (Player)p;
	}
	@Override
	public boolean checkChallenge() {
		return p.getKills() > 0 ? true : false;
	}
}

class Challenge2 implements Challengable {
	private Controller c;
	public Challenge2(Controller c) {
		this.c = c;
	}
	@Override
	public boolean checkChallenge() {
		return c.getFightClubController().getNumberOfEnemysFoughtAtOnce() > 2 ? true : false;
	}
}


interface Challengable {
	boolean checkChallenge();
}