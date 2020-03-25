package com.game.darquest;

import com.game.darquest.controller.Controller;
import com.game.darquest.view.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		View view = new View(primaryStage);
		Controller controller  = new Controller(view);
	}
	
	

}
