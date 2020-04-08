package com.game.darquest.view;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ShopView extends DownTownView {

	private Scene shopScene;
	private ListView<Item> toolsListView = new ListView<>();
	private ListView<Item> armorListView = new ListView<>();
	private ListView<Item> weaponsListView = new ListView<>();
	private List<ListView<Item>> shopListViewObjects = Arrays.asList(weaponsListView, 
			armorListView, toolsListView);
	private final int shopDialogeBoxWidth = 645; 
	
	public ShopView() {
		shopScene = new Scene(shopPane(), View.WIDTH, View.HEIGHT);
		shopScene.getStylesheets().addAll("DownTownStyle.css", "ShopStyle.css");
	}	
	
	private BorderPane shopPane() {
		BorderPane shopPane = new BorderPane();
		shopPane.setTop(topBox());
		shopPane.setLeft(leftBox());
		shopPane.setBottom(bottomPane());
		shopPane.setRight(rightPane());
		shopPane.setCenter(actualShopPane());
		
		return shopPane;
	}

	private BorderPane actualShopPane;
	private BorderPane actualShopPane() {
		actualShopPane = new BorderPane();
		actualShopPane.setCenter(center());
		actualShopPane.setBottom(bottom());
		return actualShopPane;
	}
	
	//Center of actual shop pane
	private BorderPane center() {
		BorderPane center = new BorderPane();
		center.setBackground(View.getBackground(Color.BLACK));
		Image image = new Image("file:Shop.png");
		ImageView shopImage = new ImageView(image);
		center.setCenter(shopImage);
		center.setBottom(shopDialogeBox());
		return center;
	}
	
	private HBox shopDialogeBox() {
		HBox shopDialogeBox = new HBox(10);
		shopDialogeBox.setPadding(new Insets(0,10,10,10));
		shopDialogeBox.getChildren().add(buyShopDialogeTextArea());
		shopDialogeBox.getChildren().add(sellShopDialogeTextArea());
		return shopDialogeBox;
	}
	
	private TextArea buyShopDialogeTextArea;
	private TextArea buyShopDialogeTextArea() {
		buyShopDialogeTextArea = new TextArea();
		buyShopDialogeTextArea.setId("buyShopDialoge");
		buyShopDialogeTextArea.setWrapText(true);
		buyShopDialogeTextArea.setEditable(false);
		buyShopDialogeTextArea.setMinSize(shopDialogeBoxWidth,100);
		buyShopDialogeTextArea.setMaxSize(shopDialogeBoxWidth,100);
		return buyShopDialogeTextArea;
	}
	
	public void setBuyShopDialogeTextArea(String text) {
		buyShopDialogeTextArea.setText(text);
	}
	
	public void setBuyShopDialogeRed() {
		buyShopDialogeTextArea.setStyle("-fx-text-fill: red;");
	}
	
	public void setBuyShopDialogeNormal() {
		buyShopDialogeTextArea.setStyle("-fx-text-fill: orange;");
	}
	
	private TextArea sellShopDialogeTextArea;
	private TextArea sellShopDialogeTextArea() {
		sellShopDialogeTextArea = new TextArea();
		sellShopDialogeTextArea.setId("sellShopDialoge");
		sellShopDialogeTextArea.setWrapText(true);
		sellShopDialogeTextArea.setEditable(false);
		sellShopDialogeTextArea.setMinSize(shopDialogeBoxWidth,100);
		sellShopDialogeTextArea.setMaxSize(shopDialogeBoxWidth,100);
		return sellShopDialogeTextArea;
	}
	
	public void setSellShopDialogeTextArea(String text) {
		sellShopDialogeTextArea.setText(text);
	}
	
	public void setSellShopDialogeRed() {
		sellShopDialogeTextArea.setStyle("-fx-text-fill: red;");
	}
	
	public void setSellShopDialogeNormal() {
		sellShopDialogeTextArea.setStyle("-fx-text-fill: _green;");
	}
	
	
	//Bottom of actual Shop Pane
	private BorderPane bottom() {
		BorderPane bottom = new BorderPane();
		bottom.setTop(buyAndSellButtonBox());
		bottom.setCenter(itemTabBox());
		bottom.setRight(itemInfoBox());
		return bottom;
	}
	// Buy Button \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private HBox buyAndSellButtonBox() {
		HBox buyAndSellButtonBox = new HBox(460);
		buyAndSellButtonBox.setAlignment(Pos.CENTER);
		buyAndSellButtonBox.setId("buyButtonBox");
		buyAndSellButtonBox.setPadding(new Insets(10,0,0,0));
		buyAndSellButtonBox.getChildren().add(buyButton());
		buyAndSellButtonBox.getChildren().add(sellButton());
		return buyAndSellButtonBox;
	}
	
	private Button buyButton;
	private Button buyButton() {
		buyButton = new Button("Buy");
		buyButton.setId("buy");
		buyButton.setMinSize(200, 45);
		buyButton.setMaxSize(200, 45);
		return buyButton;
	}
	
	private Button sellButton;
	private Button sellButton() {
		sellButton = new Button("Sell");
		sellButton.setId("sell");
		sellButton.setMinSize(200, 45);
		sellButton.setMaxSize(200, 45);
		return sellButton;
	}
	
	public void addShopActionListener(EventHandler<ActionEvent> l) {
		buyButton.setOnAction(l);
		sellButton.setOnAction(l);
		backButton.setOnAction(l);
	}
	
	// Item info \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private HBox itemInfoBox() {
		HBox itemInfoBox = new HBox(20);
		itemInfoBox.setId("itemInfoBox");
		itemInfoBox.setAlignment(Pos.TOP_LEFT);
		itemInfoBox.setMaxWidth(450);
		itemInfoBox.setMinWidth(450);
		itemInfoBox.getChildren().addAll(selectedItemInfoLabel(), itemInfoLabel());
		return itemInfoBox;
	}
	
	private Label itemInfoLabel;
	private Label itemInfoLabel() {
		itemInfoLabel = new Label("Browse:");
		itemInfoLabel.setPadding(new Insets(25,0,0,10));
		itemInfoLabel.setId("itemInfoLabel");
		return itemInfoLabel;
	}
	
	private Label selectedItemInfoLabel;
	private Label selectedItemInfoLabel() {
		selectedItemInfoLabel = new Label();
		selectedItemInfoLabel.setPadding(new Insets(25,0,0,10));
		selectedItemInfoLabel.setId("selectedItemInfoLabel");
		return selectedItemInfoLabel;
	}
	
	public void setSelectedItemInfoLabel(String text) {
		selectedItemInfoLabel.setText("Selected:\n"+text);
	}
	
	// Shop item list and tabs \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private HBox itemTabBox() {
		HBox itemTabBox = new HBox(0);
		itemTabBox.setId("itemTabBox");
		itemTabBox.getChildren().add(shopTabPane());
		return itemTabBox;
	}
	
	private TabPane shopTabPane;
	private TabPane shopTabPane() {
		shopTabPane = new TabPane();
		shopTabPane.setId("shopTabPane");
		shopTabPane.setMaxSize(750, 225);
		shopTabPane.setMinSize(750, 225);
		shopTabPane.getTabs().add(weaponsTab());
		shopTabPane.getTabs().add(armorTab());
		shopTabPane.getTabs().add(toolsTab());
		return shopTabPane;
	}
	
	public TabPane getShopTabPane() {
		return this.shopTabPane;
	}
	
	
	
	//List views and tabs for Shop \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\

	private Tab weaponsTab() {
		Tab weaponsTab = new Tab("Weapons");
		weaponsTab.setId("shopWeaponsTab");
		weaponsTab.setClosable(false);
		weaponsTab.setContent(weaponsListView);
		return weaponsTab;
	}

	//Armor tab
	private Tab armorTab() {
		Tab armorTab = new Tab("Armor");
		armorTab.setId("shopArmorTab");
		armorTab.setClosable(false);
		armorTab.setContent(armorListView);
		return armorTab;
	}

	//Tools tab
	private Tab toolsTab() {
		Tab toolsTab = new Tab("Tools");
		toolsTab.setId("shopToolsTab");
		toolsTab.setClosable(false);
		toolsTab.setContent(toolsListView);
		return toolsTab;
	}
	
	
	public void setAllShopItems(List<List<Item>> allItems) {

		for (int i = 0; i < allItems.size(); i++) {
			shopListViewObjects.get(i).getItems().addAll(allItems.get(i));
			shopListViewObjects.get(i).setCellFactory(cell->new ListCell<Item>() {
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
	                    	itemInfoLabel.setText("Browes:\n"+w.toString());
	                    });
	                    
	                    setTooltip(tooltip);
	                }
	            }
			});
		}
	}
	
	
	protected GridPane bottomPane() {
		GridPane bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setPadding(new Insets(20, 0, 75, 0));
		bottomPane.setVgap(15);
		bottomPane.setHgap(15);
		bottomPane.getChildren().add(backButton());
		return bottomPane;
	}
	
	
	private Button backButton;
	private Button backButton() {
		backButton = new Button("Back");
		backButton.setId("back");
		backButton.setMinSize(200, 50);
		return backButton;
	}
	
	
	
	public Scene getShopScene() {
		return this.shopScene;
	}
	
	public List<ListView<Item>> getShopListViewObjects() {
		return this.shopListViewObjects;
	}
	
	public void addShopTabListener(EventHandler<MouseEvent> l) {
		shopTabPane.setOnMouseClicked(l);
	}
	
	public void addShopListViewListener(EventHandler<MouseEvent> l) {
		shopListViewObjects.forEach(e->e.setOnMouseClicked(l));
	}
}
