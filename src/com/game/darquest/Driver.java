package com.game.darquest;

import com.game.darquest.controller.Controller;
import com.game.darquest.view.View;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application {
	private static Font pixleFont;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		pixleFont = Font.loadFont(getClass()
                .getResourceAsStream("/8-BIT FORTRESS.TTF"), 20);
		

		View view = new View(primaryStage);
		@SuppressWarnings("unused")
		Controller controller  = new Controller(view);
	}

	public static Font getPixleFont() {
		return pixleFont;
	}
	
	

}
