package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SupplyChain extends Application {
    public static final int height = 450, width = 1200, upperLine = 100;
    Pane bodyPane = new Pane();
    public Login login = new Login();
    ProductDetails productDetails = new ProductDetails();
    boolean loggedIn = false;
    Label loginLabel;
    Button orderButton;

    private GridPane headbar() throws FileNotFoundException {

        GridPane gridPane = new GridPane();
        //gridPane.setPrefSize(width, upperLine);
        gridPane.setPrefSize(width, upperLine + 20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: #F0E68C");
        gridPane.setHgap(10);
        TextField searchText = new TextField();
        searchText.setMinWidth(250);
        searchText.setPromptText("Please search here");

        loginLabel = new Label("Please Login!");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn == false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                    loginButton.setText("Login");
                }

            }
        });
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ProductDetails productDetails = new ProductDetails();
                bodyPane.getChildren().clear();
                String search = searchText.getText();
                bodyPane.getChildren().add(productDetails.getProductsByName(search));
            }
        });
        gridPane.add(searchText, 0, 0);
        gridPane.add(searchButton, 1, 0);
        gridPane.add(loginLabel, 5,0);
        gridPane.add(loginButton, 6, 0);
        return gridPane;
    }

    private GridPane footerBar() {
        GridPane gridPane = new GridPane();
        orderButton = new Button("Buy Now");
        orderButton.setMinWidth(200);
        orderButton.setTranslateY(0);
        orderButton.setStyle("-fx-background-color: #8FBC8F");
        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn == false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                }
                else{
                    Product product = productDetails.getSelectedProduct();
                    if(product != null){
                        String email = loginLabel.getText();
                        email = email.substring(10);
                        System.out.println(email);
                        if(Order.placeSingleOrder(product,email))
                            System.out.println("Order Placed");
                        else
                            System.out.println("Order Failed");
                    }else{
                        System.out.println("Please select a product");
                    }
                }
            }
        });
        gridPane.add(orderButton, 0, 0);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinWidth(width);
        gridPane.setTranslateY(height+50);
        gridPane.setStyle("-fx-background-color: #F0E68C");
        return gridPane;
    }
    private GridPane loginPage(){

        Label emailLable = new Label("Email");
        Label passwordLable = new Label("Password");
        Label messageLable = new Label("I am a message");

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Please enter email_Id");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Please enter Password");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                if (login.customerLogin(email, password)) {
                    loginLabel.setText("Welcome back : " + email);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                    loggedIn = true;
                    messageLable.setText("Login Successful");

                }
                else messageLable.setText("Invalid User");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getWidth(), bodyPane.getHeight());
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setStyle("-fx-background-color: #C0C9C0;");

        gridPane.add(emailLable, 0, 0);
        gridPane.add(emailTextField, 1, 0);
        gridPane.add(passwordLable, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 0, 2);
        gridPane.add(messageLable, 1, 2);

        return gridPane;
    }
    Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(width,height+upperLine+80);
        bodyPane.setTranslateY(upperLine);
        bodyPane.setMinSize(width,height);
        bodyPane.getChildren().add(productDetails.getAllProducts());
        root.getChildren().addAll(headbar(), bodyPane, footerBar());
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain System!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}