package com.game.darquest.view;

import java.util.Arrays;
import java.util.List;

import com.game.darquest.data.items.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ShopView extends DownTownView {

	private Scene shopScene;
	private ListView<Item> toolsListView = new ListView<>();
	private ListView<Item> armorListView = new ListView<>();
	private ListView<Item> weaponsListView = new ListView<>();
	private List<ListView<Item>> listViewObjects = Arrays.asList(weaponsListView, 
			armorListView, toolsListView);
	
	
	public ShopView() {
		shopScene = new Scene(shopPane(), View.WIDTH, View.HEIGHT);
		shopScene.getStylesheets().addAll("DownTownStyle.css", "ShopStyle.css");
	}	
	
	public BorderPane shopPane() {
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
	
	private HBox center() {
		HBox center = new HBox();
		center.setBackground(View.getBackground(Color.BLACK));
		return center;
	}
	
	private BorderPane bottom() {
		BorderPane bottom = new BorderPane();
		bottom.setTop(buyButtonBox());
		bottom.setCenter(itemTabBox());
		bottom.setRight(itemInfoBox());
		return bottom;
	}
	// Buy Button \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private HBox buyButtonBox() {
		HBox buyButtonBox = new HBox();
		buyButtonBox.setAlignment(Pos.CENTER);
		buyButtonBox.setId("buyButtonBox");
		buyButtonBox.setPadding(new Insets(10,0,0,0));
		buyButtonBox.getChildren().add(buyButton());
		return buyButtonBox;
	}
	
	private Button buyButton;
	private Button buyButton() {
		buyButton = new Button("Buy");
		buyButton.setId("buy");
		buyButton.setMinSize(200, 45);
		buyButton.setMaxSize(200, 45);
		return buyButton;
	}
	
	public void addBuyButtonListener(EventHandler<ActionEvent> l) {
		buyButton.setOnAction(l);
	}
	
	// Item info \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	private HBox itemInfoBox() {
		HBox itemInfoBox = new HBox();
		itemInfoBox.setId("itemInfoBox");
		itemInfoBox.setAlignment(Pos.CENTER_LEFT);
		itemInfoBox.setMaxWidth(360);
		itemInfoBox.setMinWidth(360);
		itemInfoBox.getChildren().add(itemInfoLabel());
		return itemInfoBox;
	}
	
	private Label itemInfoLabel;
	private Label itemInfoLabel() {
		itemInfoLabel = new Label();
		itemInfoLabel.setPadding(new Insets(25,0,0,20));
		itemInfoLabel.setId("itemInfoLabel");
		return itemInfoLabel;
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
		shopTabPane.setMaxSize(950, 225);
		shopTabPane.setMinSize(950, 225);
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
	                    	itemInfoLabel.setText(w.toString());
	                    });
	                    
	                    setTooltip(tooltip);
	                }
	            }
			});
		}
	}
	
	
	public Scene getShopScene() {
		return this.shopScene;
	}
	
	public List<ListView<Item>> getListViewObjects() {
		return this.listViewObjects;
	}
}
