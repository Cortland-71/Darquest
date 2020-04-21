package com.game.darquest.view.hub;

import com.game.darquest.view.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HubAnimation {

	private View view;
	public HubAnimation() {}
	
	public void setView(View view) {
		this.view = view;
	}
	
	private Timeline hubTimeline;
	
	public Timeline getHubTimeline() {
		return hubTimeline;
	}
	
	public void getTimeline() {
		removeAndAddDownTownImage0();
		final int hubTime = 200;
		hubTimeline = new Timeline(
				new KeyFrame(Duration.millis(hubTime), 
						ae-> removeAndAddDownTownImage0()),
				new KeyFrame(Duration.millis(hubTime*2), 
						ae-> removeAndAddDownTownImage1()),
				new KeyFrame(Duration.millis(hubTime*3), 
						ae-> removeAndAddDownTownImage2()),
				new KeyFrame(Duration.millis(hubTime*4), 
						ae-> removeAndAddDownTownImage3()),
				new KeyFrame(Duration.millis(hubTime*5), 
						ae-> removeAndAddDownTownImage4()),
				new KeyFrame(Duration.millis(hubTime*6), 
						ae-> removeAndAddDownTownImage5()),
				
				new KeyFrame(Duration.millis(hubTime*7), 
						ae-> removeAndAddDownTownImage6()),
				new KeyFrame(Duration.millis(hubTime*8), 
						ae-> removeAndAddDownTownImage7()),
				new KeyFrame(Duration.millis(hubTime*9), 
						ae-> removeAndAddDownTownImage8()),
				new KeyFrame(Duration.millis(hubTime*10), 
						ae-> removeAndAddDownTownImage9()),
				new KeyFrame(Duration.millis(hubTime*11), 
						ae-> removeAndAddDownTownImage10()),
				
				new KeyFrame(Duration.millis(hubTime*12), 
						ae-> removeAndAddDownTownImage9()),
				new KeyFrame(Duration.millis(hubTime*13), 
						ae-> removeAndAddDownTownImage8()),
				new KeyFrame(Duration.millis(hubTime*14), 
						ae-> removeAndAddDownTownImage7()),
				new KeyFrame(Duration.millis(hubTime*15), 
						ae-> removeAndAddDownTownImage6()),
				new KeyFrame(Duration.millis(hubTime*16), 
						ae-> removeAndAddDownTownImage5()),
				new KeyFrame(Duration.millis(hubTime*17), 
						ae-> removeAndAddDownTownImage4()),
				new KeyFrame(Duration.millis(hubTime*18), 
						ae-> removeAndAddDownTownImage3()),
				new KeyFrame(Duration.millis(hubTime*19), 
						ae-> removeAndAddDownTownImage2()),
				new KeyFrame(Duration.millis(hubTime*20), 
						ae-> removeAndAddDownTownImage1())
			
				);
		hubTimeline.setCycleCount(Timeline.INDEFINITE);	
		hubTimeline.playFromStart();
	}

	public Label getDownTownImage0() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown0.png"))));
        return l;
	}
	
	private Label getDownTownImage1() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown1.png"))));
        return l;
	}
	private Label getDownTownImage2() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown2.png"))));
        return l;
	}
	private Label getDownTownImage3() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown3.png"))));
        return l;
	}
	private Label getDownTownImage4() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown4.png"))));
        return l;
	}
	private Label getDownTownImage5() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown5.png"))));
        return l;
	}
	private Label getDownTownImage6() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown6.png"))));
        return l;
	}
	private Label getDownTownImage7() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown7.png"))));
        return l;
	}
	private Label getDownTownImage8() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown8.png"))));
        return l;
	}
	private Label getDownTownImage9() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown9.png"))));
        return l;
	}
	private Label getDownTownImage10() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown10.png"))));
        return l;
	}
	
	
	public void removeAndAddDownTownImage0() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage0());
	}
	
	public void removeAndAddDownTownImage1() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage1());
	}
	public void removeAndAddDownTownImage2() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage2());
	}
	public void removeAndAddDownTownImage3() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage3());
	}
	public void removeAndAddDownTownImage4() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage4());
	}
	public void removeAndAddDownTownImage5() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage5());
	}
	public void removeAndAddDownTownImage6() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage6());
	}
	public void removeAndAddDownTownImage7() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage7());
	}
	public void removeAndAddDownTownImage8() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage8());
	}
	public void removeAndAddDownTownImage9() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage9());
	}
	public void removeAndAddDownTownImage10() {
		view.getHubView().getCenterBox().getChildren().remove(0);
		view.getHubView().getCenterBox().getChildren().add(getDownTownImage10());
	}
}
