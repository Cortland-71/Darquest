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

public class ShopView {

	//private Scene shopScene;
	private ListView<Item> toolsListView = new ListView<>();
	private ListView<Item> armorListView = new ListView<>();
	private ListView<Item> weaponsListView = new ListView<>();
	private List<ListView<Item>> shopListViewObjects = Arrays.asList(weaponsListView, 
			armorListView, toolsListView);
	
	public ShopView() {
		bottomBox();
		actualShopPane();
	}	

	private BorderPane actualShopPane;
	private BorderPane actualShopPane() {
		actualShopPane = new BorderPane();
		actualShopPane.setCenter(center());
		actualShopPane.setBottom(bottom());
		return actualShopPane;
	}
	
	public BorderPane getActualShopPane() {
		return actualShopPane;
	}
	
	//Center of actual shop pane
	private BorderPane center() {
		BorderPane center = new BorderPane();
		center.setBackground(View.getBackground(Color.BLACK));
		Label l = new Label();
        l.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ShopSoft.png"))));
		center.setCenter(l);
		center.setBottom(shopDialogeBox());
		return center;
	}
	
	private HBox shopDialogeBox() {
		HBox shopDialogeBox = new HBox();
		shopDialogeBox.setAlignment(Pos.CENTER);
		shopDialogeBox.getChildren().add(shopDialogeTextArea());
		return shopDialogeBox;
	}
	
	private TextArea shopDialogeTextArea;
	private TextArea shopDialogeTextArea() {
		shopDialogeTextArea = new TextArea();
		shopDialogeTextArea.setId("shopTextArea");
		shopDialogeTextArea.setWrapText(true);
		shopDialogeTextArea.setEditable(false);
		shopDialogeTextArea.setMinSize(1200,100);
		shopDialogeTextArea.setMaxSize(1200,100);
		return shopDialogeTextArea;
	}
	
	public void setBuyShopDialogeTextArea(String text) {
		shopDialogeTextArea.setText(text);
	}
	
	public void setBuyShopDialogeRed() {
		shopDialogeTextArea.setStyle("-fx-text-fill: red;");
	}
	
	public void setBuyShopDialogeNormal() {
		shopDialogeTextArea.setStyle("-fx-text-fill: orange;");
	}
	
	//Bottom of actual Shop Pane
	private BorderPane bottom() {
		BorderPane bottom = new BorderPane();
		bottom.setCenter(itemTabBox());
		bottom.setRight(itemInfoBox());
		return bottom;
	}
	
	// Item info \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private HBox itemInfoBox() {
		HBox itemInfoBox = new HBox(0);
		itemInfoBox.setId("itemInfoBox");
		itemInfoBox.setAlignment(Pos.TOP_LEFT);
		itemInfoBox.setMaxWidth(550);
		itemInfoBox.setMinWidth(550);
		itemInfoBox.getChildren().addAll(itemInfoLabel());
		return itemInfoBox;
	}
	
	private Label itemInfoLabel;
	private Label itemInfoLabel() {
		itemInfoLabel = new Label("Browse:");
		itemInfoLabel.setPadding(new Insets(25,0,0,0));
		itemInfoLabel.setId("itemInfoLabel");
		return itemInfoLabel;
	}
	
	
	// Shop item list and tabs \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	private HBox itemTabBox() {
		HBox itemTabBox = new HBox(0);
		itemTabBox.setAlignment(Pos.TOP_LEFT);
		itemTabBox.setId("itemTabBox");
		itemTabBox.getChildren().add(shopTabPane());
		return itemTabBox;
	}
	
	private TabPane shopTabPane;
	private TabPane shopTabPane() {
		shopTabPane = new TabPane();
		shopTabPane.setId("shopTabPane");
		shopTabPane.setMaxSize(750, 200);
		shopTabPane.setMinSize(750, 200);
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
	                    	itemInfoLabel.setText(w.toString());
	                    });
	                    
	                    setTooltip(tooltip);
	                }
	            }
			});
		}
	}
	
	private HBox bottomBox;
	private HBox bottomBox() {
		bottomBox = new HBox(100);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(20, 0, 185, 0));
		bottomBox.getChildren().add(buyButton());
		bottomBox.getChildren().add(backButton());
		bottomBox.getChildren().add(sellButton());
		return bottomBox;
	}
	
	public HBox getBottomBox() {
		return bottomBox;
	}
	
	
	
	private Button buyButton;
	private Button buyButton() {
		buyButton = new Button("Buy");
		buyButton.setId("buy");
		buyButton.setMinSize(225, 50);
		buyButton.setMaxSize(225, 50);
		return buyButton;
	}
	
	private Button sellButton;
	private Button sellButton() {
		sellButton = new Button("Sell");
		sellButton.setId("sell");
		sellButton.setMinSize(225, 50);
		sellButton.setMaxSize(225, 50);
		return sellButton;
	}

	private Button backButton;
	private Button backButton() {
		backButton = new Button("Back");
		backButton.setId("back");
		backButton.setMinSize(225, 50);
		return backButton;
	}
	
	public void addShopActionListener(EventHandler<ActionEvent> l) {
		buyButton.setOnAction(l);
		sellButton.setOnAction(l);
		backButton.setOnAction(l);
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
