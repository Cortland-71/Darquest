package com.game.darquest.controller.fightClubControllers;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.controller.Clickable;
import com.game.darquest.controller.Controller;
import com.game.darquest.data.Enemy;
import com.game.darquest.data.EnemyGenerator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class FightClubHubController implements EventHandler<ActionEvent> {
	
	private Controller c;
	private List<Clickable> fightClubHubActions;
	private RandomFight randomFight;
	private Challenges challenges;
	private PlaceBet placeBet;
	private Talk talk;
	private FightTips fightTips;
	private Leave leave;
	
	private String zomDefaultDialoge = "Zom: Welcome to the fight club my man. "
			+ "If you're look'n to start some trouble, you came to the right place. "
			+ "Enter one of our fights and win some serious cash. Or perhaps you're "
			+ "the gambling type and wanna place a bet. Doin a random fight will pit you againts either "
			+ "one, two or three other fighters. If you can knock em out and win, it's $1000 per head. "
			+ "Plus you get some extra items, sometimes they rare, sometimes not but who can say no "
			+ "to free shit?";
	private String challangesDialoge = "Zom: Compleate different challenges for some extra XP and cash. "
			+ "Hover over a challenge on the board to get more information. You can compleate challenges "
			+ "by doing random fights or during your time in the story.";
	private String placeBetDialoge = "Zom: If you don't feel like fighting but wanna try and win some "
			+ "cash you can place a bet on a fighter. Watch em as they duke it out. Can get pretty "
			+ "ugly sometimes. But hey if your fighter wins, you double your bet.";
	private String talkDialoge = "Zom: Talk is cheap.";
	private String fightTipsDialoge = "Zom: I can give ya pointers if you're interested.";
	private String leaveDialoge = "Zom: Have a better one";
	private List<String> zomDialogeList = Arrays.asList(zomDefaultDialoge, 
			challangesDialoge, placeBetDialoge, talkDialoge, fightTipsDialoge, leaveDialoge);
	
	public FightClubHubController(Controller c) {
		this.c = c;
		this.randomFight = new RandomFight(this.c);
		this.challenges = new Challenges(this.c);
		this.placeBet = new PlaceBet(this.c);
		this.talk = new Talk(this.c);
		this.fightTips = new FightTips(this.c);
		this.leave = new Leave(this.c);
		
		fightClubHubActions = Arrays.asList(randomFight, challenges, placeBet, talk, fightTips, leave);
		this.c.getView().getFightClubHub().addActionListener(this);
		hoverButton();
	}
	
	public void hoverButton() {
		c.getView().getFightClubHub().getButtonList()
		.forEach(e->e.setOnMouseEntered(
				s->c.getView().getFightClubHub().setZomTextArea(
						zomDialogeList.get(Integer.parseInt(e.getId())))));
		c.getView().getFightClubHub().getButtonList()
		.forEach(e->e.setOnMouseExited(
				s->c.getView().getFightClubHub().setZomTextArea(
						"")));
	}
	

	@Override
	public void handle(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		int eventIndex = Integer.parseInt(id);
		fightClubHubActions.get(eventIndex).clickAction();
		
	}

	public List<String> getZomDialogeList() {
		return zomDialogeList;
	}

}

class RandomFight implements Clickable {
	private EnemyGenerator enemyGenerator;
	private Controller c;
	public RandomFight(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		enemyGenerator = new EnemyGenerator(this.c);
		enemyGenerator.generateEnemys();
		List<Enemy> enemyList = enemyGenerator.getEnemyList();
		c.getPlayerWinController().setAllCountersToDefault();
		c.getFightClubController().setEnemyList(enemyList);
		
		c.getHubController().drawAllEnemyBoxes(enemyList);
		
		c.getView().getFightClubView().clearAllOutputTextAreas();
		c.getView().getFightClubView().clearCommandField();
		c.getView().getHubView().showRandomFight();
		c.getView().getFightClubView().setCommandFeildFocused();
		c.getView().getFightClubView().setQueueLabel(0);
		
	}
}

class Challenges implements Clickable {
	private Controller c;
	public Challenges(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		c.getView().getFightClubHub().getChallengesSelectView().setChallenges(
				c.getChallengeController().getChallengeCheckList());
		c.getChallengeController().updateChallengeList();
		c.getView().getHubView().showChallenges();
	}
}

class PlaceBet implements Clickable {
	private Controller c;
	public PlaceBet(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("PlaceBet");
	}
}

class Talk implements Clickable {
	private Controller c;
	public Talk(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Talk");
	}
}

class FightTips implements Clickable {
	private Controller c;
	public FightTips(Controller c) {
		this.c = c;
	}
	@Override
	public void clickAction() {
		System.out.println("Fight Tips");
	}
}

class Leave implements Clickable {
	private Controller c;
	public Leave(Controller c) {
		this.c = c;
	}
	
	@Override
	public void clickAction() {
		c.getView().getHubView().showHub();
	}
}
