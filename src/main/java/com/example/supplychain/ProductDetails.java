package com.example.supplychain;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    public TableView<Product> productTable;
    public Pane getAllProducts() {

        TableColumn id = new TableColumn("Id.");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Qty.");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<Product> items = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id, name, price, quantity, description);
        //productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        id.setMinWidth(50);
        name.setMinWidth(250);
        price.setMinWidth(100);
        quantity.setMinWidth(50);
        description.setMinWidth(750);

        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width, SupplyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;

    }

    public Pane getProductsByName (String searchName){

        TableColumn id = new TableColumn("Id.");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Qty.");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<Product> items = Product.getProductsByName(searchName);

        productTable = new TableView<>();
        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id, name, price, quantity, description);
        //productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        id.setMinWidth(50);
        name.setMinWidth(250);
        price.setMinWidth(100);
        quantity.setMinWidth(50);
        description.setMinWidth(750);

        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width, SupplyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }

    public Product getSelectedProduct() {

        if(productTable == null ){
            System.out.println("Table object not found");
            return null;
        }
        if(productTable.getSelectionModel().getSelectedIndex() != -1){
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId() + " " + selectedProduct.getName() + " " +
                    selectedProduct.getPrice() + " " + selectedProduct.getQuantity() +
                    " " + selectedProduct.getDescription());
            return selectedProduct;
        }else{
            System.out.println("Nothing selected");
            return null;
        }
    }

}