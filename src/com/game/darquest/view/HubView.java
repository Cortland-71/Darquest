package com.game.darquest.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.Player;
import com.game.darquest.data.items.Item;

import javafx.animation.FadeTransition;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HubView {
	
	private View view;

	private Scene downTownScene;
	private List<String> downTownButtonTextList = Arrays.asList("Zom's Fight Club", "Shop", 
			"Email", "Level UP", "Save",
			"Tutorial");
	private List<Button> buttonList = new ArrayList<>();
	private DecimalFormat df = new DecimalFormat("#.##");

	private ListView<Item> toolsListView = new ListView<>();
	private ListView<Item> armorListView = new ListView<>();
	private ListView<Item> weaponsListView = new ListView<>();
	private List<ListView<Item>> inventoryListViewObjects = Arrays.asList(weaponsListView, armorListView,
			toolsListView);

	private Label nameLabel = new Label();
	private Label lvlLabel = new Label();
	private Label weightLabel = new Label();
	private Label hpLabel = new Label();
	private Label cashLabel = new Label();
	private Label engLabel = new Label();
	private Label eatLabel = new Label();
	private Label sleepLabel = new Label();
	private Label workLabel = new Label();
	private Label equippedWeaponLabel = new Label();
	private Label equippedArmorLabel = new Label();
	private Label equippedToolLabel = new Label();
	
	private Label defLabel = new Label();
	private Label stealthLabel = new Label();
	private Label awarenessLabel = new Label();

	public HubView() {
		downTownScene = new Scene(downTownBackground(), View.WIDTH, View.HEIGHT);

		downTownScene.getStylesheets().addAll("styles/HubStyle.css", "styles/ShopStyle.css", 
				"styles/FightClubStyle.css", "styles/FightWinStyle.css");
	}
	
	private VBox downTownBackground() {
		VBox downTownBackground = new VBox();
		downTownBackground.setBackground(View.getBackground(Color.BLACK));
		downTownBackground.setAlignment(Pos.CENTER);
		downTownBackground.getChildren().add(getDownTownPane());
		return downTownBackground;
	}

	private BorderPane bp;
	public BorderPane getDownTownPane() {
		bp = new BorderPane();
		bp.setTop(topBox());
		bp.setLeft(leftBox());
		bp.setBottom(bottomPane());
		bp.setRight(rightPane());
		bp.setCenter(centerBox());
		return bp;
	}
	
	public BorderPane getDownTownPaneObj() {
		return bp;
	}

	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/ -Top Box
	protected HBox topBox() {
		HBox topBox = new HBox(10);
		topBox.setPadding(new Insets(20,0,0,0));
		topBox.setMaxHeight(75);
		topBox.setMinHeight(75);
		topBox.setAlignment(Pos.CENTER);
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

	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/- Left Box
	protected VBox leftBox() {
		VBox leftBox = new VBox(10);
		leftBox.setMaxWidth(300);
		leftBox.setMinWidth(300);
		leftBox.setAlignment(Pos.TOP_LEFT);
		leftBox.setPadding(new Insets(0, View.DEF_PAD, 0, View.DEF_PAD));
		leftBox.getChildren().addAll(nameLabel, lvlLabel, cashLabel, weightLabel, weightBar(), statsBox(),
				equippedBox(), skillsBox());
		return leftBox;
	}

	private ProgressBar weightBar;

	private ProgressBar weightBar() {
		weightBar = new ProgressBar();
		weightBar.setId("weightBar");
		weightBar.setMinSize(barLength, 15);
		weightBar.setMaxSize(barLength, 15);
		return weightBar;
	}

	private VBox statsBox() {
		VBox equippedBox = new VBox(8);
		equippedBox.setPadding(new Insets(20, 0, 0, 0));
		equippedBox.getChildren().addAll(hpLabel, hpBar(), engLabel, engBar(), eatLabel, eatBar(), sleepLabel,
				sleepBar(), workLabel, workBar());
		return equippedBox;
	}

	private final int barLength = 270;
	private final int barWidth = 15;
	private ProgressBar hpBar;
	private ProgressBar engBar;
	private ProgressBar eatBar;
	private ProgressBar sleepBar;
	private ProgressBar workBar;

	private ProgressBar hpBar() {
		hpBar = new ProgressBar();
		hpBar.setId("hpBar");
		hpBar.setMinSize(barLength, barWidth);
		hpBar.setMaxSize(barLength, barWidth);
		return hpBar;
	}

	private ProgressBar engBar() {
		engBar = new ProgressBar();
		engBar.setId("engBar");
		engBar.setMinSize(barLength, barWidth);
		engBar.setMaxSize(barLength, barWidth);
		return engBar;
	}

	private ProgressBar eatBar() {
		eatBar = new ProgressBar();
		eatBar.setId("esBar");
		eatBar.setMinSize(barLength, barWidth);
		eatBar.setMaxSize(barLength, barWidth);
		return eatBar;
	}

	private ProgressBar sleepBar() {
		sleepBar = new ProgressBar();
		sleepBar.setId("esBar");
		sleepBar.setMinSize(barLength, barWidth);
		sleepBar.setMaxSize(barLength, barWidth);
		return sleepBar;
	}

	private ProgressBar workBar() {
		workBar = new ProgressBar();
		workBar.setId("esBar");
		workBar.setMinSize(barLength, barWidth);
		workBar.setMaxSize(barLength, barWidth);
		return workBar;
	}

	
	// Equipped Box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private VBox equippedBox() {
		VBox equippedBox = new VBox(5);
		equippedBox.setPadding(new Insets(20, 0, 0, 0));
		equippedBox.getChildren().addAll(equippedWeaponLabel, equippedArmorLabel, equippedToolLabel);
		return equippedBox;
	}
	
	// Skills Box \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private VBox skillsBox() {
		VBox skillsBox = new VBox(8);
		skillsBox.setPadding(new Insets(20, 0, 0, 0));
		defLabel.setTooltip(new Tooltip("Defense\nThis skill lowers the damage you take\n"
				+ "when getting hit by an enemy."));
		stealthLabel.setTooltip(new Tooltip("Stealth\nAllows you to steal cash more effectivly.\n"
				+ "Awareness counter acts stealth."));
		awarenessLabel.setTooltip(new Tooltip("Awareness\nLowers the chances of the enemy stealing\n"
				+ "or planting devices on you."));
		skillsBox.getChildren().addAll(defLabel, stealthLabel, awarenessLabel);
		return skillsBox;
	}
	

	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Right Box inventory
	private VBox rightPane;

	protected VBox rightPane() {
		rightPane = new VBox();
		rightPane.setAlignment(Pos.TOP_CENTER);
		rightPane.setMaxWidth(300);
		rightPane.setMinWidth(300);
		rightPane.setPadding(new Insets(0, View.DEF_PAD, 0, View.DEF_PAD));
		rightPane.getChildren().add(inventoryLabel());
		rightPane.getChildren().add(inventoryTabPane());
		return rightPane;
	}

	private Label inventoryLabel() {
		return new Label("Inventory");
	}

	private TabPane playerInventoryTabPane;
	private TabPane inventoryTabPane() {
		playerInventoryTabPane = new TabPane();
		playerInventoryTabPane.setMaxHeight(725);
		playerInventoryTabPane.setMinHeight(725);
		playerInventoryTabPane.getTabs().add(weaponsTab());
		playerInventoryTabPane.getTabs().add(armorTab());
		playerInventoryTabPane.getTabs().add(toolsTab());
		return playerInventoryTabPane;
	}

	public TabPane getPlayerInventoryTabPane() {
		return this.playerInventoryTabPane;
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

	private void clearAllInventoryItems() {
		inventoryListViewObjects.forEach(e -> e.getItems().clear());
	}

	public void setAllInventoryItems(List<List<Item>> allItems) {
		clearAllInventoryItems();

		for (int i = 0; i < allItems.size(); i++) {
			inventoryListViewObjects.get(i).getItems().addAll(allItems.get(i));

			inventoryListViewObjects.get(i).setCellFactory(cell -> new ListCell<Item>() {
				Tooltip tooltip = new Tooltip();

				@Override
				protected void updateItem(Item w, boolean empty) {
					super.updateItem(w, empty);
					if (w == null || empty) {
						setText(null);
						setTooltip(null);
					} else {
						setText(w.getName());
						tooltip.setText(w.getToStringForPlayerInventory());
						setTooltip(tooltip);
					}
				}
			});
		}
	}

	public List<Integer> getSelectedIndexListOfAllTabs() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < inventoryListViewObjects.size(); i++) {
			list.add(inventoryListViewObjects.get(i).getSelectionModel().getSelectedIndex());
		}
		return list;
	}

	public List<ListView<Item>> getInventoryListViewObjects() {
		return this.inventoryListViewObjects;
	}

	public void setInventoryListener(EventHandler<MouseEvent> l) {
		inventoryListViewObjects.forEach(e -> e.setOnMouseClicked(l));
	}

	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\-Bottom Box
	protected GridPane bottomPane() {
		int counter = 0;
		GridPane bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setPadding(new Insets(20, 0, 120, 0));
		bottomPane.setVgap(15);
		bottomPane.setHgap(15);
		for (int i = 0; i < downTownButtonTextList.size(); i++) {
			buttonList.add(View.getButton(downTownButtonTextList.get(i), ((Integer) i).toString()));
			if (i < 3) {
				bottomPane.add(buttonList.get(i), i, 0);
				continue;
			}
			bottomPane.add(buttonList.get(i), counter++, 1);
		}
		return bottomPane;
	}
	
	public Button getLevelUpButton() {
		return buttonList.get(3);
	}
	
	public void setLevelUpButtonDisabled(boolean state) {
		buttonList.get(3).setDisable(state);
	}

	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Center
	private VBox centerBox;
	private VBox centerBox() {
		centerBox = new VBox();
		centerBox.setId("centerBox");
		centerBox.setMinSize(1200, 600);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setBackground(View.getBackground(Color.BLACK));
		centerBox.getChildren().add(getDownTownImage());
		return centerBox;
	}
	
	private Label getDownTownImage() {
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/downTown.png"))));
        return l;
	}
	
	public void showLevelUp() {
		centerBox.getChildren().remove(0);
		centerBox.getChildren().add(view.getLevelUpView().levelUpCenter());
		bp.setBottom(view.getLevelUpView().getLevelUpBottom());
	}
	
	public void showShop() {
		centerBox.getChildren().remove(0);
		centerBox.getChildren().add(view.getShopView().getActualShopPane());
		bp.setBottom(view.getShopView().getBottomBox());
		fadeIn();
	}
	
	public void showHub() {
		centerBox.getChildren().remove(0);
		centerBox.getChildren().add(getDownTownImage());
		bp.setBottom(bottomPane());
		fadeIn();
	}
	
	public void showFightClubHub() {
		centerBox.getChildren().remove(0);
		centerBox.getChildren().add(view.getFightClubHub().getFightClubHubCenter());
		bp.setBottom(view.getFightClubHub().getFightClubHubBottom());
		fadeIn();
	}
	
	public void showRandomFight() {
		centerBox.getChildren().remove(0);
		centerBox.getChildren().add(view.getFightClubView().getFightClubCenter());
		bp.setBottom(view.getFightClubView().getFightClubBottom());
		fadeIn();
	}
	
	public void showWin() {
		centerBox.getChildren().remove(0);
		centerBox.getChildren().add(view.getFightWinView().getFightWinCenter());
		bp.setBottom(view.getFightWinView().getFightWinBottom());
		fadeIn();
	}
	
	private void fadeIn() {
		FadeTransition ft = new FadeTransition(Duration.millis(800), 
				centerBox);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();
	}

	// \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\- Helper
	
	public void setPlayerStats(Player p) {
		xpAmountLabel.setText(p.getXp() + "/1.0");
		nameLabel.setText("Name:\t" + p.getName());
		lvlLabel.setText("Lvl:\t" + p.getLvl());
		cashLabel.setText("Cash:\t" + p.getCashFormatted());
		weightLabel.setText("Weight: " + df.format(p.getWeight()) + "/1.0");
		hpLabel.setText("HP:\t" + p.getHp() + "/1.0");
		engLabel.setText("Eng:\t" + p.getEng() + "/1.0");
		eatLabel.setText("Eat:\t" + p.getEat() + "/1.0");
		sleepLabel.setText("Sleep:\t" + p.getSleep() + "/1.0");
		workLabel.setText("Work:\t" + p.getWork() + "/1.0");

		if(p.getXp() >= 1) {
			buttonList.get(3).setStyle("-fx-text-fill: orange;"
					+ "-fx-border-color: orange;");
			setLevelUpButtonDisabled(false);
		} else {
			buttonList.get(3).setStyle("-fx-text-fill: _green;"
					+ "-fx-border-color: _green;");
			setLevelUpButtonDisabled(true);
		}
		xpBar.setProgress(p.getXp());
		weightBar.setProgress(p.getWeight());
		hpBar.setProgress(p.getHp());
		engBar.setProgress(p.getEng());
		eatBar.setProgress(p.getEat());
		sleepBar.setProgress(p.getSleep());
		workBar.setProgress(p.getWork());

		equippedWeaponLabel.setText("Wep:\t" + p.getEquippedWeaponString());
		equippedArmorLabel.setText("Arm:\t" + p.getEquippedArmorString());
		equippedToolLabel.setText("Tool:\t" + p.getEquippedToolString());
		
		
		if(p.getDef() < p.getMaxDef()) {
			defLabel.setStyle("-fx-text-fill: red");
		} else {
			defLabel.setStyle("-fx-text-fill: _lightBlue");
		}
		
		if(p.getStealth() < p.getMaxStealth()) {
			stealthLabel.setStyle("-fx-text-fill: red");
		} else {
			stealthLabel.setStyle("-fx-text-fill: _lightBlue");
		}
		
		if(p.getAwareness() < p.getMaxAwareness()) {
			awarenessLabel.setStyle("-fx-text-fill: red");
		} else {
			awarenessLabel.setStyle("-fx-text-fill: _lightBlue");
		}
		
		defLabel.setText("Defense:   " + p.getDef());
		stealthLabel.setText("Stealth:   " + p.getStealth());
		awarenessLabel.setText("Awareness: " + p.getAwareness());
	}
	
//	public void animateWorkBar(Player p) {
//		Timeline timeline = new Timeline(
//				new KeyFrame(Duration.millis(25), 
//						ae-> workBar.setProgress(p.getWork()-.1)),
//				new KeyFrame(Duration.millis(25*2), 
//						ae->workBar.setProgress(p.getWork()-.08)),
//				new KeyFrame(Duration.millis(25*3), 
//						ae->workBar.setProgress(p.getWork()-.06)),
//				new KeyFrame(Duration.millis(25*4), 
//						ae-> workBar.setProgress(p.getWork()-.04)),
//				new KeyFrame(Duration.millis(25*5), 
//						ae-> workBar.setProgress(p.getWork()-.02)),
//				new KeyFrame(Duration.millis(25*5), 
//						ae-> workBar.setProgress(p.getWork()))
//				);
//		timeline.playFromStart();
//	}

	public Scene getDownTownScene() {
		return this.downTownScene;
	}

	public void addActionListener(EventHandler<ActionEvent> l) {
		buttonList.forEach(e -> e.setOnAction(l));
	}

	public void setView(View view) {
		this.view = view;
		
	}
}
