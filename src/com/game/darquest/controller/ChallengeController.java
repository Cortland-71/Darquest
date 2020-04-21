package com.game.darquest.controller;

import java.util.ArrayList;
import java.util.List;

import com.game.darquest.data.Person;
import com.game.darquest.data.Player;

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

		this.p = (Player)c.getPlayer();
		challengeChecks.add(new Challenge1(this.p));
	}
	
	public void updateChallengeList() {
//		c.getView().getFightClubHub().getChallengesSelectView().getChallengeLabels()
//		.get(0).setStyle("-fx-strikethrough: true");
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
	
	
	
	@Override
	public void handle(MouseEvent e) {
		System.out.println(((Label)e.getSource()).getId());
		
	}

	public void runChallengeTest(int i) {
		boolean state = challengeChecks.get(i).checkChallenge();
		p.setChallengeBoolsByIndex(i, state);
		
	}
	
	public int getChallengeCheckListSize() {
		return challengeChecks.size();
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


interface Challengable {
	boolean checkChallenge();
}