package com.game.darquest.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JProgressBar;

import com.game.darquest.data.Item;
import com.game.darquest.data.Person;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DownTownView {
	
	private Scene downTownScene;
	private List<String> downTownButtonTextList = Arrays.asList("Fight Club", "Shop", "Email",
			"Main Menu", "Save", "Tutorial");
	private List<Button> buttonList = new ArrayList<>();
	
	private ListView<Item> toolsListView = new ListView<>();
	private ListView<Item> armorListView = new ListView<>();
	private ListView<Item> weaponsListView = new ListView<>();
	private List<ListView<Item>> listViewObjects = Arrays.asList(weaponsListView, 
			armorListView, toolsListView);
	
	public DownTownView() {
		downTownScene = new Scene(getDownTownPane(), View.WIDTH, View.HEIGHT);
		downTownScene.getStylesheets().add("downTownStyle.css");
	}
	
	public BorderPane getDownTownPane() {
		BorderPane bp = new BorderPane();
		bp.setTop(topBox());
		bp.setLeft(leftBox());
		bp.setBottom(bottomPane());
		bp.setRight(rightPane());
		bp.setCenter(centerBox());
		return bp;
	}
	
	//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/ -Top Box
	protected HBox topBox() {
		HBox topBox = new HBox(10);
		topBox.setMaxHeight(75);
		topBox.setMinHeight(75);
		topBox.setAlignment(Pos.CENTER);
		topBox.setPadding(new Insets(View.DEF_PAD,0,View.DEF_PAD,0));
		topBox.getChildren().add(xpLabel());
		topBox.getChildren().add(xpBar());
		topBox.getChildren().add(xpAmountLabel());
		return topBox;
	}
	
	private Label xpLabel() {
		Label xpLabel = new Label("XP:");
		return xpLabel;
	}
	
	private ProgressBar xpBar;
	private ProgressBar xpBar() {
		xpBar = new ProgressBar();
		xpBar.setId("xpBar");
		xpBar.setMinSize(1150, 20);
		xpBar.setMaxSize(1150, 20);
		return xpBar;
	}
	
	private Label xpAmountLabel;
	private Label xpAmountLabel() {
		xpAmountLabel = new Label("0/100");
		return xpAmountLabel;
	}
	

	
	//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/- Left Box
	protected VBox leftBox() {
		VBox leftBox = new VBox(8);
		leftBox.setMaxWidth(300);
		leftBox.setMinWidth(300);
		leftBox.setAlignment(Pos.TOP_LEFT);
		leftBox.setPadding(new Insets(View.DEF_PAD,View.DEF_PAD,0,View.DEF_PAD));
		leftBox.getChildren().addAll(nameLabel(), lvlLabel(), hpLabel(), hpBar(),
				engLabel(), engBar(), eatLabel(), eatBar(), sleepLabel(), sleepBar(),
				workLabel(), workBar(), cashLabel());
		return leftBox;
	}
	
	
	private Label nameLabel;
	private Label nameLabel() {
		nameLabel = new Label();
		return nameLabel;
	}
	
	private Label lvlLabel;
	private Label lvlLabel() {
		lvlLabel = new Label();
		return lvlLabel;
	}
	
	private Label hpLabel;
	private Label hpLabel() {
		hpLabel = new Label();
		return hpLabel;
	}
	private int barLength = 270;
	private ProgressBar hpBar;
	private ProgressBar hpBar() {
		hpBar = new ProgressBar();
		hpBar.setId("hpBar");
		hpBar.setMinSize(barLength, 15);
		hpBar.setMaxSize(barLength, 15);
		return hpBar;
	}
	
	private Label engLabel;
	private Label engLabel() {
		engLabel = new Label();
		return engLabel;
	}
	
	private ProgressBar engBar;
	private ProgressBar engBar() {
		engBar = new ProgressBar(0);
		engBar.setId("engBar");
		engBar.setMinSize(barLength, 15);
		engBar.setMaxSize(barLength, 15);
		return engBar;
	}
	
	private Label eatLabel;
	private Label eatLabel() {
		eatLabel = new Label();
		return eatLabel;
	}
	
	private ProgressBar eatBar;
	private ProgressBar eatBar() {
		eatBar = new ProgressBar(0);
		eatBar.setId("esBar");
		eatBar.setMinSize(barLength, 15);
		eatBar.setMaxSize(barLength, 15);
		return eatBar;
	}
	
	private Label sleepLabel;
	private Label sleepLabel() {
		sleepLabel = new Label();
		return sleepLabel;
	}
	
	private ProgressBar sleepBar;
	private ProgressBar sleepBar() {
		sleepBar = new ProgressBar(0);
		sleepBar.setId("esBar");
		sleepBar.setMinSize(barLength, 15);
		sleepBar.setMaxSize(barLength, 15);
		return sleepBar;
	}
	
	private Label workLabel;
	private Label workLabel() {
		workLabel = new Label();
		return workLabel;
	}
	
	private ProgressBar workBar;
	private ProgressBar workBar() {
		workBar = new ProgressBar(0);
		workBar.setId("esBar");
		workBar.setMinSize(barLength, 15);
		workBar.setMaxSize(barLength, 15);
		return workBar;
	}
	
	private Label cashLabel;
	private Label cashLabel() {
		cashLabel = new Label();
		return cashLabel;
	}
	
	public void setPlayerStats(Person p) {
		xpAmountLabel.setText(p.getXp()+"/1.00");
		xpBar.setProgress(p.getXp());
		
		nameLabel.setText("Name: \t"+p.getName());
		lvlLabel.setText("Lvl: \t"+p.getLvl());
		
		hpLabel.setText("HP: \t"+p.getHp()+"/1.0");
		hpBar.setProgress(p.getHp());
		
		engLabel.setText("ENG: \t"+p.getEng()+"/1.0");
		engBar.setProgress(p.getEng());
		
		eatLabel.setText("Eat: \t"+p.getEat()+"/1.0");
		eatBar.setProgress(p.getEat());
		
		sleepLabel.setText("Sleep: \t"+p.getSleep()+"/1.0");
		sleepBar.setProgress(p.getSleep());
		
		workLabel.setText("Work: \t"+p.getWork()+"/1.0");
		workBar.setProgress(p.getWork());
		
		cashLabel.setText("Cash: \t"+p.getCashFormatted());
	}
	
	//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Right Box inventory
	private VBox rightPane;
	protected VBox rightPane() {
		rightPane = new VBox(10);
		rightPane.setAlignment(Pos.TOP_CENTER);
		rightPane.setMaxWidth(300);
		rightPane.setMinWidth(300);
		rightPane.setPadding(new Insets(View.DEF_PAD,View.DEF_PAD,0,View.DEF_PAD));
		rightPane.getChildren().add(inventoryLabel());
		rightPane.getChildren().add(inventoryPane());
		return rightPane;
	}
	
	private Label inventoryLabel() {
		return new Label("Inventory");
	}
	
	private TabPane inventoryPane;
	private TabPane inventoryPane() {
		inventoryPane = new TabPane();
		inventoryPane.getTabs().add(weaponsTab());
		inventoryPane.getTabs().add(armorTab());
		inventoryPane.getTabs().add(toolsTab());
		return inventoryPane;
	}
	
	private Tab weaponsTab;
	private Tab weaponsTab() {
		weaponsTab = new Tab("Weapons");
		weaponsTab.setId("invWepTab");
		weaponsTab.setClosable(false);
		weaponsTab.setContent(weaponsListView);
		return weaponsTab;
	}
	
	
	private Tab armorTab() {
		Tab armorTab = new Tab("Armor");
		armorTab.setId("invArmTab");
		armorTab.setClosable(false);
		armorTab.setContent(armorListView);
		return armorTab;
	}
	
	private Tab toolsTab() {
		Tab toolsTab = new Tab("Tools");
		toolsTab.setId("invToolTab");
		toolsTab.setClosable(false);
		toolsTab.setContent(toolsListView);
		return toolsTab;
	}
	
	public void clearAllInventoryItems() {
		listViewObjects.forEach(e->e.getItems().clear());
	}
	
	public void setAllInventoryItems(List<List<Item>> allItems) {

		for (int i = 0; i < allItems.size(); i++) {
			listViewObjects.get(i).getItems().addAll(allItems.get(i));
			
			listViewObjects.get(i).setCellFactory(cell->new ListCell<Item>() {
				Tooltip tooltip = new Tooltip();
	            @Override
	            protected void updateItem(Item w, boolean empty) {
	                super.updateItem(w, empty);
	                if (w == null || empty) {
	                    setText(null);
	                    setTooltip(null);
	                } else {
	                    setText(w.getName());
	                    tooltip.setText(w.toString());
	                    setOnMouseEntered(e-> {
	                    });
	                    setTooltip(tooltip);
	                }
	            }
			});
		}
	}
	
	
	//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\-Bottom Box
	protected GridPane bottomPane() {
		int counter = 0;
		GridPane bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setPadding(new Insets(20,0,120,0));
		bottomPane.setVgap(15); 
	    bottomPane.setHgap(15);
		for (int i = 0; i < downTownButtonTextList.size(); i++) {
			buttonList.add(View.getButton(downTownButtonTextList.get(i), 
					((Integer)i).toString()));
			if(i<3) {
				bottomPane.add(buttonList.get(i), i,0);
				continue;
			}
			bottomPane.add(buttonList.get(i), counter++,1);
		}
		return bottomPane;
	}
		
	
	//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Center
	private VBox centerBox() {
		VBox centerBox = new VBox();
		centerBox.setBackground(View.getBackground(Color.BLACK));
		return centerBox;
	}
	
	
	//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Helper
	public Scene getDownTownScene() {
		return this.downTownScene;
	}
	
	public void addActionListener(EventHandler<ActionEvent> l ) {
		buttonList.forEach(e->e.setOnAction(l));
	}
}
